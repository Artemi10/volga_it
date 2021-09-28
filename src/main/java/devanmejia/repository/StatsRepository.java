package devanmejia.repository;

import devanmejia.model.Stats;

import java.sql.SQLException;
import java.util.Optional;

public interface StatsRepository {
    void save(Stats stats) throws SQLException;
    Optional<Stats> getByPath(String path) throws SQLException;
    void delete(String path) throws SQLException;
}
