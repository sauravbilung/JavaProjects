package concurrency._1_threadmanagement;

import java.util.concurrent.*;

public class _5_WorkingWithFutures {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> longRunningTask = () -> {
            Thread.sleep(3000); // simulate long task
            return "Completed Task";
        };

        Future<String> future = executor.submit(longRunningTask);

        System.out.println("Is task done? " + future.isDone());  // false

        try {
            // Try to get result with timeout
            System.out.println("Trying to get result with 1s timeout...");
            String result = future.get(1, TimeUnit.SECONDS); // should timeout
            System.out.println("Result: " + result);
        } catch (TimeoutException e) {
            System.out.println("TimeoutException: Task not completed in time.");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException: " + e.getMessage());
        }

        // Cancel the task (mayInterruptIfRunning = true)
        boolean cancelled = future.cancel(true);
        System.out.println("Was cancel called? " + cancelled);

        // Check states
        System.out.println("Is task cancelled? " + future.isCancelled());
        System.out.println("Is task done? " + future.isDone());

        // Try to get result again after cancellation
        try {
            System.out.println("Trying to get result after cancellation...");
            String result = future.get();  // throws CancellationException
            System.out.println("Result: " + result);
        } catch (CancellationException e) {
            System.out.println("CancellationException: Task was cancelled.");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException: " + e.getMessage());
        }

        executor.shutdown();
    }
}
