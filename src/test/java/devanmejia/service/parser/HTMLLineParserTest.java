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
    public void parse_empty_line_test() {
        String emptyLine1 = lineParser.parse("").collect(Collectors.joining());
        String emptyLine2 = lineParser.parse(" ").collect(Collectors.joining());
        Assertions.assertEquals("", emptyLine1);
        Assertions.assertEquals("", emptyLine2);
    }

    @Test
    public void parse_empty_text_test() {
        String line1 = lineParser.parse("<div></div>").collect(Collectors.joining());
        String line2 = lineParser.parse("<div> </div>").collect(Collectors.joining());
        String line3 = lineParser.parse("<div class=\"row\"> </div>").collect(Collectors.joining());
        String line4 = lineParser.parse("<input type=\"text\">").collect(Collectors.joining());
        String line5 = lineParser.parse("<div>&nbsp; </div>").collect(Collectors.joining());
        String line6 = lineParser.parse("<div>&nbsp;</div>").collect(Collectors.joining());
        Assertions.assertEquals("", line1);
        Assertions.assertEquals("", line2);
        Assertions.assertEquals("", line3);
        Assertions.assertEquals("", line4);
        Assertions.assertEquals("", line5);
        Assertions.assertEquals("", line6);
    }

    @Test
    public void parse_text_with_special_symbols_test(){
        String line1 = lineParser.parse("<div>How&nbsp;are&nbsp;you</div>").collect(Collectors.joining(", "));
        String line2 = lineParser.parse("<div>How&nbsp;are</div>").collect(Collectors.joining(", "));
        String line3 = lineParser.parse("<div>&nbsp;How&nbsp;&nbsp;&nbsp;are&nbsp;</div>").collect(Collectors.joining(", "));
        Assertions.assertEquals("How, are, you", line1);
        Assertions.assertEquals("How, are", line2);
        Assertions.assertEquals("How, are", line3);
    }

    @Test
    public void parse_text_with_nested_tags_test(){
        String line1 = lineParser.parse("<div>How <span>are</span> you</div>").collect(Collectors.joining(", "));
        String line2 = lineParser.parse("<div>How<span>are</span> you</div>").collect(Collectors.joining(", "));
        String line3 = lineParser.parse("<div>How<span>are</span>you</div>").collect(Collectors.joining(", "));
        String line4 = lineParser.parse("<div>How&nbsp;<span>are</span>&nbsp;you</div>").collect(Collectors.joining(", "));
        String line5 = lineParser.parse("<li>&nbsp;Chapter 5 of Roy Fielding&rsquo;s dissertation&nbsp;<a rel=\"nofollow\" href=\"http://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm\">&laquo;Representational State Transfer (REST)&raquo;</a></li>\n")
                .collect(Collectors.joining(", "));
        Assertions.assertEquals("How, are, you", line1);
        Assertions.assertEquals("Howare, you", line2);
        Assertions.assertEquals("Howareyou", line3);
        Assertions.assertEquals("How, are, you", line4);
        Assertions.assertEquals("Chapter, 5, of, Roy, Fielding’s, dissertation, State, Transfer, REST", line5);
    }

}
