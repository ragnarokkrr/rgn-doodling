package ragna.springwebfluxcass.client;

import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import ragna.springwebfluxcass.person.domain.service.Person;
import reactor.core.publisher.Mono;

public class Client {
    private WebClient client = WebClient.create("http://localhost:8080");

    public void doStuff() {
        // POST
        final Person record = new Person(UUID.randomUUID(), "John", "Doe", "UK", 50);
        final Mono<ClientResponse> postResponse =
                client.post()
                .uri("/people")
                .body(Mono.just(record), Person.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        postResponse
                .map(ClientResponse::statusCode)
                .subscribe(status -> System.out.println("POST: " + status.getReasonPhrase()));


        //GET
        client.get()
                .uri("/people/{id}", "a4f66fe5-7c1b-4bcf-89b4-93d8fcbc52a4")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(Person.class))
                .subscribe(person -> System.out.println("GET: " + person));

        //ALL
        client.get()
                .uri("/people")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMapMany(response -> response.bodyToFlux(Person.class));


        //PUT
        final Person updated = new Person(UUID.randomUUID(), "Peter", "Parker", "US", 18);

        client.put()
                .uri("/people/{id}", "ec2212fc-669e-42ff-9c51-69782679c9fc")
                .body(Mono.just(updated), Person.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .map(ClientResponse::statusCode)
                .subscribe(response -> System.out.println("PUT: " + response.getReasonPhrase()));

        //DELETE
        client.delete()
                .uri("/people/{id}", "ec2212fc-669e-42ff-9c51-69782679c9fc")
                .exchange()
                .map(ClientResponse::statusCode)
                .subscribe(status -> System.out.println("DELETE: " + status));


    }

}
