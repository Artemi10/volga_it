package devanmejia.repository;

import devanmejia.model.WordsStatics;

import java.util.Optional;

public interface WordsStaticsRepository {
    void save(WordsStatics word);
    Optional<WordsStatics> getById(int id);
    void delete(WordsStatics word);
}
