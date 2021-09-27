package devanmejia;

import devanmejia.model.WordsStats;
import devanmejia.service.HTMLFileStatsService;

import java.io.*;

public class WordsStatisticsApp {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\lyaha\\OneDrive\\Рабочий стол\\volga_it\\file3.html");
        HTMLFileStatsService htmlStatistics = new HTMLFileStatsService(file);
        WordsStats wordsStats = htmlStatistics.createWordsStatistic();
        System.out.println(wordsStats);
    }
}
