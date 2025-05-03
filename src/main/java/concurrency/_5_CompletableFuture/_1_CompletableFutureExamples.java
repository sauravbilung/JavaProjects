package concurrency._5_CompletableFuture;// Java CompletableFuture - Complete Guide for Interviews and Real Projects

import java.util.concurrent.*;

/**
 * CompletableFuture is part of java.util.concurrent and represents a future result of an asynchronous computation.
 **/
public class _1_CompletableFutureExamples {

    // 1. Basic Async Supply
    public static void basicAsync() {
        // supplyAsync() is for tasks that return a result.
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "Hello";
        });

        //thenAccept() is a callback that runs when the task completes.
        future.thenAccept(result -> System.out.println("Result: " + result));
        sleep(1000);
    }

    public static void basicAsyncWithNoReturn() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Running in background");
        });
    }

    // 2. Chaining with thenApply
    public static void chainingThenApply() {
        // thenApply() – Transform the result
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Java")
                .thenApply(name -> name + " Concurrency")
                .thenApply(String::toUpperCase);

        future.thenAccept(System.out::println);
        sleep(1000);
    }

    // 3. thenCombine - Combine Two Futures
    public static void combineTwoFutures() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Completable");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "Future");

        f1.thenCombine(f2, (s1, s2) -> s1 + " " + s2)
                .thenAccept(System.out::println);

        sleep(1000);
    }

    // 4. thenCompose - Flatten Futures (Chaining dependent tasks)
    public static void composeFutures() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Java")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Developer"));

        future.thenAccept(System.out::println);
        sleep(1000);
    }

    // 5. allOf - Wait for multiple futures
    public static void allOfExample() {
        CompletableFuture<Void> all = CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> sleep(100)),
                CompletableFuture.runAsync(() -> sleep(200)),
                CompletableFuture.runAsync(() -> sleep(300))
        );

        all.thenRun(() -> System.out.println("All tasks completed"));
        sleep(500);
    }

    // 6. anyOf - Fastest result wins
    public static void anyOfExample() {
        CompletableFuture<Object> fastest = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> {
                    sleep(300);
                    return "Slow";
                }),
                CompletableFuture.supplyAsync(() -> {
                    sleep(100);
                    return "Fast";
                })
        );

        fastest.thenAccept(System.out::println);
        sleep(500);
    }

    // 7. Exception Handling with exceptionally
    public static void exceptionHandling() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("Boom!");
            return "Success";
        }).exceptionally(ex -> {
            System.out.println("Handled error: " + ex);
            return "Default";
        });

        future.thenAccept(System.out::println);
        sleep(500);
    }

    // 8. Handle both success and failure
    public static void handleExample() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("Failed");
            return "Success";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println("Error: " + ex);
                return "Fallback";
            } else {
                return result;
            }
        });

        future.thenAccept(System.out::println);
        sleep(500);
    }

    public static void main(String[] args) {
        basicAsync();
        chainingThenApply();
        combineTwoFutures();
        composeFutures();
        allOfExample();
        anyOfExample();
        exceptionHandling();
        handleExample();
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}

/*
# Summary for Interview

| Concept              | Method Used           | Purpose                                        |
|---------------------|------------------------|------------------------------------------------|
| Basic Async Task    | supplyAsync            | Run async code and return a result             |
| Chaining            | thenApply              | Transform the result                          |
| Compose Tasks       | thenCompose            | Flatten dependent future                       |
| Combine Futures     | thenCombine            | Combine two independent futures                |
| Wait All            | allOf                  | Wait for all tasks to complete                 |
| Wait Any            | anyOf                  | Get the first task to complete                 |
| Handle Exception    | exceptionally, handle  | Recover from errors                            |
*/
