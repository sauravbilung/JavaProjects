package FileHandling._3_ParallelProcessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Buidling a word count solution using :
 * ✅ Completable Future
 * ✅ Batch Processing
 * ✅ Efficient buffered reader
 * ✅ Parallelism for performance on large files
 */
public class _1_WordCount {

    private static final int BATCH_SIZE = 1000;
    // returns the number of CPU cores available to the Java Virtual Machine (JVM).
    private static final int THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws Exception {
        String filePath = System.getProperty("user.home") + "/IdeaProjects/LetsGoJava/src/main/resources/data/1800.csv";
        long totalWords = countWordsInParallel(filePath);
        System.out.println("Total Word Count: " + totalWords);
    }

    public static long countWordsInParallel(String filePath) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        List<CompletableFuture<Long>> futures = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> batch = new ArrayList<>(BATCH_SIZE);
            String line;

            while ((line = reader.readLine()) != null) {
                batch.add(line);
                if (batch.size() == BATCH_SIZE) {
                    futures.add(processBatchAsync(new ArrayList<>(batch), executor));
                    batch.clear();
                }
            }

            // Process remaining lines
            if (!batch.isEmpty()) {
                futures.add(processBatchAsync(batch, executor));
            }
        }

        // Wait for all batches to complete and sum their results
        CompletableFuture<Void> allDone = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allDone.join();

        long totalCount = 0;
        for (CompletableFuture<Long> future : futures) {
            totalCount += future.get(); // Or use join()
        }

        executor.shutdown();
        return totalCount;
    }

    private static CompletableFuture<Long> processBatchAsync(List<String> lines, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            return lines.stream()
                    .flatMap(line -> Arrays.stream(line.trim().split("\\s+")))
                    .filter(word -> !word.isEmpty())
                    .count();
        }, executor);
    }
}
