package concurrency._1_threadmanagement;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class _4_SubmittingMultipleTasks2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = Arrays.asList(
                () -> {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " - Task 1 completed");
                    return "Task 1 completed";
                },
                () -> {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " - Task 2 completed");
                    return "Task 2 completed";
                },
                () -> {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " - Task 3 completed");
                    return "Task 3 completed";
                },
                new CallableTask2()
        );

        // ---- invokeAll(): Waits for all tasks ----
        System.out.println("Executing invokeAll()...");
        List<Future<String>> futures = executor.invokeAll(tasks);

        System.out.println("Printing futures of invokeAll ...");
        for (Future<String> future : futures) {
            System.out.println("Result: " + future.get());  // blocks until result is ready
        }

        // ---- invokeAny(): Returns result of the fastest task ----
        System.out.println("\nExecuting invokeAny()...");
        String result = executor.invokeAny(tasks);  // returns result of one completed task
        System.out.println("First completed result: " + result);

        executor.shutdown();
    }
}

class CallableTask2 implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " - Task 4 completed");
        return "Task 4 completed.";
    }
}

/*
*
* <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
* - Executes given tasks and waits for all tasks to complete. Returns List of Future instances in same order in which they
* were in original collection.
*
* <T> T invokeAny(Collection<? extends Callable<T>> tasks)
* - Executes given tasks and waits for at least one to compute.
* */
