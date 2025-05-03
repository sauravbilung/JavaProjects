package concurrency._1_threadmanagement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _3_SubmittingMultipleTasks {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}

/*
* Key Points:
1. Reuses a pool of threads → more efficient than creating new ones.
2. Use .shutdown() to stop accepting new tasks.
* */