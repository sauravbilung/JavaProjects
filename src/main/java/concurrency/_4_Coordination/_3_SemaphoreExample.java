package concurrency._4_Coordination;

import java.util.concurrent.Semaphore;

/**
 * A Semaphore in Java is a concurrency utility that controls access to a shared resource by using a set number of permits.
 * It allows multiple threads to access the resource up to a defined limit, unlike a lock which typically allows only one thread.
 *
 * 🧠 Key concepts :
 * Found in java.util.concurrent.
 * Semaphore(int permits) defines how many threads can access the resource simultaneously.
 * Threads call acquire() to get a permit, and release() when done.
 * If no permits are available, acquire() blocks until one is released.
 * Can be used for rate-limiting, connection pools, or resource access control.
 *
 */
public class _3_SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); // Allow max 2 threads

        Runnable task = () -> {
            try {
                semaphore.acquire(); // Wait if no permit
                System.out.println(Thread.currentThread().getName() + " got permit");
                Thread.sleep(2000); // Simulate work
                System.out.println(Thread.currentThread().getName() + " releasing permit");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release(); // Release permit
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }
    }
}

/*
* Semaphore controls concurrency level, not data safety. Unlike Lock, ReadWriteLock, or StampedLock which are designed for safe access to shared state,
* Semaphore is for access throttling, not mutual exclusion. If threads share data, extra synchronization is still needed.
*
* ✅ Is Semaphore Interruptible?
*
* Yes, Semaphore.acquire() is interruptible. If a thread is waiting on acquire() and is interrupted, it will throw InterruptedException.
* This makes Semaphore safer than synchronized in blocking situations — you can cancel waiting threads.
*
* ❌ Is Semaphore Reentrant?
*
* No, Semaphore is not reentrant. A thread cannot reacquire the same permit it already holds without releasing it first.
* If it does, it will block — even though it's the same thread.
* 🔥 Unlike ReentrantLock, Semaphore doesn't track thread ownership — so it doesn't "know" the same thread is acquiring again.
*
* If a method guarded by a semaphore calls another method guarded by the same semaphore, it will deadlock because Semaphore is not reentrant.
* Use ReentrantLock for nested locking. If using different semaphores/locks, always acquire them in a consistent order to avoid deadlocks.
*
*/
