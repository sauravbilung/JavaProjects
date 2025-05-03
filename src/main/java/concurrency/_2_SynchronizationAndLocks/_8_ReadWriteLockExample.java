package concurrency._2_SynchronizationAndLocks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class _8_ReadWriteLockExample {
    private int counter = 0;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void increment() {
        rwLock.writeLock().lock();
        try {
            counter++;
            // Simulate some work
            Thread.sleep(1);
        } catch (InterruptedException ignored) {
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public int getCounter() {
        rwLock.readLock().lock();
        try {
            return counter;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        _8_ReadWriteLockExample example = new _8_ReadWriteLockExample();

        // Writer thread (write 100 times)
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                example.increment();
            }
        });

        // Multiple reader threads
        Runnable readTask = () -> {
            for (int i = 0; i < 50; i++) {
                int value = example.getCounter();
                System.out.println(Thread.currentThread().getName() + " read: " + value);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ignored) {}
            }
        };

        // Launching 5 concurrent readers
        Thread[] readers = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new Thread(readTask, "Reader-" + i);
            readers[i].start();
        }

        writer.start();

        // Join all threads
        for (Thread reader : readers) {
            reader.join();
        }
        writer.join();

        System.out.println("Final counter: " + example.getCounter());
    }
}


/*
🔒 ReentrantReadWriteLock
Provides two types of locks:
Read Lock (readLock()) – shared, multiple threads can hold it simultaneously as long as no thread holds the write lock.
Write Lock (writeLock()) – exclusive, only one thread at a time can hold it, and no other thread can read or write during this time.

🔍 Real-World Motivation
In many real systems:
  -- Reads are frequent (e.g., fetching data from cache or DB)
  -- Writes are rare (e.g., updating user profile)
  -- Using a traditional synchronized or ReentrantLock:

Even reading blocks all other threads (i.e., no concurrent reading allowed)
  -- Performance suffers under high read load
  -- Using ReentrantReadWriteLock:
  -- Multiple threads can safely read concurrently

Only blocks if a write is in progress
  -- Write locks still guarantee exclusive access
*/
