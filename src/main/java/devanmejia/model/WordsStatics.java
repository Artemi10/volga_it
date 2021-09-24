package devanmejia.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WordsStatics {
    private Map<String, Long> stats;
    private File htmlFile;

    public WordsStatics(File htmlFile) {
        this.stats = new HashMap<>();
        this.htmlFile = htmlFile;
    }

    public boolean containsWord(String word){
        return stats.containsKey(word);
    }

    public void addWord(String word){
        long repeatAmount = 0;
        if (containsWord(word)) {
            repeatAmount = stats.get(word);
        }
        stats.put(word, repeatAmount + 1);
    }

    public void deleteWord(String word, int amount){
        if (containsWord(word)) {
            long repeatAmount = stats.get(word);
            if (repeatAmount > amount){
                stats.put(word, repeatAmount - amount);
            }
            else {
                stats.remove(word);
            }
        }
    }

    public void deleteWord(String word){
        deleteWord(word, 1);
    }
}
