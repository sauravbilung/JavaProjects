package concurrency._3_Collections;

import java.util.concurrent.ConcurrentLinkedQueue;

public class _3_ConcurrentLinkedQueueExample {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        queue.offer("Task1");
        queue.offer("Task2");

        System.out.println("Polled: " + queue.poll());
        System.out.println("Next: " + queue.peek());
    }
}

/*
* ConcurrentLinkedQueue is a non-blocking, lock-free (based on CAS – Compare-And-Swap), thread-safe FIFO (First-In-First-Out) queue from the java.util.concurrent package.
*
* 🔍 BlockingQueue vs ConcurrentLinkedQueue
*
| Feature                 | `BlockingQueue`                      | `ConcurrentLinkedQueue`                 |
| ----------------------- | ------------------------------------ | --------------------------------------- |
| ✅ Thread-safe           | ✅ Yes                                | ✅ Yes                                   |
| 🔄 FIFO order           | ✅ Yes (usually)                      | ✅ Yes                                   |
| 🧱 Blocking methods     | ✅ Yes (e.g. `put()`, `take()`)       | ❌ No (non-blocking only)                |
| 🚫 Non-blocking methods | ✅ Yes (`offer()`, `poll()`)          | ✅ Yes (`offer()`, `poll()`)             |
| ⏳ Waits if empty/full   | ✅ Yes                                | ❌ No                                    |
| ⚖️ Capacity             | ✅ Can be **bounded** or unbounded    | ❌ Always unbounded                      |
| 🔧 Backing structure    | Usually **array** or **linked list** | Lock-free **linked list**               |
| ⚙️ Use case             | **Producer-consumer** scenarios      | **High-throughput non-blocking queues** |
| 🧠 Underlying mechanism | Uses **locks + condition variables** | Uses **lock-free CAS algorithm**        |


* ⚠️ Important Differences in Behavior
*
| Behavior                      | `BlockingQueue` | `ConcurrentLinkedQueue`   |
| ----------------------------- | --------------- | ------------------------- |
| If empty                      | `take()` blocks | `poll()` returns `null`   |
| If full (for bounded)         | `put()` blocks  | N/A (no size restriction) |
| Safe iteration during changes | ❌ No            | ✅ Yes (weakly consistent) |
*/