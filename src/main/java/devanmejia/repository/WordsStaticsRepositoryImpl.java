package devanmejia.repository;

import devanmejia.model.WordsStatics;

import java.util.List;
import java.util.Optional;

public class WordsStaticsRepositoryImpl implements WordsStaticsRepository {
    private static final WordsStaticsRepository INSTANCE = new WordsStaticsRepositoryImpl();

    private WordsStaticsRepositoryImpl() {}

    public static WordsStaticsRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public void save(WordsStatics word) {

    }

    @Override
    public Optional<WordsStatics> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(WordsStatics word) {

    }
}
