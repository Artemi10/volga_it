package devanmejia.service.loggers;

import devanmejia.model.WordsStatistics;
import devanmejia.service.FileStatisticsService;

import java.io.IOException;


public class FileStatisticsServiceExceptionLogger implements FileStatisticsService {
    private FileStatisticsService fileStatisticsService;

    public FileStatisticsServiceExceptionLogger(FileStatisticsService fileStatisticsService) {
        this.fileStatisticsService = fileStatisticsService;
    }

    @Override
    public WordsStatistics createWordsStatistic() throws IOException {
        try {
            return fileStatisticsService.createWordsStatistic();
        } catch (Exception e){
            // TODO logging
            throw e;
        }
    }
}
