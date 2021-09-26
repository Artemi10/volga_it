package devanmejia;

import devanmejia.model.WordsStatistics;
import devanmejia.service.HTMLFileStatisticsService;

import java.io.*;

public class WordsStatisticsApp {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\lyaha\\OneDrive\\Рабочий стол\\volga_it\\file3.html");
        HTMLFileStatisticsService htmlStatistics = new HTMLFileStatisticsService(file);
        WordsStatistics wordsStatistics = htmlStatistics.createWordsStatistic();
        System.out.println(wordsStatistics);
    }
}
