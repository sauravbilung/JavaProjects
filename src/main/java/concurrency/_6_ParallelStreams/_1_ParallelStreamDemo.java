package concurrency._6_ParallelStreams;

import java.util.List;
import java.util.stream.IntStream;

public class _1_ParallelStreamDemo {
    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1, 10_000_000).boxed().toList();

        long start = System.currentTimeMillis();
        long sum = numbers.stream().mapToLong(i -> i * 2).sum(); // Sequential
        System.out.println("Sequential sum: " + sum + " in " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        long parallelSum = numbers.parallelStream().mapToLong(i -> i * 2).sum(); // Parallel
        System.out.println("Parallel sum: " + parallelSum + " in " + (System.currentTimeMillis() - start) + "ms");
    }
}

/*
* In this example parallelStream will perform slower because there are too much overhead. If the dataset would have been large it would have worked better.
*
* 1. Overhead of parallelism
* 2. Workload is too trivial
* 3. Memory and boxing cost.
* 4. JVM warm-up & thread scheduling:
* */