package devanmejia.service;

import devanmejia.model.WordsStats;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.*;

public class HTMLFileStatsService implements FileStatsService {
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final File htmlFile;
    private final Semaphore semaphore;
    private final ExecutorService executor;

    public HTMLFileStatsService(File htmlFile) {
        this.htmlFile = htmlFile;
        this.semaphore = new Semaphore(0);
        this.executor = Executors.newFixedThreadPool(PROCESSORS);
    }

    @Override
    public WordsStats createWordsStatistic() throws IOException {
        WordsStats statistics = new WordsStats();
        int lineAmount = readFile(statistics);
        executor.shutdown();
        try {
            semaphore.acquire(lineAmount);
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        return statistics;
    }

    private int readFile(WordsStats statistics) throws IOException {
        int taskAmount = 0;
        try (InputStream inputStream = new FileInputStream(htmlFile);
             Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNext()){
                scanner.useDelimiter("</.*?>");
                String line = scanner.next() + "</tag>";
                executor.execute(new LineWorker(line, semaphore, statistics));
                taskAmount++;
            }
        }
        return taskAmount;
    }

    private static class LineWorker implements Runnable{
        private final String line;
        private final Semaphore semaphore;
        private final WordsStats statistics;

        public LineWorker(String line, Semaphore semaphore, WordsStats statistics) {
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