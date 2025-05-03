package concurrency._2_SynchronizationAndLocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class _5_TryLockExample {

    private final ReentrantLock lock = new ReentrantLock();

    public void accessResource() {
        boolean locked = false;
        try {
            // lock.tryLock() attempts to acquire the lock within 500 milliseconds. If the lock is acquired, locked becomes true. If not, it stays false.
            locked = lock.tryLock(500, TimeUnit.MILLISECONDS);
            if (locked) {
                System.out.println(Thread.currentThread().getName() + " acquired lock");
                Thread.sleep(1000); // Simulate work
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire lock");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        _5_TryLockExample example = new _5_TryLockExample();

        Runnable task = example::accessResource;

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }
}

/*
while (!lock.tryLock()) {
    // This is busy waiting — repeatedly tries acquiring the lock
    // Consumes CPU cycles unnecessarily
}

💡 Conclusion:
lock() is efficient and does not cause busy waiting. By using lock(). if lock is not available, OS puts thread to sleep until it's notified.
tryLock() is useful when you want to avoid blocking and possibly do something else if the lock isn't immediately available.

🔍 If tryLock() often skips the critical section… then why use it at all?
Because in many real-world scenarios, we don't want to block or wait if a resource is busy. We want to make a fast decision and move on.

✅ Real-World Scenarios Where tryLock() Is Exactly What You Need:
1. Deadlock Avoidance in Multi-Lock Scenarios
2. Responsive Systems (e.g., UI, Real-Time, Microservices) :
    -- In UI apps or event-driven systems: you don’t want to freeze the interface.
    -- If a resource is busy, do something else and check again later.
3. Timeouts with tryLock(time, unit) : wait for short time but not indefinitely.
*/