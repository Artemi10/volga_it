package devanmejia.repository.loggers;

import devanmejia.model.WordsStats;
import devanmejia.repository.WordsStatisticsRepository;

import java.util.Optional;

public class WordsStatisticsRepositoryExceptionLogger implements WordsStatisticsRepository {
    private WordsStatisticsRepository wordsStatisticsRepository;

    public WordsStatisticsRepositoryExceptionLogger(WordsStatisticsRepository wordsStatisticsRepository) {
        this.wordsStatisticsRepository = wordsStatisticsRepository;
    }

    //TODO

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
