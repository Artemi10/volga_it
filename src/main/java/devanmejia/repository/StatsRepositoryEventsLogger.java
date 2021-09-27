package devanmejia.repository;

import devanmejia.model.Stats;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class StatsRepositoryEventsLogger implements StatsRepository {
    private static final String FILE_NAME = "/logger.config";
    private static final Logger LOGGER;
    static {
        try(InputStream inputStream = StatsRepositoryEventsLogger.class.getResourceAsStream(FILE_NAME)){
            LogManager.getLogManager().readConfiguration(inputStream);
        }catch (IOException e){
            System.err.printf("Can not find %s file. Create standard logger\n", FILE_NAME);
        }
        LOGGER = Logger.getLogger(StatsRepositoryEventsLogger.class.getName());
    }

    private final StatsRepository statsRepository;

    public StatsRepositoryEventsLogger(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    public void save(Stats stats) {
        LOGGER.log(Level.INFO, "Saving word statistics");
        statsRepository.save(stats);
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
    public void delete(Stats stats) {
        LOGGER.log(Level.INFO, "Deleting word statistics");
        statsRepository.delete(stats);
        LOGGER.log(Level.INFO, "Word statistics was successfully deleted");
    }
}
