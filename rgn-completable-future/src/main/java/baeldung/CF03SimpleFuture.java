package baeldung;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class CF03SimpleFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //

        final var stringCompletableFuture = calculateAsync();

        final var result = stringCompletableFuture.get();
        System.out.println(result);
    }

    private static CompletableFuture<String> calculateAsync() {
        var completableFuture = new CompletableFuture<String>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return "ok";
        });

        return completableFuture;
    }
}
