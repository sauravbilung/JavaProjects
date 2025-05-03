package concurrency._5_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class _3_Suppliers {
    public static void main(String[] args) {
        HeavyTask task = new HeavyTask();
        
        CompletableFuture<String> future = CompletableFuture.supplyAsync(task);

        future.thenAccept(result -> System.out.println("Result: " + result));

        // Prevent main thread from exiting early
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
    }
}


class HeavyTask implements Supplier<String> {
    @Override
    public String get() {
        // Simulate heavy task
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        return "Heavy computation done!";
    }
}

