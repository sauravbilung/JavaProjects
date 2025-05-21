package concurrency._1_threadmanagement;

import java.util.concurrent.*;

public class _3_SubmittingMultipleTasks {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
            });
        }

        Future<String> future = executor.submit(new CallableTask());
        executor.submit(new RunnableTask());

        String callableResult = future.get();
        System.out.println(callableResult);
        executor.shutdown();
    }
}

class CallableTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        String result = String.format("Callable executed task 6 by %s", Thread.currentThread().getName());
        return result;
    }
}

class RunnableTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Executing task 7 by " + Thread.currentThread().getName());
    }
}

/*
* Key Points:
1. Reuses a pool of threads → more efficient than creating new ones.
2. Use .shutdown() to stop accepting new tasks.
*
* Future<?> submit(Runnable task) - Executes Runnable task at some point in future and returns Future representing task.
*
* <T> Future<T> submit(Callable<T> task) - Executes Callable task at some point in future and returns Future representing pending
* results of task.
* */

