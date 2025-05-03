package concurrency._2_SynchronizationAndLocks;

public class _3_Deadlock {

    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    public static void main(String[] args) {
        // Each thread locks one object and then tries to lock another, causing circular waiting — this creates a deadlock.
        Thread thread1 = new Thread(() -> {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding Lock1...");

                try {
                    Thread.sleep(100); // Give Thread 2 time to lock Lock2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1: Waiting for Lock2...");
                synchronized (Lock2) {
                    System.out.println("Thread 1: Acquired Lock2!");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (Lock2) {
                System.out.println("Thread 2: Holding Lock2...");

                try {
                    Thread.sleep(100); // Give Thread 1 time to lock Lock1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: Waiting for Lock1...");
                synchronized (Lock1) {
                    System.out.println("Thread 2: Acquired Lock1!");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
