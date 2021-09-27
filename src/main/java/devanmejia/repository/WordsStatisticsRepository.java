package devanmejia.repository;

import devanmejia.model.WordsStats;

import java.util.Optional;

public interface WordsStatisticsRepository {
    void save(WordsStats word);
    Optional<WordsStats> getById(int id);
    void delete(WordsStats word);
}
