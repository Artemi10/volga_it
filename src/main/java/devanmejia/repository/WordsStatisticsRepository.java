package devanmejia.repository;

import devanmejia.model.WordsStatistics;

import java.util.Optional;

public interface WordsStatisticsRepository {
    void save(WordsStatistics word);
    Optional<WordsStatistics> getById(int id);
    void delete(WordsStatistics word);
}
