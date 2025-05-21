package concurrency._2_SynchronizationAndLocks;

/**
 * 🔵 Demonstrates reentrant locking behavior in Java
 * 🔵 Shows both: (1) same object lock (reentrant) and (2) different object locks (potential deadlock risk).
 *
 * Note: synchronized locks per-instance unless the method is static, then it locks on the Class object.
 */
public class _3_ReentrantExample {

    public static void main(String[] args) {
        // Lock on same object is reentrant.
        //syncOnSameObjectIsReentrant();

        // Lock on different object
       differentObjectMeansDifferentLocks();
    }

    private static void syncOnSameObjectIsReentrant() {
        ReentrantLockingExample example = new ReentrantLockingExample();
        // Thread t1 = new Thread(() -> example.methodA());
        Thread t1 = new Thread(example::methodA, "Thread-1"); // Equivalent to previous statement.
        t1.start();
    }

    private static void differentObjectMeansDifferentLocks() {
        DifferentObjDifferentLockExample ex1 = new DifferentObjDifferentLockExample();
        DifferentObjDifferentLockExample ex2 = new DifferentObjDifferentLockExample();
        ex1.method1(ex2); // ex1 calls method2 on ex2
    }

}

/**
 * 🔵 Thread-1 enters methodA(), acquires "this" object's lock.
 * 🔵 Inside methodA(), it calls methodB().
 * 🔵 Since methodB() also needs the same "this" lock, and thread already owns it, it re-enters without deadlock.
 * ✅ Correct reentrant behavior.
 *
 * Summary : In Java, synchronized methods and blocks on the same object can be nested freely because locks are reentrant.
 *
 * When we say "same object" — what exactly does it mean?
 *
 * 👉 It means the same class instance (same object in memory).
 * In Java, every object has its own intrinsic lock (monitor lock).
 * If two methods (or blocks) are synchronized on the same instance, it's considered the same lock.
 * So, when the thread already owns the lock of that specific object, it can re-enter.
 */
class ReentrantLockingExample {
    public synchronized void methodA() {
        System.out.println(Thread.currentThread().getName() + " - inside methodA");
        methodB(); // 🔥 Re-entering another synchronized method
    }

    public synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + " - inside methodB");
    }
}


/**
 * 🔵 method1() locks "this" object.
 * 🔵 Inside it, it calls method2() on other object.
 * 🔵 other has its own lock, so new lock acquisition happens.
 * 🔵 In multithreaded use, this could cause deadlocks if not managed carefully.
 * ✅ Correct for demonstrating different object locks.
 * */
class DifferentObjDifferentLockExample {
    public synchronized void method1(DifferentObjDifferentLockExample other) {
        other.method2(); // calling method2 on a different object
    }

    public synchronized void method2() {
        System.out.println("Inside method2");
    }
}

