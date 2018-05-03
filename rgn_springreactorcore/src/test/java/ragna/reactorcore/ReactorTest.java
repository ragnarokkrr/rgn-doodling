package ragna.reactorcore;



import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ReactorTest {

    @Test
    public void producingStream(){
        Flux<String> just = Flux.just("1", "2", "3");

        Mono<String> just2 = Mono.just("foo");

        Publisher<String> pub1 = just;

        Publisher<String> pub2 = just2;

    }


    @Test
    public void subscribingToStream(){
        List<Integer> elements = new ArrayList<>();

        Flux.just(1,2,3,4)
                .log()
                .subscribe(elements::add);

        assertThat(elements).containsExactly(1, 2, 3, 4);
    }

    @Test
    public void substribingSubscriber(){
        List<Integer> elements = new ArrayList<>();

        Flux.just(1,2,3,4)
                .log()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Test
    public void backPressure(){
        List<Integer> elements = new ArrayList<>();
        Flux.just(1,2,3,4)
                .log()
                .subscribe(new Subscriber<Integer>() {
                    private Subscription s;
                    int onNextAmount;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        //Backpressure is when a downstream can tell an upstream to send it fewer data in order
                        // to prevent it from being overwhelmed.
                        elements.add(integer);
                        if (onNextAmount % 2 == 0) {
                            s.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testMap() {
        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .map( i -> i * 2)
                .subscribe(elements::add);

        assertThat(elements).containsExactly(2, 4, 6, 8);
    }

    @Test
    public void testCombineStreams(){
        List<String> elements = new ArrayList<>();

        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .zipWith(Flux.range(0, Integer.MAX_VALUE),
                        (two, one) -> String.format("First Flux: %d, Second Flux: %d", one, two))
                .subscribe(elements::add);
        assertThat(elements).containsExactly(
          "First Flux: 0, Second Flux: 2",
          "First Flux: 1, Second Flux: 4",
          "First Flux: 2, Second Flux: 6",
          "First Flux: 3, Second Flux: 8"
        );
    }

    @Test
    public void infiniteStremaConnectableFluxThrottling(){
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
            while(true) {
                fluxSink.next(System.currentTimeMillis());
            }
        }).sample(ofSeconds(2))
                .publish();

        publish.subscribe(System.out::println);
        publish.subscribe(System.out::println);
        publish.connect();
    }

    @Test
    public void concurrent(){
        List<Integer> elements = new ArrayList<>();

        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .subscribeOn(Schedulers.parallel())
                .subscribe(elements::add);

        assertThat(elements).containsExactly(2, 4, 6, 8);
    }
}
