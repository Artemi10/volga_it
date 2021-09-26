package devanmejia;

import devanmejia.model.WordsStatistics;
import devanmejia.service.HTMLStatisticsService;

import java.io.*;

public class WordsStatisticsApp {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\lyaha\\OneDrive\\Рабочий стол\\volga_it\\file3.html");
        HTMLStatisticsService htmlStatistics = new HTMLStatisticsService(file);
        WordsStatistics wordsStatistics = htmlStatistics.createWordsStatistic();
        System.out.println(wordsStatistics);
    }
}
