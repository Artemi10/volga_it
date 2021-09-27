package devanmejia.repository;

import devanmejia.model.Stats;

import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

class StatsRepositoryImpl implements StatsRepository {
    private static final java.lang.String URL;
    private static final java.lang.String USER;
    private static final java.lang.String PASSWORD;
    static {
        Properties properties = new Properties();
        try {
            properties.load(StatsRepositoryImpl.class.getResourceAsStream("/database.properties"));
            URL = properties.getProperty("url");
            USER = properties.getProperty("user");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e){
            java.lang.String message = "Specify database configuration in database.properties";
            throw new IllegalArgumentException(message);
        }
    }
    private static final StatsRepository INSTANCE = new StatsRepositoryImpl();

    private Connection connection;

    private StatsRepositoryImpl() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static StatsRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public void save(Stats stats) throws SQLException{
        long documentId = savePath(stats.getPath());
        saveStats(stats.getWords(), documentId);
    }

    private long savePath(java.lang.String path) throws SQLException {
        java.lang.String pathSql = "INSERT INTO my_schema.documents (path) VALUE (?);";
        PreparedStatement statement = connection.prepareStatement(pathSql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, path);
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0){
            throw new SQLException("Creating document failed, no rows affected.");
        }
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    private void saveStats(Map<java.lang.String, Integer> words, long documentId) throws SQLException {
        java.lang.String wordSql = "INSERT INTO my_schema.statistics \n" +
                "(word, amount, document_id) VALUE (?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(wordSql, Statement.RETURN_GENERATED_KEYS);
        for (Map.Entry<java.lang.String, Integer> entry : words.entrySet()){
            statement.setString(1, entry.getKey());
            statement.setInt(2, entry.getValue());
            statement.setLong(3, documentId);
            statement.addBatch();
        }
        statement.executeBatch();
    }

    @Override
    public Optional<Stats> getByPath(String path) {
        return Optional.empty();
    }

    @Override
    public void delete(String path) {

    }
}
