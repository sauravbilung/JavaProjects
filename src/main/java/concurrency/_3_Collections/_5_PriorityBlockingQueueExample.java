package concurrency._3_Collections;

import java.util.concurrent.PriorityBlockingQueue;

public class _5_PriorityBlockingQueueExample {
    static class Task implements Comparable<Task> {
        int priority;
        String name;

        Task(int priority, String name) {
            this.priority = priority;
            this.name = name;
        }

        @Override
        public int compareTo(Task other) {
            return Integer.compare(other.priority, this.priority); // max heap
        }

        @Override
        public String toString() {
            return name + " (priority " + priority + ")";
        }
    }

    public static void main(String[] args) {
        // max heap type PriorityBlockingQueue.
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        queue.add(new Task(2, "Low"));
        queue.add(new Task(10, "High"));
        queue.add(new Task(5, "Medium"));

        while (!queue.isEmpty()) {
            System.out.println("Processing: " + queue.poll());
        }
    }
}

/*
* ✅ General Rule to Use PriorityBlockingQueue:

PriorityBlockingQueue<E> requires elements of type E that:
    either implement Comparable<E>
    or you provide a Comparator<E> at queue creation
    So whether it's a Task, Runnable, or Wrapper<Task> — it must be comparable or sortable.
*/