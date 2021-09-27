package devanmejia.service.loggers;

import devanmejia.model.WordsStats;
import devanmejia.service.FileStatsService;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;


public class FileStatsServiceExceptionLogger implements FileStatsService {
    private static final String FILE_NAME = "logger.config";
    private static final Logger LOGGER;
    static {
        try(InputStream inputStream = FileStatsServiceExceptionLogger.class.getResourceAsStream(FILE_NAME)){
            LogManager.getLogManager().readConfiguration(inputStream);
        }catch (IOException e){
            System.err.printf("Can not find %s file. Create standard logger", FILE_NAME);
        }
        LOGGER = Logger.getLogger(FileStatsServiceExceptionLogger.class.getName());
    }

    private final FileStatsService fileStatsService;

    public FileStatsServiceExceptionLogger(FileStatsService fileStatsService) {
        this.fileStatsService = fileStatsService;
    }

    @Override
    public WordsStats createWordsStatistic() throws IOException {
        try {
            return fileStatsService.createWordsStatistic();
        }
        catch (IOException e){
            LOGGER.log(Level.WARNING, "IOException",  e);
            throw e;
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception",  e);
            throw e;
        }
    }
}
