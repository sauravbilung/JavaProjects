package concurrency._7_ForkJoinFramework;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class _3_ParallelMergeSort extends RecursiveAction {
    private final int[] array;
    private final int start, end;
    private static final int THRESHOLD = 16;

    public _3_ParallelMergeSort(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            Arrays.sort(array, start, end);
            return;
        }

        int mid = (start + end) / 2;

        _3_ParallelMergeSort leftTask = new _3_ParallelMergeSort(array, start, mid);
        _3_ParallelMergeSort rightTask = new _3_ParallelMergeSort(array, mid, end);

        invokeAll(leftTask, rightTask);
        merge(start, mid, end);
    }

    private void merge(int start, int mid, int end) {
        int[] temp = new int[end - start];
        int i = start, j = mid, k = 0;

        while (i < mid && j < end) {
            temp[k++] = array[i] <= array[j] ? array[i++] : array[j++];
        }
        while (i < mid) temp[k++] = array[i++];
        while (j < end) temp[k++] = array[j++];

        System.arraycopy(temp, 0, array, start, temp.length);
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 9, 1, 6, 4, 2, 8, 7, 0};

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new _3_ParallelMergeSort(nums, 0, nums.length));

        System.out.println("Sorted: " + Arrays.toString(nums));
    }
}
