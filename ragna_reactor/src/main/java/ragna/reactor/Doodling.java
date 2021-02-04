package ragna.reactor;

import java.util.List;
import reactor.core.publisher.Flux;

public class Doodling {

  public static void main(String[] args) {
    System.out.println();

    Flux<String> seq1 = Flux.just("foo", "bar", "foobar");

    List<String> iterable = List.of("foo", "bar", "foobar");

    Flux<String> seq2 = Flux.fromIterable(iterable);

    seq1.subscribe(s -> System.out.println("s = " + s));
    seq2.subscribe(s -> System.out.println("s2 = [" + s + "]"));
  }
}
