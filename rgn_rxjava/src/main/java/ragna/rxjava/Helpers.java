package ragna.rxjava;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class Helpers {

  public static <T> Disposable subscribePrint(Observable<T> observable, String name) {
    return observable.subscribe(
        (v) -> System.out.println(name + " : " + v),
        (e) -> {
          System.err.println("Error from " + name + ":");
          System.err.println(e.getMessage());
        },
        () -> System.out.println(name + " ended!"));
  }
}
