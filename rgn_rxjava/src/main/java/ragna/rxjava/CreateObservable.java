package ragna.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// https://github.com/meddle0x53/learning-rxjava/blob/724eadf5b0db988b185f8d86006d772286037625/src/main/java/com/packtpub/reactive/common/CreateObservable.java#L61
public class CreateObservable {

  public static ConnectableObservable<String> from(final InputStream stream) {
    return from(new BufferedReader(new InputStreamReader(stream)));
  }

  public static ConnectableObservable<String> from(final BufferedReader reader) {

    return Observable.create(
            (ObservableEmitter<String> emitter) -> {
              try {
                String line;

                while ((line = reader.readLine()) != null) {
                  if (line.equals("exit")) {
                    break;
                  }
                  emitter.onNext(line);
                }
              } catch (IOException e) {
                emitter.onError(e);
              }
              emitter.onComplete();
            })
        .publish();
  }
}
