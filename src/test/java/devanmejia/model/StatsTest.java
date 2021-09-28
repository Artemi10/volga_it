package devanmejia.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

class StatsTest {
    private Stats stats;
    private Stream<String> testWords;
    private Stream<String> testWordsToSort;
    private Map<String, Integer> expectedWordsMap;

    @BeforeEach
    public void initialize(){
        stats = new Stats(new File("C:/test.txt"));
        testWordsToSort = Stream.of("hello", "hello", "hello", "how", "are", "are");
        testWords = Stream.of("hello", "hello", "how", "are", "you", "doing", "today",
                "how", "are", "OK", "Hello", "ok", "one", "two", "two", "two", "two");
        expectedWordsMap = new HashMap<>();
        expectedWordsMap.put("hello", 2);
        expectedWordsMap.put("how", 2);
        expectedWordsMap.put("are", 2);
        expectedWordsMap.put("you", 1);
        expectedWordsMap.put("doing", 1);
        expectedWordsMap.put("today", 1);
        expectedWordsMap.put("OK", 1);
        expectedWordsMap.put("Hello", 1);
        expectedWordsMap.put("ok", 1);
        expectedWordsMap.put("two", 4);
        expectedWordsMap.put("one", 1);
    }

    @Test
    public void add_words_test(){
        testWords.forEach(stats::addWord);
        assertTrue(mapEquals(expectedWordsMap, stats.getWords()));
    }

    @Test
    public void add_words_concurrent_test() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);
        testWords.forEach(word -> {
            Runnable runnable = () -> stats.addWord(word);
            service.execute(runnable);
            latch.countDown();
        });
        service.shutdown();
        latch.await();
        assertTrue(mapEquals(expectedWordsMap, stats.getWords()));
    }

    @Test
    public void to_string_sorted_test(){
        testWordsToSort.forEach(stats::addWord);
        String expected = "hello - 3\n" +
                "are - 2\n" +
                "how - 1";
        assertEquals(expected, stats.toStringSorted());
    }

    @Test
    public void get_path_test(){
        String expected = "C:\\test.txt";
        assertEquals(expected, stats.getPath());
    }


    private static boolean mapEquals(Map<String, Integer> expected, Map<String, Integer> actual){
        for (Map.Entry<String, Integer> entry : actual.entrySet()){
            String actualKey = entry.getKey();
            Integer actualValue = entry.getValue();
            if (!actualValue.equals(expected.get(actualKey))) return false;
        }
        return expected.size() == actual.size();
    }

}
