package devanmejia;

import devanmejia.model.Stats;
import devanmejia.repository.StatsRepository;
import devanmejia.repository.StatsRepositoryFactory;
import devanmejia.service.FileService;
import devanmejia.service.FileServiceFactory;
import devanmejia.service.type.FileType;

import java.io.*;
import java.sql.SQLException;

public class WordsStatsApp {

    public static void main(java.lang.String[] args) throws IOException, SQLException {
//        File file = new File("C:\\Users\\lyaha\\OneDrive\\Рабочий стол\\volga_it\\file3.html");
//        FileService fileService = FileServiceFactory.createService(file, FileType.HTML);
//        Stats stats = fileService.createWordsStatistic();
//        System.out.println(stats);
        StatsRepository repository = StatsRepositoryFactory.createRepository();
        //repository.save(stats);
        Stats stats = repository.getByPath("C:\\Users\\lyaha\\OneDrive\\Рабочий стол\\volga_it\\file3.html").get();
        System.out.println(stats);
    }
}
