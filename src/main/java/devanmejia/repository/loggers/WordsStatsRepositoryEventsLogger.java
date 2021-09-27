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

public class WordsStatsRepositoryEventsLogger implements WordsStatsRepository {
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

    public WordsStatsRepositoryEventsLogger(WordsStatsRepository wordsStatsRepository) {
        this.wordsStatsRepository = wordsStatsRepository;
    }

    @Override
    public void save(WordsStats word) {
        LOGGER.log(Level.INFO, "Saving word statistics");
        wordsStatsRepository.save(word);
        LOGGER.log(Level.INFO, "Word statistics was successfully saved");
    }

    @Override
    public Optional<WordsStats> getById(int id) {
        LOGGER.log(Level.INFO, "Getting word statistics");
        Optional<WordsStats> wordsStats = wordsStatsRepository.getById(id);
        LOGGER.log(Level.INFO, "Word statistics was successfully got");
        return wordsStats;
    }

    @Override
    public void delete(WordsStats word) {
        LOGGER.log(Level.INFO, "Deleting word statistics");
        wordsStatsRepository.delete(word);
        LOGGER.log(Level.INFO, "Word statistics was successfully deleted");
    }
}
