package devanmejia.repository;

public class StatsRepositoryFactory {

    public static StatsRepository createRepository(){
        StatsRepository repository = StatsRepositoryImpl.getInstance();
        return new StatsRepositoryExceptionLogger(new StatsRepositoryEventsLogger(repository));
    }
}
