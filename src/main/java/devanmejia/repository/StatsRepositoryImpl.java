package devanmejia.repository;

import devanmejia.model.Stats;
import devanmejia.repository.template_storage.SqlTemplatesStorage;
import devanmejia.repository.template_storage.SqlType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

class StatsRepositoryImpl implements StatsRepository {
    private static final StatsRepository INSTANCE = new StatsRepositoryImpl();
    private Connection connection;
    private SqlTemplatesStorage templatesStorage;

    private StatsRepositoryImpl() {
        try {
            Properties DB_CONFIGURATION = loadDBConfigProperties();
            this.connection = DriverManager.getConnection(DB_CONFIGURATION.getProperty("url"),
                    DB_CONFIGURATION.getProperty("user"), DB_CONFIGURATION.getProperty("password"));
            this.templatesStorage = SqlTemplatesStorage.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static StatsRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public void save(Stats stats) throws SQLException{
        long documentId = saveDocument(stats.getPath());
        saveStats(stats.getWords(), documentId);
    }

    private long saveDocument(String path) throws SQLException {
        String insertDocumentQuery = templatesStorage.getTemplate(SqlType.INSERT_DOCUMENT);
        PreparedStatement statement = connection
                .prepareStatement(insertDocumentQuery, Statement.RETURN_GENERATED_KEYS);
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
        String insertStatQuery = templatesStorage.getTemplate(SqlType.INSERT_STATS);
        PreparedStatement statement = connection
                .prepareStatement(insertStatQuery, Statement.RETURN_GENERATED_KEYS);
        for (Map.Entry<java.lang.String, Integer> entry : words.entrySet()){
            statement.setString(1, entry.getKey());
            statement.setInt(2, entry.getValue());
            statement.setLong(3, documentId);
            statement.addBatch();
        }
        statement.executeBatch();
    }

    @Override
    public Optional<Stats> getByPath(String path) throws SQLException {
        String selectStatQuery = templatesStorage.getTemplate(SqlType.SELECT_STATS);
        PreparedStatement statement = connection.prepareStatement(selectStatQuery);
        statement.setString(1, path);
        ResultSet resultSet = statement.executeQuery();
        Map<String, Integer> words = new HashMap<>();
        while (resultSet.next()){
            words.put(resultSet.getString(1), resultSet.getInt(2));
        }
        if (!words.isEmpty()){
            File htmlFile = new File(resultSet.getString(3));
            return Optional.of(new Stats(htmlFile, words));
        }
        return Optional.empty();
    }

    @Override
    public void delete(String path) {

    }

    private static Properties loadDBConfigProperties(){
        try {
            Properties properties = new Properties();
            InputStream inputStream = StatsRepositoryImpl.class
                    .getResourceAsStream("/database.properties");
            properties.load(inputStream);
            return properties;
        } catch (IOException e){
            java.lang.String message = "Specify database configuration in database.properties";
            throw new IllegalArgumentException(message);
        }
    }
}
