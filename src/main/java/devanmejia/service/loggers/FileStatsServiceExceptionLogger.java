package devanmejia.service.loggers;

import devanmejia.model.WordsStats;
import devanmejia.service.FileStatsService;

import java.io.IOException;


public class FileStatsServiceExceptionLogger implements FileStatsService {
    private FileStatsService fileStatsService;

    public FileStatsServiceExceptionLogger(FileStatsService fileStatsService) {
        this.fileStatsService = fileStatsService;
    }

    @Override
    public WordsStats createWordsStatistic() throws IOException {
        try {
            return fileStatsService.createWordsStatistic();
        } catch (Exception e){
            // TODO logging
            throw e;
        }
    }
}
