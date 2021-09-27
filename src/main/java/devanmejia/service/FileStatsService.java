package devanmejia.service;

import devanmejia.model.WordsStats;

import java.io.IOException;

public interface FileStatsService {
    WordsStats createWordsStatistic() throws IOException;
}
