package ragna.rxjava.ch02;

import io.reactivex.Observable;
import java.util.regex.Pattern;
import ragna.rxjava.CreateObservable;

public class ReactiveSum {

  public static void main(String[] args) {
    //

    var input = CreateObservable.from(System.in);

    var a = varStream("a", input);
    var b = varStream("b", input);

    reactiveSum(a, b);

    input.connect();
  }

  public static void reactiveSum(Observable<Double> a, Observable<Double> b) {
    Observable.combineLatest(a, b, (x, y) -> x + y)
        .subscribe(
            sum -> System.out.println("update : a + b = " + sum),
            error -> {
              System.out.println("Got an error!");
              error.printStackTrace();
            },
            () -> System.out.println("Exiting..."));
  }

  public static Observable<Double> varStream(final String name, Observable<String> input) {
    var pattern = Pattern.compile("\\s*" + name + "\\s*[:|=]\\s*(-?\\d+\\.?\\d*)$");
    return input
        .map(pattern::matcher)
        .filter(m -> m.matches() && m.group(1) != null)
        .map(matcher -> matcher.group(1))
        .map(Double::parseDouble);
  }
}
