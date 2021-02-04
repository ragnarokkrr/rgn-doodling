package baeldung;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CF06CompbinigFutures {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //
        final var stringCompletableFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(
                        CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);

        System.out.println("Res: " + stringCompletableFuture.get());
    }
}
