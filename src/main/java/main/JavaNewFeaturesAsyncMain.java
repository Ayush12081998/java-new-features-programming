package main;

import service.AsyncComputationService;

import java.util.concurrent.*;

public class JavaNewFeaturesAsyncMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AsyncComputationService asyncComputationService = new AsyncComputationService();
//        Future<String> result = asyncComputationService.demonstrateUsageOfCompletableFutureAsFuture();
//        asyncComputationService.demonstrateSupplyAsync();
//        System.out.println(result.get());
        executors();
    }

    static void executors(){
        ExecutorService threadPoolExecutor=
                new ThreadPoolExecutor(2,4,5, TimeUnit.MINUTES
                        ,new ArrayBlockingQueue<>(2),
                        new CustomThreadPoolFactory(),new CustomRejectionHandler());

//        threadPoolExecutor.submit(()->{});
//        threadPoolExecutor.execute(()->{});
        //change i value to see if thread is utilized through maxPoolSize
        for(int i=0;i<7;i++){
            threadPoolExecutor.submit(()->{
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                }
                System.out.println("Task processed by: "+Thread.currentThread().getName());
            });
        }
        threadPoolExecutor.shutdown();
    }

    static class CustomThreadPoolFactory implements ThreadFactory{

        private static int COUNT=0;
        @Override
        public Thread newThread(Runnable r) {
            Thread thread= new Thread(r);
            thread.setPriority(Thread.NORM_PRIORITY);
            thread.setName("custom-thread-"+COUNT);
            thread.setDaemon(false);
            COUNT++;
            return thread;
        }
    }

    static class CustomRejectionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("Task rejected: "+ r.toString());
        }
    }
}
