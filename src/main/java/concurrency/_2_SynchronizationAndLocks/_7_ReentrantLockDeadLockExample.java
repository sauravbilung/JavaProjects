package concurrency._2_SynchronizationAndLocks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class _7_ReentrantLockDeadLockExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ReentrantDeadlock example = new ReentrantDeadlock();

        executorService.submit(example::methodA); // same as () -> example.methodA()
        executorService.submit(example::methodB); // same as () -> example.methodB()

        executorService.shutdown();
    }
}

class ReentrantDeadlock {
    private final ReentrantLock lockA = new ReentrantLock();
    private final ReentrantLock lockB = new ReentrantLock();

    public void methodA() {
        lockA.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired lockA.");
            Thread.sleep(100); // simulate work
            System.out.println(Thread.currentThread().getName() + " trying to acquire lockB...");
            lockB.lock(); // potential deadlock point
            try {
                System.out.println(Thread.currentThread().getName() + " acquired lockB.");
            } finally {
                lockB.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lockA.unlock();
        }
    }

    public void methodB() {
        lockB.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired lockB.");
            Thread.sleep(100); // simulate work
            System.out.println(Thread.currentThread().getName() + " trying to acquire lockA...");
            lockA.lock(); // potential deadlock point
            try {
                System.out.println(Thread.currentThread().getName() + " acquired lockA.");
            } finally {
                lockA.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lockB.unlock();
        }
    }
}
