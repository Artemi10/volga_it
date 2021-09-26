package devanmejia.service;

import devanmejia.model.WordsStatistics;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HTMLStatistics implements FileStatistics{
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final File htmlFile;
    private final Semaphore semaphore;
    private final ExecutorService executor;

    public HTMLStatistics(File htmlFile) {
        this.htmlFile = htmlFile;
        this.semaphore = new Semaphore(0);
        this.executor = Executors.newFixedThreadPool(PROCESSORS);
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

    private int readFile(WordsStatistics statistics) throws IOException {
        int taskAmount = 0;
        try (FileChannel inputChannel = new FileInputStream(htmlFile).getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(4 * 1024);
            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                String line = StandardCharsets.UTF_8.decode(buffer).toString();
                System.out.println(line);
                buffer.clear();
                taskAmount++;
            }
        }
        return taskAmount;
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
