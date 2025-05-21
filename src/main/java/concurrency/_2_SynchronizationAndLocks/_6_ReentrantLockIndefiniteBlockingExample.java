package concurrency._2_SynchronizationAndLocks;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class _6_ReentrantLockIndefiniteBlockingExample {

    public static void main(String[] args) {
        IncompleteUnlockExample example = new IncompleteUnlockExample();

        var executorService = Executors.newFixedThreadPool(2);

        executorService.submit(example::methodA);
        executorService.submit(example::methodB);

        executorService.shutdown();
    }
}

class IncompleteUnlockExample {

    ReentrantLock lock = new ReentrantLock();

    public void methodA() {
        if (lock.tryLock()) { // acquired lock
            lock.lock(); // acquired same lock again
            try {
                System.out.println(Thread.currentThread().getName() + " acquired lock for methodA");
                System.out.println("Hold count after locking: " + lock.getHoldCount());
            } finally {
                // Acquired lock twice but release only once.
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " could not acquired lock for methodA");
        }
    }

    public void methodB() {
        lock.lock(); // Requests lock and blocks until lock is acquired.
        try {
            System.out.println(Thread.currentThread().getName() + " acquired lock for methodB");
        } finally {
            // best practice is to always unlock with finally
            lock.unlock();
        }
    }
}