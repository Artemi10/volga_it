package devanmejia.repository;

import devanmejia.model.Stats;

import java.util.Optional;

public interface StatsRepository {
    void save(Stats stats);
    Optional<Stats> getById(int id);
    void delete(Stats stats);
}
