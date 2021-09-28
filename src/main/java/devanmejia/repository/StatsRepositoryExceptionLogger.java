package devanmejia.repository;

import devanmejia.model.Stats;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class StatsRepositoryExceptionLogger implements StatsRepository {
    private static final java.lang.String FILE_NAME = "/logger.config";
    private static final Logger LOGGER;
    static {
        try(InputStream inputStream = StatsRepositoryExceptionLogger.class.getResourceAsStream(FILE_NAME)){
            LogManager.getLogManager().readConfiguration(inputStream);
        }catch (IOException e){
            System.err.printf("Can not find %s file. Create standard logger\n", FILE_NAME);
        }
        LOGGER = Logger.getLogger(StatsRepositoryExceptionLogger.class.getName());
    }

    private final StatsRepository statsRepository;

    public StatsRepositoryExceptionLogger(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    public void save(Stats stats) throws SQLException {
        try{
            statsRepository.save(stats);
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception during saving word statistics",  e);
            throw e;
        }
    }

    @Override
    public Optional<Stats> getByPath(String path) throws SQLException {
        try{
            return statsRepository.getByPath(path);
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception during selecting word statistics",  e);
            throw e;
        }
    }

    @Override
    public void update(Stats stats) throws SQLException {
        try{
            statsRepository.update(stats);
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception during updating word statistics",  e);
            throw e;
        }
    }

    @Override
    public void delete(String path) throws SQLException {
        try{
            statsRepository.delete(path);
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception during deleting word statistics",  e);
            throw e;
        }
    }
}
