package devanmejia.model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class WordsStatistics {
    private final ConcurrentHashMap<String, Integer> stats;

    public WordsStatistics() {
        this.stats = new ConcurrentHashMap<>();
    }

    public void addWord(String word){
        Integer previousValue = stats.putIfAbsent(word, 1);
        synchronized (this){
            if (previousValue != null){
                stats.put(word, previousValue + 1);
            }
        }
    }

    @Override
    public String toString() {
        return stats.keySet().stream()
                .map(key -> key + " " + stats.get(key))
                .collect(Collectors.joining("\n"));
    }
}
