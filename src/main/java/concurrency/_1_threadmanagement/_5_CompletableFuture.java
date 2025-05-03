package concurrency._1_threadmanagement;

import java.util.concurrent.CompletableFuture;

public class _5_CompletableFuture {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Result 1";
        }).thenApply(result -> {
            return result + " → Transformed";
        }).thenAccept(finalResult -> {
            System.out.println("Final Output: " + finalResult);
        });

        sleep(2000); // wait for async to complete
    }

    static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}

/*
* 🔹 Key Points:
Non-blocking, chainable, great for event-driven apps.
Can combine multiple tasks (thenCombine, allOf, etc).
*/