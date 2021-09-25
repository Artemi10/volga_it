package devanmejia.repository;

import devanmejia.model.WordsStatistics;

import java.util.Optional;

public class WordsStatisticsRepositoryImpl implements WordsStatisticsRepository {
    private static final WordsStatisticsRepository INSTANCE = new WordsStatisticsRepositoryImpl();

    private WordsStatisticsRepositoryImpl() {}

    public static WordsStatisticsRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public void save(WordsStatistics word) {

    }

    @Override
    public Optional<WordsStatistics> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(WordsStatistics word) {

    }
}
