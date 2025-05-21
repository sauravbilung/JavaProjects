package concurrency._2_SynchronizationAndLocks;

import java.util.concurrent.locks.StampedLock;

public class _12_StampedLockExample {
    private int counter = 0;
    private final StampedLock stampedLock = new StampedLock();

    public void increment() {
        long stamp = stampedLock.writeLock();
        try {
            counter++;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public int getCounter() {
        long stamp = stampedLock.tryOptimisticRead();
        int value = counter;
        if (!stampedLock.validate(stamp)) {
            // If someone wrote during read, fallback to read lock
            // Writers are blocked — no writeLock() calls will succeed until this read lock is released.
            stamp = stampedLock.readLock();
            try {
                value = counter;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return value;
    }

    public static void main(String[] args) throws InterruptedException {

        /*
        * 🔍 When to Use StampedLock:
        *   When reads vastly outnumber writes
        *   When you want to reduce contention and improve throughput vs. using traditional ReentrantReadWriteLock
        */

        _12_StampedLockExample example = new _12_StampedLockExample();

        Runnable writeTask = () -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        };

        Runnable readTask = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Read counter: " + example.getCounter());
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        };

        Thread writer = new Thread(writeTask);
        Thread reader = new Thread(readTask);

        writer.start();
        reader.start();

        writer.join();
        reader.join();

        System.out.println("Final Counter: " + example.getCounter());
    }
}
