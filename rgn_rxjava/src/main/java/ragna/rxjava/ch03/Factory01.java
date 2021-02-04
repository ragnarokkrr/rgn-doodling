package ragna.rxjava.ch03;

import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import ragna.rxjava.Helpers;

public class Factory01 {

  public static void main(String[] args) {
    //
    Helpers.subscribePrint(Observable.interval(500L, TimeUnit.MILLISECONDS), "Interval Observable");
  }
}
