package devanmejia;

import devanmejia.model.Stats;
import devanmejia.repository.StatsRepository;
import devanmejia.repository.StatsRepositoryFactory;
import devanmejia.service.FileService;
import devanmejia.service.FileServiceFactory;

import java.io.*;

public class WordsStatsApp {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\lyaha\\OneDrive\\Рабочий стол\\volga_it\\file3.html");
        FileService fileService = FileServiceFactory.createService(file);
        Stats stats = fileService.createWordsStatistic();
        System.out.println(stats);
        StatsRepository repository = StatsRepositoryFactory.createRepository();
        repository.save(stats);
    }
}
