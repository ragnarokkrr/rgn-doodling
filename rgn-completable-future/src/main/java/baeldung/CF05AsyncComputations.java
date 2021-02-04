package baeldung;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CF05AsyncComputations {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //

        final var stringCompletableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        final var asyncFuture = stringCompletableFuture.thenApply(s -> s + " World");

        System.out.println("Res: " + asyncFuture.get());


        // return value consumer

        final var voidCompletableFuture = stringCompletableFuture.thenAccept(s -> System.out.println("Computation returned: " + s));

        voidCompletableFuture.get();

        // no value needed

        final var voidCompletableFutureRunnable = stringCompletableFuture.thenRun(() -> System.out.println("Computation finished."));

        voidCompletableFutureRunnable.get();

    }
}
