package devanmejia.service.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;


class HTMLLineParserTest {
    private LineParser lineParser;

    @BeforeEach
    public void initialize(){
        lineParser = new HTMLLineParser();
    }

    @Test
    void parse_empty_line_test() {
        String emptyLine1 = lineParser.parse("").collect(Collectors.joining());
        String emptyLine2 = lineParser.parse(" ").collect(Collectors.joining());
        Assertions.assertEquals("", emptyLine1);
        Assertions.assertEquals("", emptyLine2);
    }

    @Test
    void parse_empty_text_test() {
        String emptyLine1 = lineParser.parse("<div></div>").collect(Collectors.joining());
        String emptyLine2 = lineParser.parse("<div> </div>").collect(Collectors.joining());
        String emptyLine3 = lineParser.parse("<div class=\"row\"> </div>").collect(Collectors.joining());
        String emptyLine4 = lineParser.parse("<input type=\"text\">").collect(Collectors.joining());
        String emptyLine5 = lineParser.parse("<div>&nbsp; </div>").collect(Collectors.joining());
        String emptyLine6 = lineParser.parse("<div>&nbsp;</div>").collect(Collectors.joining());
        Assertions.assertEquals("", emptyLine1);
        Assertions.assertEquals("", emptyLine2);
        Assertions.assertEquals("", emptyLine3);
        Assertions.assertEquals("", emptyLine4);
        Assertions.assertEquals("", emptyLine5);
        Assertions.assertEquals("", emptyLine6);
    }

    @Test
    void parse_text_with_special_symbols_test(){
        String emptyLine1 = lineParser.parse("<div>How&nbsp;are&nbsp;you</div>").collect(Collectors.joining(", "));
        String emptyLine2 = lineParser.parse("<div>How&nbsp;are</div>").collect(Collectors.joining(", "));
        String emptyLine3 = lineParser.parse("<div>&nbsp;How&nbsp;&nbsp;&nbsp;are&nbsp;</div>").collect(Collectors.joining(", "));
        Assertions.assertEquals("How, are, you", emptyLine1);
        Assertions.assertEquals("How, are", emptyLine2);
        Assertions.assertEquals("How, are", emptyLine3);
    }

    @Test
    void parse_text_with_nested_tags_test(){
        String emptyLine1 = lineParser.parse("<div>How <span>are</span> you</div>").collect(Collectors.joining(", "));
        String emptyLine2 = lineParser.parse("<div>How<span>are</span> you</div>").collect(Collectors.joining(", "));
        String emptyLine3 = lineParser.parse("<div>How<span>are</span>you</div>").collect(Collectors.joining(", "));
        String emptyLine4 = lineParser.parse("<div>How&nbsp;<span>are</span>&nbsp;you</div>").collect(Collectors.joining(", "));
        Assertions.assertEquals("How, are, you", emptyLine1);
        Assertions.assertEquals("Howare, you", emptyLine2);
        Assertions.assertEquals("Howareyou", emptyLine3);
        Assertions.assertEquals("How, are, you", emptyLine4);
    }
}
