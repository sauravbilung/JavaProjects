package concurrency._4_Coordination;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * A CyclicBarrier in Java is a synchronization aid that allows a set of threads to wait for each other to reach a common barrier point (i.e., a point in the code).
 * It's called cyclic because it can be reused after the waiting threads are released.
 *
 * 🔧 Key Concepts:
 * Part of java.util.concurrent package.
 * All threads wait at the barrier until a specified number of threads (parties) have called await().
 * Once the last thread arrives, all waiting threads are released simultaneously.
 * Can optionally execute a barrier action (a Runnable) once the last thread arrives.
 */
public class _2_CyclicBarrierExample {
    public static void main(String[] args) {
        // The second param is a barrier action(runnable) which is optional.
        CyclicBarrier barrier = new CyclicBarrier(3, () ->
            System.out.println("All threads reached barrier. Proceeding..."));

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " waiting at barrier");
            try {
                barrier.await(); // Wait for others
                System.out.println(Thread.currentThread().getName() + " passed barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
    }
}
