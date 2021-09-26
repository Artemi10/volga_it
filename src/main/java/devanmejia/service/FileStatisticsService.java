package devanmejia.service;

import devanmejia.model.WordsStatistics;

import java.io.IOException;

public interface FileStatisticsService {
    WordsStatistics createWordsStatistic() throws IOException;
}
