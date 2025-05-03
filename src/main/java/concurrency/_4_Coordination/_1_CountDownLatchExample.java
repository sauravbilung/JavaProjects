package concurrency._4_Coordination;

import java.util.concurrent.CountDownLatch;

/**
 * A main thread waits until N other threads complete.
 *
 * CountDownLatch is a synchronization aid in Java that allows one or more threads to wait until a set of operations being performed in other threads completes.
 *
 * It is initialized with a count.
 * Threads call await() to wait until the count reaches zero.
 * Other threads call countDown() to decrement the count.
 * Once the count reaches zero, all waiting threads are released.
 *
 **/
public class _1_CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3); // Wait for 3 tasks

        Runnable worker = () -> {
            System.out.println(Thread.currentThread().getName() + " started work");
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            System.out.println(Thread.currentThread().getName() + " finished work");
            latch.countDown(); // Decrement count
        };

        for (int i = 0; i < 3; i++) {
            new Thread(worker).start();
        }

        System.out.println("Main thread waiting for workers...");
        latch.await(); // Main thread waits
        System.out.println("All workers done. Main thread continues.");
    }
}
