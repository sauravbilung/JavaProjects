package concurrency._2_SynchronizationAndLocks;

import java.util.concurrent.locks.ReentrantLock;

public class _6_DeadlockAvoidance {

    private static final ReentrantLock lockA = new ReentrantLock();
    private static final ReentrantLock lockB = new ReentrantLock();

    public static void main(String[] args) {
        Runnable task1 = () -> {
            while (true) {
                if (lockA.tryLock()) {
                    try {
                        Thread.sleep(50); // simulate some work
                        if (lockB.tryLock()) {
                            try {
                                System.out.println("Thread-1 acquired both locks");
                                break;
                            } finally {
                                lockB.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lockA.unlock();
                    }
                }
                System.out.println("Thread-1: Couldn't get both locks, retrying...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable task2 = () -> {
            while (true) {
                if (lockB.tryLock()) {
                    try {
                        Thread.sleep(50); // simulate some work
                        if (lockA.tryLock()) {
                            try {
                                System.out.println("Thread-2 acquired both locks");
                                break;
                            } finally {
                                lockA.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lockB.unlock();
                    }
                }
                System.out.println("Thread-2: Couldn't get both locks, retrying...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}
