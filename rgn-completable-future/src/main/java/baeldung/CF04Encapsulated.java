package baeldung;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CF04Encapsulated {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    //

    final var stringCompletableFuture = CompletableFuture.supplyAsync(() -> "Hello");

    System.out.println("Hello: " + stringCompletableFuture.get());
  }
}
