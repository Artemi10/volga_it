package devanmejia;

import devanmejia.model.WordsStatistics;
import devanmejia.service.HTMLStatistics;

import java.io.*;
import java.util.Date;

public class WordsStatisticsApp {

    public static void main(String[] args) throws IOException {
//        new Thread(() -> {
//            while (true){
//                System.out.println("Fre memory");
//                System.out.println(Runtime.getRuntime().freeMemory());
//                System.out.println("Max memory");
//                System.out.println(Runtime.getRuntime().maxMemory());
//                System.out.println("Total memory");
//                System.out.println(Runtime.getRuntime().totalMemory());
//                System.out.println();
//                System.out.println();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        File file = new File("C:\\Users\\Lenovo\\Desktop\\creditcard.csv");
        HTMLStatistics htmlStatistics = new HTMLStatistics(file);
        long time = new Date().getTime();
        WordsStatistics wordsStatistics = htmlStatistics.createWordsStatistic();
        System.out.println(new Date().getTime() - time);
        System.out.println(wordsStatistics);
    }
}
