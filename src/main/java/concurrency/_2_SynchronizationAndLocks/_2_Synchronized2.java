package concurrency._2_SynchronizationAndLocks;

public class _2_Synchronized2 {

    public static void main(String[] args) {
        // Example to show synchronization in Java is always on an object
        SyncTest test = new SyncTest();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting for monitor lock to access methodA");
                test.methodA();
                System.out.println(Thread.currentThread().getName() + " is waiting for monitor lock to access methodB");
                test.methodB();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting for monitor lock to access methodB");
                test.methodB();
                System.out.println(Thread.currentThread().getName() + " is waiting for monitor lock to access method");
                test.methodA();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}

class SyncTest {
    public synchronized void methodA() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " acquired monitor lock and is accessing methodA");
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + " released monitor lock");
    }

    public synchronized  void methodB() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " acquired monitor lock and is accessing methodB");
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + " released monitor lock");
    }
}

/*
* 🔑 Key Concepts
* In Java, when a method is marked synchronized, the thread needs to acquire the intrinsic lock (monitor) of the object before it can execute that method.
* Both methodA() and methodB() are synchronized instance methods, so they lock on the same object, i.e., the test object of class SyncTest.
*
* Note : Whether Thread-1 or Thread-2 gets the lock after it's released is non-deterministic — depends on OS scheduler.
* But while one thread holds it, others just wait in Blocked state.
* */