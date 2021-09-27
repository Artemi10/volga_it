package devanmejia.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Stats {
    private final ConcurrentHashMap<String, Integer> words;

    public Stats() {
        this.words = new ConcurrentHashMap<>();
    }

    public void addWord(String word){
        Integer previousValue = words.putIfAbsent(word, 1);
        synchronized (this){
            if (previousValue != null){
                words.put(word, previousValue + 1);
            }
        }
    }

    public Map<String, Integer> getWords() {
        return Collections.unmodifiableMap(words);
    }

    @Override
    public String toString() {
        return getWords().entrySet().stream()
                .sorted((k1, k2) -> - k1.getValue().compareTo(k2.getValue()))
                .map(entry -> entry.getKey() + " - " + words.get(entry.getKey()))
                .collect(Collectors.joining("\n"));
    }
}
