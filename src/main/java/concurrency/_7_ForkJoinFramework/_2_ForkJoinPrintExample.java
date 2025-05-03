package concurrency._7_ForkJoinFramework;

import java.util.concurrent.*;

public class _2_ForkJoinPrintExample {

    static class PrintTask extends RecursiveAction {
        private final int[] nums;
        private final int start, end;
        private static final int THRESHOLD = 10;

        public PrintTask(int[] nums, int start, int end) {
            this.nums = nums;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    System.out.println(Thread.currentThread().getName() + " prints: " + nums[i]);
                }
                return;
            }

            int mid = (start + end) / 2;
            invokeAll(
                new PrintTask(nums, start, mid),
                new PrintTask(nums, mid, end)
            );
        }
    }

    public static void main(String[] args) {
        int[] array = new int[30];
        for (int i = 0; i < 30; i++) array[i] = i;

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new PrintTask(array, 0, array.length));
    }
}
