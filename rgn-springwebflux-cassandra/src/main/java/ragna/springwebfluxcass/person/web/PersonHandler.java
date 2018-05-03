package ragna.springwebfluxcass.person.web;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import ragna.springwebfluxcass.person.domain.service.Person;
import ragna.springwebfluxcass.person.domain.service.PersonManager;
import reactor.core.publisher.Mono;

@Component
public class PersonHandler {

    private final PersonManager personManager;

    public PersonHandler(PersonManager personManager) {
        this.personManager = personManager;
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        final UUID id = UUID.fromString(request.pathVariable("id"));
        final Mono<Person> person = personManager.findById(id);
        return person
                .flatMap(p ->
                    ok().contentType(MediaType.APPLICATION_JSON)
                    .body(fromPublisher(person, Person.class)))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> all(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(personManager.findAll(), Person.class));
    }

    public Mono<ServerResponse> post (ServerRequest request) {
        final Mono<Person> person = request.bodyToMono(Person.class);
        final UUID id = UUID.randomUUID();
        return created(UriComponentsBuilder
                .fromPath("people/" + id).build().toUri())
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(person.map(p ->
                        new Person(p, id))
                        .flatMap(personManager::save), Person.class));
    }

    public Mono<ServerResponse> put (ServerRequest request) {
        final UUID id = UUID.fromString(request.pathVariable("id"));
        final Mono<Person> person = request.bodyToMono(Person.class);


        return personManager
                .findById(id)
                .flatMap(old ->
                    ok().contentType(MediaType.APPLICATION_JSON)
                        .body(fromPublisher(person.map(p-> new Person(p, id))
                                .flatMap(p -> personManager.update(old,p))
                                , Person.class))
                ).switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> delete (ServerRequest request) {
        final UUID id = UUID.fromString(request.pathVariable("id"));
        return personManager
                .findById(id)
                .flatMap(p -> noContent().build(personManager.delete(p)));
    }

    public Mono<ServerResponse> getByCountry(ServerRequest serverRequest) {
        final String country = serverRequest.pathVariable("country");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(personManager.findAllByCountry(country), Person.class));
    }
}
