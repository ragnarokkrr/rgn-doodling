package ragna.reactorcore;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Test01 {

    public static void main(String[] args) {
        Flux<String> just = Flux.just("1", "2", "3");


        Mono<String> just2 = Mono.just("foo");

        Publisher<String> pub1 = just;

        Publisher<String> pub2 = just2;

    }
}
