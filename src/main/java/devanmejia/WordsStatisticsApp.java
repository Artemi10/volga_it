package devanmejia;

import devanmejia.model.WordsStatistics;
import devanmejia.service.HTMLStatisticsService;

import java.io.*;
import java.util.Date;

public class WordsStatisticsApp {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\lyaha\\OneDrive\\Рабочий стол\\volga_it\\file4.html");
        HTMLStatisticsService htmlStatistics = new HTMLStatisticsService(file);
        long time = new Date().getTime();
        WordsStatistics wordsStatistics = htmlStatistics.createWordsStatistic();
        System.out.println(new Date().getTime() - time);
        System.out.println(wordsStatistics);
    }
}
