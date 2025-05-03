package concurrency._6_ParallelStreams;

import java.util.List;

public class HeavyTaskParallelStream {
    public static void main(String[] args) {
        List<String> items = List.of("A", "B", "C", "D", "E");

        items.parallelStream().forEach(item -> {
            System.out.println("Processing " + item + " in " + Thread.currentThread().getName());
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        });
    }
}
