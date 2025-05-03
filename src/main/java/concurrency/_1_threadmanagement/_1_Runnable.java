package concurrency._1_threadmanagement;

public class _1_Runnable {

    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Thread running: " + Thread.currentThread().getName());
        };

        Thread thread = new Thread(task);
        thread.start();
    }
}

/*
* 🔹 Key Points:
1. Avoid extending Thread directly unless needed.
2. Runnable cannot return a value.
* */