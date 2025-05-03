package concurrency._3_Collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class _2_LinkedBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Thread producer = new Thread(() -> {
            try {
                queue.put("Item 1");
                queue.put("Item 2");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                System.out.println("Consumed: " + queue.take());
                System.out.println("Consumed: " + queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}

/*
* BlockingQueue<E> is an interface from java.util.concurrent that supports:
* Thread-safe insertion and removal
* Automatic blocking if:
The queue is full on put()
The queue is empty on take()
*
* ✅ MOST POPULAR BLOCKINGQUEUES
* 1. ArrayBlockingQueue : Bounded (capacity fixed at construction), Fast and memory-efficient (array-backed), Used in fixed-size producer-consumer systems.
* Eg : BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
*
* 2. LinkedBlockingQueue : Optionally bounded (default: unbounded), Backed by linked nodes, Higher throughput due to separate locks for put/take.
* eg : BlockingQueue<String> queue = new LinkedBlockingQueue<>();
*
* 3. PriorityBlockingQueue : Unbounded, Orders elements by priority (natural or via Comparator), Doesn’t block producers — so watch for memory leaks!
* eg : BlockingQueue<Task> queue = new PriorityBlockingQueue<>();
*
* 4. DelayQueue : Unbounded, Elements must implement Delayed, Items can only be taken after delay has expired.
*  DelayQueue<DelayedTask> queue = new DelayQueue<>();
* 📌 Use When: Scheduling or token expiration.
*/