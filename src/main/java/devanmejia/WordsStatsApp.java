package devanmejia;

import devanmejia.model.Stats;
import devanmejia.repository.StatsRepository;
import devanmejia.repository.StatsRepositoryFactory;
import devanmejia.service.FileService;
import devanmejia.service.FileServiceFactory;
import devanmejia.service.type.FileType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class WordsStatsApp {

    public static void main(String[] args) {
        System.out.println("Write html file path...");
        try{
            File file = new File(readLine());
            FileService fileService = FileServiceFactory.createService(file, FileType.HTML);
            Stats stats = fileService.getWordsStatistic();
            System.out.println(stats.toStringSorted());
            StatsRepository repository = StatsRepositoryFactory.createRepository();
            repository.update(stats);
        } catch (Exception e){
           e.printStackTrace();
        }
    }

    private static String readLine() throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8.displayName(new Locale("rus"))))){
            return  reader.readLine();
        }
    }
}
