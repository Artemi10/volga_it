package devanmejia;

import devanmejia.model.Stats;
import devanmejia.service.HTMLFileService;

import java.io.*;

public class WordsStatsApp {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\lyaha\\OneDrive\\Рабочий стол\\volga_it\\file3.html");
        HTMLFileService htmlStatistics = new HTMLFileService(file);
        Stats stats = htmlStatistics.createWordsStatistic();
        System.out.println(stats);
    }
}
