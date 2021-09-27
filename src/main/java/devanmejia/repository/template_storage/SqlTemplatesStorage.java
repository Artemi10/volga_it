package devanmejia.repository.template_storage;

import java.util.HashMap;
import java.util.Map;

public class SqlTemplatesStorage {
    private static final SqlTemplatesStorage TEMPLATE_STORAGE= new SqlTemplatesStorage();
    private final Map<String, String> templateStorage;

    private SqlTemplatesStorage() {
        templateStorage = new HashMap<>();
        templateStorage.put(SqlType.INSERT_DOCUMENT.name(),
                "INSERT INTO my_schema.documents (path) VALUE (?);");
        templateStorage.put(SqlType.INSERT_STATS.name(),
                "INSERT INTO my_schema.statistics \n" +
                "(word, amount, document_id) VALUE (?, ?, ?);");
        templateStorage.put(SqlType.SELECT_STATS.name(),
                "SELECT word, amount, path \n" +
                "FROM my_schema.statistics \n" +
                "LEFT JOIN documents d on d.id = statistics.document_id \n" +
                "WHERE document_id = ?;");
    }

    public static SqlTemplatesStorage getInstance(){
        return TEMPLATE_STORAGE;
    }

    public String getTemplate(SqlType type){
        return templateStorage.get(type.name());
    }
}
