package service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AsyncComputationService {

    public Future<String> demonstrateUsageOfCompletableFutureAsFuture() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(5000);
            completableFuture.complete("Hello Bhai kya haal?");
            return null;
        });
        return completableFuture;
    }

    public Future<String> demonstrateSupplyAsync() {
        return CompletableFuture.supplyAsync(() -> "Hi Bhai kya haal hai?");
    }

    /*
        passing implementation of apply() method of Function to thenApply() method if we need result of completed stage as input of final result
        and returned future.get() == value returned by apply method passed to  thenApply()
    */
    public Future<String> demonstrateProcessingResultOfAsyncComputation() {

        return CompletableFuture
                .supplyAsync(() -> "Hi Bhai kya haal hai?")
                .thenApply(s -> s + " Sab badhiya?");
    }

    /*
       passing implementation of accept() method of Consumer to thenAccept() method if we don't need result of completed stage as input of final result
       future.get() == null
    */
    public Future<Void> demonstrateProcessingResultOfAsyncComputationVoidReturnValue() {

        return CompletableFuture
                .supplyAsync(() -> "Hi Bhai kya haal hai?")
                .thenAccept(result -> System.out.println("Result of computation : " + result));
    }

    /*
        passing implementation of run() method of Runnable to thenRun() method if we don't need result of completed stage as input of final result
        future.get()== null
     */
    public Future<Void> demonstrateProcessingResultOfAsyncComputationOutputOfComputationNotRequiredInResultAndVoidReturnValue() {

        return CompletableFuture
                .supplyAsync(() -> "Hi Bhai kya haal hai?")
                .thenRun(() -> System.out.println("Computation finished"));
    }

    public Future<String> demonstrateCombiningFutures() {
        return CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " " + "World"))
                .thenApply(res -> "kar do kuch bhi" + res);
    }

    public void demonstrateRunningMultipleFutureInParallel() {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);
        Stream.of(future1, future2, future3).map(CompletableFuture::join).collect(Collectors.toList());
    }
}
