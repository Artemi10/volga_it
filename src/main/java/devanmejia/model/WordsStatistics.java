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
    public synchronized String toString() {
        return stats.entrySet().stream()
                .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                .map(entry -> entry.getKey() + " - " + stats.get(entry.getKey()))
                .collect(Collectors.joining("\n"));
    }
}
