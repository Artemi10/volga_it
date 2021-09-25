package devanmejia.service;

import devanmejia.model.WordsStatistics;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.*;

public class HTMLStatistics implements FileStatistics{
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final File htmlFile;
    private final Semaphore semaphore;
    private final ExecutorService executor;

    public HTMLStatistics(File htmlFile) {
        this.htmlFile = htmlFile;
        this.semaphore = new Semaphore(0);
        this.executor = Executors.newFixedThreadPool(PROCESSORS / 2);
    }

    @Override
    public WordsStatistics createWordsStatistic() {
        WordsStatistics statistics = new WordsStatistics();
        try{
            int lineAmount = readFile(statistics);
            executor.shutdown();
            semaphore.acquire(lineAmount);
        } catch (Exception e){
            // TODO
            e.printStackTrace();
        }
        return statistics;
    }

    private int readFile(WordsStatistics statistics) throws IOException, ExecutionException, InterruptedException {
        BufferedReader reader = new BufferedReader(new FileReader(htmlFile));
        ForkJoinPool customThreadPool = new ForkJoinPool(PROCESSORS / 2 + PROCESSORS % 2);
        return customThreadPool.submit(() -> reader.lines().parallel()
                .map(line -> executor.submit(new LineWorker(line, semaphore, statistics)))
                .count()).get().intValue();
    }

    private static class LineWorker implements Runnable{
        private final String line;
        private final Semaphore semaphore;
        private final WordsStatistics statistics;

        public LineWorker(String line, Semaphore semaphore, WordsStatistics statistics) {
            this.line = line;
            this.semaphore = semaphore;
            this.statistics = statistics;
        }

        @Override
        public void run() {
            Arrays.stream(line.replaceAll("<.*?>", "")
                            .split("&(.*);|\\s|[^a-zA-Z0-9А-Яа-я]"))
                    .filter(word -> !word.trim().equals(""))
                    .forEach(statistics::addWord);
            semaphore.release();
        }
    }
}
