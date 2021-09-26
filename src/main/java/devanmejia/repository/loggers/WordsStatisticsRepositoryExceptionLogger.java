package devanmejia.repository.loggers;

import devanmejia.model.WordsStatistics;
import devanmejia.repository.WordsStatisticsRepository;

import java.util.Optional;

public class WordsStatisticsRepositoryExceptionLogger implements WordsStatisticsRepository {
    private WordsStatisticsRepository wordsStatisticsRepository;

    public WordsStatisticsRepositoryExceptionLogger(WordsStatisticsRepository wordsStatisticsRepository) {
        this.wordsStatisticsRepository = wordsStatisticsRepository;
    }

    //TODO

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
