package ragna.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Error {
  public static void main(String[] args) {
/*
    String v1;
    try{
      v1 = callExternalService("key1");
    } catch (Throwable error) {
      v1 = getFromCache("key1");
    }
    String v2;
    try{
      v2 = callExternalService("key2");
    } catch (Throwable error) {
      v2 = getFromCache("key2");
    }
    System.out.println("v1: " + v1 + " v2: " + v2);

*/

    Flux<String> stringFlux = Flux.just("key1", "key2")
        .flatMap(k -> callExternalService(k)
            .onErrorResume(e -> getFromCache(k)));
    stringFlux.subscribe(s -> System.out.println("============================\n" + s));

    //staticFallbackValue();

    // firstHandleExample();

    // simpleHandling();

    //
  }

  private static Mono<String> getFromCache(String key) {
    return Mono.just("CACHED: " + key);
  }

  private static Mono<String> callExternalService(String key) {
    if(key.endsWith("2")) {
      throw new RuntimeException("OMG" + key);
    }
    return Mono.just("Value: " + key);
  }

  private static void staticFallbackValue() {
    Flux<String> stringFlux =
        Flux.just(5).map(Error::doSomethingDandegrous).onErrorReturn("RECOVERED");

    stringFlux.subscribe(
        value -> System.out.println("OK " + value), error -> System.err.println("ERROR " + error));

    Flux<String> stringFlux1 =
        Flux.just(5)
            .map(Error::doSomethingDandegrous)
            .onErrorReturn(e -> e.getMessage().equals("OMG5"), "recovered5");

    stringFlux1.subscribe(
        value -> System.out.println("OK " + value), error -> System.err.println("ERR " + error));

    String v1 = null;
    try {
      v1 = doSomethingDandegrous(5);
    } catch (Throwable error) {
      v1 = "RECOVERED";
    }
    System.out.println(v1);
  }

  private static void firstHandleExample() {
    Flux<String> s =
        Flux.range(1, 10).map(Error::doSomethingDandegrous).map(Error::doSecondTransform);

    s.subscribe(
        value -> System.out.println("RECEIVED  " + value),
        error -> System.err.println("CAUGHT " + error));
    try {
      for (int i = 1; i < 11; i++) {
        String v1 = doSomethingDandegrous(i);
        String v2 = doSecondTransform(v1);
        System.out.println("RECEIVED " + v2);
      }
    } catch (Throwable t) {
      System.err.println("CAUGHT " + t);
    }
  }

  private static String doSecondTransform(String v1) {
    return "Res 2 -> " + v1;
  }

  private static String doSomethingDandegrous(int i) {
    if (i == 5) {
      throw new IllegalArgumentException("OMG" + i);
    }

    return "Res: " + i;
  }

  private static void simpleHandling() {
    Flux<String> stringFlux =
        Flux.just(1, 2, 0)
            .map(i -> "100 / " + i + " = " + (100 / i))
            .onErrorReturn("Divided by zer0 :(");

    stringFlux.subscribe(System.out::println);
  }
}
