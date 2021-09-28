package devanmejia.service;

import devanmejia.model.Stats;
import devanmejia.service.parser.LineParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

abstract class AbstractFileService implements FileService {
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final File htmlFile;
    private final Semaphore semaphore;
    private final ExecutorService executor;
    private final String delimiter;
    private final LineParser lineParser;

    public AbstractFileService(File htmlFile, LineParser lineParser, String delimiter) {
        this.htmlFile = htmlFile;
        this.lineParser = lineParser;
        this.semaphore = new Semaphore(0);
        this.delimiter = delimiter;
        this.executor = Executors.newFixedThreadPool(PROCESSORS);
    }

    @Override
    public Stats createWordsStatistic() throws IOException {
        Stats statistics = new Stats(htmlFile);
        int lineAmount = readFile(statistics);
        executor.shutdown();
        try {
            semaphore.acquire(lineAmount);
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        return statistics;
    }

    private int readFile(Stats statistics) throws IOException {
        int taskAmount = 0;
        try (InputStream inputStream = new FileInputStream(htmlFile);
             Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNext()){
                scanner.useDelimiter(delimiter);
                LineWorker worker = new LineWorker(scanner.next(), lineParser, semaphore, statistics);
                executor.execute(worker);
                taskAmount++;
            }
        }
        return taskAmount;
    }

    private static class LineWorker implements Runnable{
        private final String line;
        private final LineParser lineParser;
        private final Stats statistics;
        private final Semaphore semaphore;

        public LineWorker(String line, LineParser lineParser, Semaphore semaphore, Stats statistics) {
            this.line = line;
            this.lineParser = lineParser;
            this.statistics = statistics;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            lineParser.parse(line).forEach(statistics::addWord);
            semaphore.release();
        }
    }
}
