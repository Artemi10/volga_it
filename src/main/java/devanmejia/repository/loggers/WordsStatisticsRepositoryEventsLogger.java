package devanmejia.repository.loggers;

import devanmejia.model.WordsStats;
import devanmejia.repository.WordsStatisticsRepository;

import java.util.Optional;

public class WordsStatisticsRepositoryEventsLogger implements WordsStatisticsRepository {
    private WordsStatisticsRepository wordsStatisticsRepository;

    public WordsStatisticsRepositoryEventsLogger(WordsStatisticsRepository wordsStatisticsRepository) {
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
