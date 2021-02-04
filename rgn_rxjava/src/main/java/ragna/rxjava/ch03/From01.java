package ragna.rxjava.ch03;

import io.reactivex.Observable;
import java.util.List;

public class From01 {

  public static void main(String[] args) {
    var list = List.of("blue", "red", "green", "yellow", "orange", "cyan", "purple");

    var listObservable = Observable.fromIterable(list);
    listObservable.subscribe(
        color -> System.out.print(color + "|"), System.out::println, System.out::println);
  }
}

