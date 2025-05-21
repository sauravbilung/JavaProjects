package concurrency._5_CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

/*
 * Demo code to show flattening chained task.
 * Tasks to perform :
 * Step 1. Fetch user
 * Step 2. Fetch details for that user
 */
public class _4_DependentTasks {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<List<String>> future = getUserAsync().thenCompose(user -> getOrdersForUser(user.userId()));

        future.thenAccept(orders -> {
            for (String order : orders) {
                System.out.println(order);
            }
        });

        sleep(1000);
    }

    private static CompletableFuture<User> getUserAsync() {
        return CompletableFuture.supplyAsync(() -> new User("007", "saurav"));
    }

    private static CompletableFuture<List<String>> getOrdersForUser(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                sleep(100);
                return List.of("Order-1 for " + userId, "Order-2 for " + userId);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

record User(String userId, String name) {
}