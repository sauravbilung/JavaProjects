package concurrency._6_ParallelStreams;

import java.util.Arrays;

public class _2_OrderedParallelStream {
    public static void main(String[] args) {
        Arrays.asList("B", "A", "D", "C")
              .parallelStream()
              .forEachOrdered(System.out::println); // maintains order
    }

    // Using .forEachOrdered() to maintain order
}
