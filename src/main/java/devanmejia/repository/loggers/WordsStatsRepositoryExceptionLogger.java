package devanmejia.repository.loggers;

import devanmejia.model.WordsStats;
import devanmejia.repository.WordsStatsRepository;
import devanmejia.service.loggers.FileStatsServiceExceptionLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class WordsStatsRepositoryExceptionLogger implements WordsStatsRepository {
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

    private final WordsStatsRepository wordsStatsRepository;

    public WordsStatsRepositoryExceptionLogger(WordsStatsRepository wordsStatsRepository) {
        this.wordsStatsRepository = wordsStatsRepository;
    }

    @Override
    public void save(WordsStats word) {
        try{
            wordsStatsRepository.save(word);
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception during saving word statistics",  e);
            throw e;
        }
    }

    @Override
    public Optional<WordsStats> getById(int id) {
        try{
            return wordsStatsRepository.getById(id);
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception during getting word statistics",  e);
            throw e;
        }
    }

    @Override
    public void delete(WordsStats word) {
        try{
            wordsStatsRepository.delete(word);
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception during deleting word statistics",  e);
            throw e;
        }
    }
}
