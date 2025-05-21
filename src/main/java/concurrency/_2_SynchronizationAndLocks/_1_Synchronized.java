package concurrency._2_SynchronizationAndLocks;

public class _1_Synchronized {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedExample example = new SynchronizedExample();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                example.incrementBlock();
                // OR example.incrementMethod();
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

class SynchronizedExample {
    private int counter = 0;

    // Synchronization in Java is always on an object (either "this", any obj, or SomeClass.class).
    // synchronized means "lock this particular object" before proceeding.

    // Synchronized method
    public synchronized void incrementMethod() {
        counter++;
    }

    // Synchronized block
    public void incrementBlock() {
        synchronized (this) {
            counter++;
        }
    }

    public int getCounter() {
        return counter;
    }
}