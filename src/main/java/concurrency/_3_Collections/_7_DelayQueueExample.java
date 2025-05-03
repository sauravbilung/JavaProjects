package concurrency._3_Collections;

import java.util.concurrent.*;

/**
 * Purpose - To simulate scheduled task execution—like a timer queue where tasks are processed after a delay.
 * DelayQueue - a special blocking queue where elements become available only after a certain delay has expired.
 * */
public class _7_DelayQueueExample {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();

        queue.put(new DelayedTask("task1", 3));
        queue.put(new DelayedTask("task2", 1));
        queue.put(new DelayedTask("task3", 2));

        while (!queue.isEmpty()) {
            DelayedTask task = queue.take(); // blocks until delay expires
            System.out.println("Executing: " + task);
        }
    }
}

class DelayedTask implements Delayed {
    private final long triggerTime; // stores the future time when the task becomes available.
    private final String taskName;

    public DelayedTask(String taskName, long delaySeconds) {
        this.taskName = taskName;
        this.triggerTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(delaySeconds);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // tells how much time is left before the task is available.
        return unit.convert(triggerTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        //ensures correct order in the queue (soonest delay first).
        return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return taskName;
    }
}