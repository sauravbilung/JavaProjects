package concurrency._1_threadmanagement;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _6_ScheduledExecutorService {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Running at: " + System.currentTimeMillis());
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }
}

/*
* Key Points:
* schedule(), scheduleAtFixedRate(), scheduleWithFixedDelay()
* Ideal for polling, heartbeats, retries.
*
* 1. schedule() - Runs the task once after a given delay.
*
* syntax : ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit)
* command → the task you want to run
* delay → how long to wait before running it
* unit → in seconds, milliseconds, etc. eg : TimeUnit.SECONDS
*
*
* 2. scheduleAtFixedRate() - Runs the task repeatedly, starting after an initial delay, and then every fixed period (no matter how long the task takes).
*
* syntax : ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
* initialDelay → how long to wait before first execution
* period → how much time between start of one task and start of next
* If a task takes longer than the period, the next task starts immediately after the previous ends.
*
* 3. scheduleWithFixedDelay() - Runs the task repeatedly, starting after an initial delay, but waits a fixed time after one task finishes before starting the next.
*
* syntax : ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
* initialDelay → wait before first execution
* delay → wait after a task finishes before starting the next one
*
*
* Important :
*
* 🔍 Issue with scheduleAtFixedRate()
* scheduleAtFixedRate() creates and submits a new task at every fixed interval, regardless of whether the previous task has finished.
* This can lead to problems if each task consistently takes longer to execute than the interval between executions.
*
* In such cases:
* The executor keeps submitting new tasks.
* Tasks may accumulate in the queue or run in parallel (if thread pool allows).
* Over time, this can cause memory issues or even crash the program due to too many pending or active tasks.
*
* scheduledWithFixedDelay() method creates a new task only after the previous task has finished.
*
* But it doesn't mean scheduleAtFixedRate() is useless. We want to run some fixed task at every interval and we are sure that the task will complete within
* the interval. Eg: polling a sensor every hour.
*/