package ragna.springwebfluxcass.person.web;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PersonRouter {

    @Bean
    public RouterFunction<ServerResponse> route(PersonHandler personHandler) {
        return RouterFunctions.route(
            GET("/people/{id}").and(accept(APPLICATION_JSON))
                , personHandler::get)
            .andRoute(GET("/people").and(accept(APPLICATION_JSON))
                    , personHandler::all)
            .andRoute(POST("/people").and(accept(APPLICATION_JSON).and(contentType(APPLICATION_JSON)))
                    , personHandler::post)
            .andRoute(PUT("/people/{id}").and(accept(APPLICATION_JSON).and(contentType(APPLICATION_JSON)))
                    , personHandler::put)
            .andRoute(DELETE("/peopler/{id}"), personHandler::delete)
            .andRoute(GET("/people/country/{country}").and(accept(APPLICATION_JSON))
                    , personHandler::getByCountry)
                ;
    }
}
