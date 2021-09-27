package devanmejia.repository;

import devanmejia.repository.loggers.StatsRepositoryEventsLogger;
import devanmejia.repository.loggers.StatsRepositoryExceptionLogger;

public class StatsRepositoryFactory {

    public static StatsRepository getRepository(){
        StatsRepository repository = StatsRepositoryImpl.getInstance();
        return new StatsRepositoryExceptionLogger(new StatsRepositoryEventsLogger(repository));
    }
}
