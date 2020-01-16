package ragna.rxjava.ch03;

import io.reactivex.Observable;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class From02 {

  public static void main(String[] args) {
    Path resources = Paths.get("src", "main", "resources");

    try(DirectoryStream<Path> dStream = Files.newDirectoryStream(resources)){
      var dirObservable = Observable.fromIterable(dStream);
      dirObservable.subscribe(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
