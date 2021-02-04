import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Scratch {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("aaaaa");

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Scheduler scheduler = Schedulers.fromExecutor(executor);

        Supplier<Flux<Integer>> source = Scratch::cleanSource;
        //Supplier<Flux<Integer>> source = Scratch::sourceErrorInBeginning;
        //Supplier<Flux<Integer>> source = Scratch::sourceErrorInMiddle;


        Flux.interval(Duration.ofSeconds(1L))
                .doOnNext(tick -> System.out.println("====================================> TICK: " + tick))
                .flatMap(tick -> source.get()
                        .map(Scratch::times10)
                        .doOnNext(Scratch::processInt)
                        .log()
                        .onErrorResumeWith(throwable -> Scratch.handleErrorFromFluxAndComplete(throwable)
                        )
                )

                .publishOn(scheduler)
                .subscribe(
                        myInt -> System.out.println("VALOR " + myInt),
                        throwable -> System.out.println("ERRO " + throwable),
                        () -> System.out.println("Completed"),
                        subscription -> System.out.println("Subscribed"))

        ;
        TimeUnit.SECONDS.sleep(5L);
        executor.shutdown();
    }

    private static Integer times10(Integer myInt) {
        return myInt * 10;
    }

    static void processInt(int myInt) {
        System.out.println("processInt():" + myInt);
    }

    static Mono<Integer> handleErrorFromFluxAndComplete(Throwable t) {
        System.out.println("handleErrorFromFlux() throwable: " + t);
        return Mono.just(1)
                .doOnNext(inte -> {
                    System.out.println("handleErrorFromFlux() inte: " + inte);
                })
                ;
    }

    static Flux<Integer> cleanSource() {
        System.out.println("cleanSource!!! ");

        return Flux.fromStream(IntStream.range(0, 10).boxed());
    }

    static Flux<Integer> sourceErrorInMiddle() {
        System.out.println("sourceErrorInMiddle!!! ");

        return Flux.fromStream(IntStream.range(0, 10).boxed())
                .flatMap(myInt -> {
                    if (myInt == 3) {
                        return Mono.error(new IllegalStateException("Deu ruim: " + myInt));
                    }
                    return Mono.just(3);
                });
    }

    static Flux<Integer> sourceErrorInBeginning() {
        System.out.println("sourceErrorInBeginning!!! ");

        return Flux.error(new IllegalStateException("Error in beginnning"));
    }


}