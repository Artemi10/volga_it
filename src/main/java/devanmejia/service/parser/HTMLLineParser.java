package devanmejia.service.parser;


import org.apache.commons.text.*;

import java.util.Arrays;
import java.util.stream.Stream;

public class HTMLLineParser implements LineParser {
    @Override
    public Stream<String> parse(String line){
        line = StringEscapeUtils.unescapeHtml4(line) + "</tag>";
        return Arrays.stream(line.replaceAll("<.*?>", "")
                        .replaceAll(String.valueOf((char) 160), " ")
                        .split(",|\\.|!|\\?|\"|;|:|\\[|]|\\(|\\)|\\s"))
                .map(String::trim)
                // check if word is not empty and contains at least one letter or number
                .filter(word -> !word.equals("") && word.matches(".*\\d|[a-zA-Zа-яА-Я]+.*"));
    }
}
