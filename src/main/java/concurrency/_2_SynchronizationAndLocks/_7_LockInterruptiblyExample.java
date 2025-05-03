package concurrency._2_SynchronizationAndLocks;

import java.util.concurrent.locks.ReentrantLock;

/*
* Goal : To show how a thread waiting for a lock can be safely interrupted and stop waiting
*/
public class _7_LockInterruptiblyExample {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " trying to acquire lock...");
                lock.lockInterruptibly(); // Wait, but respond to interrupt
                try {
                    System.out.println(Thread.currentThread().getName() + " acquired lock.");
                    Thread.sleep(3000); // simulate work
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " released lock.");
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted while waiting for lock.");
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start(); // t1 starts and acquires the lock
        Thread.sleep(100); // main sleeps 100ms to let t1 grab the lock before t2 starts
        t2.start(); // t2 starts and tries lockInterruptibly(), but gets BLOCKED

        Thread.sleep(500);  // main thread now waits so that t2 has time to enter the blocked state
        t2.interrupt();    // main interrupts t2 while it is BLOCKED waiting on the lock
    }
}

/*
* If we use lock() instead of lockInterruptibly(), then a thread trying to acquire the lock will be blocked indefinitely — even if it's interrupted.
*
* 🔒 lock() behavior:
It ignores Thread.interrupt() while waiting.
//The thread stays in the "waiting for lock" state. Blocked state.
No InterruptedException is thrown.
Only once the lock becomes available will the thread proceed.
*
* ✅ lockInterruptibly() is different:
If the thread is interrupted while waiting for lock, it:
Immediately stops waiting
Throws InterruptedException
We can handle it (e.g., log, exit thread, etc.)
*
*
* | Thread State      | When it Happens                             | Waiting For                             | Can Be Interrupted?                           | Common Methods That Cause It           |
|---------------------|---------------------------------------------|-----------------------------------------|-----------------------------------------------|------------------------------------------|
| **BLOCKED**         | Waiting to acquire a **monitor/lock**       | Another **thread's lock**               | ❌ Only `lockInterruptibly()` is interruptible | `synchronized`, `lock()`                |
| **WAITING**         | Waiting for a **signal or notification**    | **Another thread's action**, not a lock | ✅ Yes                                         | `Object.wait()`, `Thread.join()`, `LockSupport.park()` |
*
*
* 🔒 Lock-related operations put a thread in the BLOCKED state if the lock is unavailable.
*
* | Operation                         | When BLOCKED?                                |
|----------------------------------|-----------------------------------------------|
| `synchronized (obj)`             | If another thread holds the monitor on `obj`  |
| `ReentrantLock.lock()`           | If another thread holds the lock              |
| `ReentrantLock.lockInterruptibly()` | Same as above (but can respond to interrupt) |
| `ReentrantLock.tryLock()`        | ❌ Never blocks (returns immediately)         |
*
*/