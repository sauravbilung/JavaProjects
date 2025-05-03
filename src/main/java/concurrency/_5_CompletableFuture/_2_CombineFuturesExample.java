package concurrency._5_CompletableFuture;

import java.util.concurrent.*;

public class _2_CombineFuturesExample {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Future 1: returns a string after delay
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            System.out.println("Future 1 done by : " + Thread.currentThread().getName());
            return "Hello";
        }, executor);

        // Future 2: returns another string after delay
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(1500);
            System.out.println("Future 2 done by : " + Thread.currentThread().getName());
            return "World";
        }, executor);

        CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (res1, res2) -> {
            System.out.println("Combining on thread : " + Thread.currentThread().getName());
            return res1 + " " + res2;
        });

        // Wait and get the result
        String result = combinedFuture.get(); // or combinedFuture.join();

        System.out.println("Combined Result: " + result); // Output: Hello World
        executor.shutdown();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }
}

/*
* We are explicitly passing a custom thread pool (executor) to supplyAsync(), which means:

* The task will run on a thread from the fixed pool (Executors.newFixedThreadPool(2)).
We control threading (good for tuning concurrency, resources, avoiding the ForkJoinPool default).
*
* Note :
*  .thenCombineAsync(...) when combining logic is heavy.
*
* 1. With .thenCombine(...) : Combines the results synchronously in the thread of whichever future completes last.
* 2. With .thenCombineAsync(...) (no executor) : Combines results asynchronously in the common ForkJoinPool.
* 3. With .thenCombineAsync(..., executor) :  Combining logic is scheduled asynchronously on any available thread from the provided ExecutorService.
*
* If we use CompletableFuture.supplyAsync() without an executor, like this:
* */