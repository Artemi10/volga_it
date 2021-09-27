package devanmejia.repository;

import devanmejia.model.WordsStats;

import java.util.Optional;

public class WordsStatisticsRepositoryImpl implements WordsStatisticsRepository {
    private static final WordsStatisticsRepository INSTANCE = new WordsStatisticsRepositoryImpl();

    private WordsStatisticsRepositoryImpl() {}

    public static WordsStatisticsRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public void save(WordsStats word) {

    }

    @Override
    public Optional<WordsStats> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(WordsStats word) {

    }
}
