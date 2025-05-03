package concurrency._7_ForkJoinFramework;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class _4_FileSearchTask extends RecursiveTask<List<File>> {
    private final File directory;
    private final String keyword;

    public _4_FileSearchTask(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }

    @Override
    protected List<File> compute() {
        List<File> matchingFiles = new ArrayList<>();
        List<_4_FileSearchTask> subTasks = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files == null) return matchingFiles;

        for (File file : files) {
            if (file.isDirectory()) {
                _4_FileSearchTask task = new _4_FileSearchTask(file, keyword);
                task.fork(); // process subdirectory in parallel
                subTasks.add(task);
            } else {
                if (file.getName().contains(keyword)) {
                    matchingFiles.add(file);
                }
            }
        }

        for (_4_FileSearchTask task : subTasks) {
            matchingFiles.addAll(task.join()); // wait and merge results
        }

        return matchingFiles;
    }

    public static void main(String[] args) {
        File root = new File("src"); // change to a valid path
        String keyword = "Service";  // search for files containing this word

        ForkJoinPool pool = new ForkJoinPool();
        _4_FileSearchTask task = new _4_FileSearchTask(root, keyword);
        List<File> result = pool.invoke(task);

        result.forEach(file -> System.out.println("Found: " + file.getAbsolutePath()));
    }
}
