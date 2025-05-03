package concurrency._3_Collections;

import java.util.concurrent.PriorityBlockingQueue;

public class _6_RunnablePriorityBlockingQueueExample {

    // Wrapping Runnable in a RunnableTask with Priority
    static class RunnableTask implements Runnable {
        int priority;
        String name;

        public RunnableTask(int priority, String name) {
            this.priority = priority;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Running: " + name);
        }

        @Override
        public String toString() {
            return name + " (priority " + priority + ")";
        }
    }

    public static void main(String[] args) {
        PriorityBlockingQueue<RunnableTask> queue = new PriorityBlockingQueue<>(
            10,
            (a, b) -> Integer.compare(b.priority, a.priority) // max heap
        );

        queue.add(new RunnableTask(2, "Low"));
        queue.add(new RunnableTask(10, "High"));
        queue.add(new RunnableTask(5, "Medium"));

        while (!queue.isEmpty()) {
            RunnableTask task = queue.poll();
            System.out.println("Processing: " + task);
            task.run();
        }
    }
}
