package concurrency._2_SynchronizationAndLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _4_ReentrantLockExample {

    private int counter = 0;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // If lock is not available, OS puts thread to sleep until it's notified
        try {
            counter++;
        } finally {
            lock.unlock(); // Always unlock in finally block
        }
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {
        _4_ReentrantLockExample example = new _4_ReentrantLockExample();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Counter: " + example.getCounter()); // Expected ~2000
    }
}

/*
🔥 Notes:
Concept	                         Best Practice
Explicit Lock 	                 Must manually lock() and unlock()
Always unlock	                 In finally block to avoid deadlocks
Advantages over synchronized	 TryLock, fair locking, interruptible locking

✅ ReentrantLock allows much more flexibility than synchronized.
*/
