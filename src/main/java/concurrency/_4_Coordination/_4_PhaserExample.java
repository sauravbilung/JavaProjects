package concurrency._4_Coordination;

import java.util.concurrent.Phaser;

/*
 * A Phaser is a Java concurrency utility that lets multiple threads wait at a barrier repeatedly across multiple phases.
 * It’s more flexible than CountDownLatch and CyclicBarrier, supporting dynamic registration of threads and multiple phases of synchronization.
 */
public class _4_PhaserExample {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3); // 3 threads to register

        Runnable task = () -> {
            for (int phase = 0; phase < 3; phase++) {
                System.out.println(Thread.currentThread().getName() + " is working in phase " + phase);
                try {
                    Thread.sleep(500); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                phaser.arriveAndAwaitAdvance(); // Wait for others
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
    }
}

/*
* 🔁 What's happening ?
*
* 3 threads are created.
* Each thread runs the task.
* The task has a loop of 3 steps (phases).
* After each phase, the thread waits at the Phaser barrier (arriveAndAwaitAdvance()).
* Only when all 3 threads reach the same phase, they all advance to the next phase together.
* */

/*
* 🧩 Real-World Use Cases:
Multi-phase algorithms (e.g., Map-Reduce style jobs)
Coordinating worker threads in simulation steps
Data processing pipelines with stages
Replacing nested barriers/latches in complex flows
* */