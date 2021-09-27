package devanmejia.model;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Stats {
    private final File htmlFile;
    private final ConcurrentHashMap<java.lang.String, Integer> words;

    public Stats(File htmlFile) {
        this.htmlFile = htmlFile;
        this.words = new ConcurrentHashMap<>();
    }

    public void addWord(java.lang.String word){
        Integer previousValue = words.putIfAbsent(word, 1);
        synchronized (this){
            if (previousValue != null){
                words.put(word, previousValue + 1);
            }
        }
    }

    public Map<java.lang.String, Integer> getWords() {
        return Collections.unmodifiableMap(words);
    }

    public java.lang.String getPath(){
        return htmlFile.getAbsolutePath();
    }

    @Override
    public java.lang.String toString() {
        return getWords().entrySet().stream()
                .sorted((k1, k2) -> - k1.getValue().compareTo(k2.getValue()))
                .map(entry -> entry.getKey() + " - " + words.get(entry.getKey()))
                .collect(Collectors.joining("\n"));
    }
}
