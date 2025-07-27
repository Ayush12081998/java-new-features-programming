package main;

import service.AsyncComputationService;

import java.util.concurrent.ExecutionException;

public class JavaNewFeaturesAsyncMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AsyncComputationService asyncComputationService = new AsyncComputationService();
//        Future<String> result = asyncComputationService.demonstrateUsageOfCompletableFutureAsFuture();
        asyncComputationService.demonstrateSupplyAsync();
//        System.out.println(result.get());
    }
}
