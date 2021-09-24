package devanmejia.service;

import devanmejia.model.WordsStatics;

import java.io.File;

public class HTMLStatistics implements FileStatistics{
    private File file;

    public HTMLStatistics(File file) {
        this.file = file;
    }

    public HTMLStatistics(String path) {
        this.file = new File(path);
    }

    @Override
    public WordsStatics createWordsStatistic() {
        return null;
    }
}
