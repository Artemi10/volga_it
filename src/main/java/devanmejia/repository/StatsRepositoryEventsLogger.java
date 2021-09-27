package devanmejia.repository;

import devanmejia.model.Stats;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class StatsRepositoryEventsLogger implements StatsRepository {
    private static final java.lang.String FILE_NAME = "/logger.config";
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
    public void save(Stats stats) throws SQLException {
        LOGGER.log(Level.INFO, "Saving word statistics");
        statsRepository.save(stats);
        LOGGER.log(Level.INFO, "Word statistics was successfully saved");
    }

    @Override
    public Optional<Stats> getByPath(String path) throws SQLException {
        LOGGER.log(Level.INFO, "Getting word statistics");
        Optional<Stats> wordsStats = statsRepository.getByPath(path);
        LOGGER.log(Level.INFO, "Word statistics was successfully got");
        return wordsStats;
    }

    @Override
    public void delete(String path) {
        LOGGER.log(Level.INFO, "Deleting word statistics");
        statsRepository.delete(path);
        LOGGER.log(Level.INFO, "Word statistics was successfully deleted");
    }
}
