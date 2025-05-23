package concurrency._1_threadmanagement;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class _2_Callable {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "Result from thread: " + Thread.currentThread().getName();
        };

        Future<String> future = executor.submit(task);
        System.out.println("Waiting for result...");
        String result = future.get();  // blocking
        System.out.println("Received: " + result);

        executor.shutdown();
    }
}

/*
* 🔹 Key Points:
* 1. Callable can return a result or throw exceptions.
* 2. Future.get() blocks until result is available.
*
* ExecutorService : An interface that represents an advanced, flexible thread pool mechanism to manage and control thread execution.
* Executors : A utility class (java.util.concurrent.Executors) that provides factory methods to create different types of ExecutorService instances easily.
*
* In simple words:
* ExecutorService : To manage and control asynchronous task execution.
* Executors : 	To create instances of thread pools or executor services easily.
*
*
* Not used but for completion :
*
* Executor is an interface that defines a simple contract for executing Runnable tasks. The execution may be asynchronous, depending on the implementation.
* It has only one method : void execute(Runnable command);
*
* Whether the task is run asynchronously or not depends on the implementation:
*
* Example of synchronous execution:
* Executor directExecutor = command -> command.run();  // runs in caller's thread
*
* Example of asynchronous execution:
* Executor asyncExecutor = Executors.newSingleThreadExecutor();  // runs in separate thread
*
*/