package devanmejia.service.parser;


import java.util.Arrays;
import java.util.stream.Stream;

public class HTMLLineParser implements LineParser {
    @Override
    public Stream<String> parse(String line){
        line += "</tag>";
        return Arrays.stream(line.replaceAll("<.*?>", "")
                        .split("&([^;]*);|\\s|[^a-zA-Z0-9А-Яа-я]"))
                .filter(word -> !word.trim().equals(""));
    }
}
