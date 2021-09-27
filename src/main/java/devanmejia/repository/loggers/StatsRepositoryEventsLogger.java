package devanmejia.repository.loggers;

import devanmejia.model.Stats;
import devanmejia.repository.StatsRepository;
import devanmejia.service.loggers.FileServiceExceptionLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class StatsRepositoryEventsLogger implements StatsRepository {
    private static final String FILE_NAME = "logger.config";
    private static final Logger LOGGER;
    static {
        try(InputStream inputStream = FileServiceExceptionLogger.class.getResourceAsStream(FILE_NAME)){
            LogManager.getLogManager().readConfiguration(inputStream);
        }catch (IOException e){
            System.err.printf("Can not find %s file. Create standard logger", FILE_NAME);
        }
        LOGGER = Logger.getLogger(FileServiceExceptionLogger.class.getName());
    }

    private final StatsRepository statsRepository;

    public StatsRepositoryEventsLogger(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    public void save(Stats word) {
        LOGGER.log(Level.INFO, "Saving word statistics");
        statsRepository.save(word);
        LOGGER.log(Level.INFO, "Word statistics was successfully saved");
    }

    @Override
    public Optional<Stats> getById(int id) {
        LOGGER.log(Level.INFO, "Getting word statistics");
        Optional<Stats> wordsStats = statsRepository.getById(id);
        LOGGER.log(Level.INFO, "Word statistics was successfully got");
        return wordsStats;
    }

    @Override
    public void delete(Stats word) {
        LOGGER.log(Level.INFO, "Deleting word statistics");
        statsRepository.delete(word);
        LOGGER.log(Level.INFO, "Word statistics was successfully deleted");
    }
}
