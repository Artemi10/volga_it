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

public class StatsRepositoryExceptionLogger implements StatsRepository {
    private static final String FILE_NAME = "/logger.config";
    private static final Logger LOGGER;
    static {
        try(InputStream inputStream = FileServiceExceptionLogger.class.getResourceAsStream(FILE_NAME)){
            LogManager.getLogManager().readConfiguration(inputStream);
        }catch (IOException e){
            System.err.printf("Can not find %s file. Create standard logger\n", FILE_NAME);
        }
        LOGGER = Logger.getLogger(FileServiceExceptionLogger.class.getName());
    }

    private final StatsRepository statsRepository;

    public StatsRepositoryExceptionLogger(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    public void save(Stats word) {
        try{
            statsRepository.save(word);
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception during saving word statistics",  e);
            throw e;
        }
    }

    @Override
    public Optional<Stats> getById(int id) {
        try{
            return statsRepository.getById(id);
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception during getting word statistics",  e);
            throw e;
        }
    }

    @Override
    public void delete(Stats word) {
        try{
            statsRepository.delete(word);
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception during deleting word statistics",  e);
            throw e;
        }
    }
}
