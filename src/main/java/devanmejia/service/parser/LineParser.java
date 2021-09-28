package devanmejia.service.parser;

import java.util.stream.Stream;

public interface LineParser {
    Stream<String> parse(String line);
}
