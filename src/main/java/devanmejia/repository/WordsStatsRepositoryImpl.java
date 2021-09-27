package devanmejia.repository;

import devanmejia.model.WordsStats;

import java.util.Optional;

public class WordsStatsRepositoryImpl implements WordsStatsRepository {
    private static final WordsStatsRepository INSTANCE = new WordsStatsRepositoryImpl();

    private WordsStatsRepositoryImpl() {}

    public static WordsStatsRepository getInstance(){
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
