package devanmejia.repository;

import devanmejia.model.Stats;

import java.util.Optional;

public class StatsRepositoryImpl implements StatsRepository {
    private static final StatsRepository INSTANCE = new StatsRepositoryImpl();

    private StatsRepositoryImpl() {}

    public static StatsRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public void save(Stats word) {

    }

    @Override
    public Optional<Stats> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(Stats word) {

    }
}
