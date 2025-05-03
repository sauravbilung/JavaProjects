package concurrency._7_ForkJoinFramework;

import java.util.concurrent.*;

public class _1_ForkJoinSumExample {

    static class SumTask extends RecursiveTask<Long> {
        private final int[] nums;
        private final int start, end;
        private static final int THRESHOLD = 10;

        public SumTask(int[] nums, int start, int end) {
            this.nums = nums;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) sum += nums[i];
                return sum;
            }

            int mid = (start + end) / 2;
            SumTask left = new SumTask(nums, start, mid);
            SumTask right = new SumTask(nums, mid, end);

            left.fork();                      // run in some other worker thread (asynchronously)
            long rightResult = right.compute(); // compute right directly (optimizes local thread use), same thread
            long leftResult = left.join();    // wait for left result

            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) array[i] = i + 1;

        ForkJoinPool pool = new ForkJoinPool(); // Default pool = # of CPU cores
        SumTask task = new SumTask(array, 0, array.length);
        long result = pool.invoke(task);

        System.out.println("Sum: " + result);
    }
}
