package devanmejia.service.parser;

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals("", emptyLine1);
        assertEquals("", emptyLine2);
    }

    @Test
    public void parse_empty_text_test() {
        String line1 = lineParser.parse("<div></div>").collect(Collectors.joining());
        String line2 = lineParser.parse("<div> </div>").collect(Collectors.joining());
        String line3 = lineParser.parse("<div class=\"row\"> </div>").collect(Collectors.joining());
        String line4 = lineParser.parse("<input type=\"text\">").collect(Collectors.joining());
        String line5 = lineParser.parse("<div>&nbsp; </div>").collect(Collectors.joining());
        String line6 = lineParser.parse("<div>&nbsp;</div>").collect(Collectors.joining());
        assertEquals("", line1);
        assertEquals("", line2);
        assertEquals("", line3);
        assertEquals("", line4);
        assertEquals("", line5);
        assertEquals("", line6);
    }

    @Test
    public void parse_text_with_special_symbols_test(){
        String line1 = lineParser.parse("<div>How&nbsp;are&nbsp;you</div>").collect(Collectors.joining(", "));
        String line2 = lineParser.parse("<div>How&nbsp;are</div>").collect(Collectors.joining(", "));
        String line3 = lineParser.parse("<div>&nbsp;How&nbsp;&nbsp;&nbsp;are&nbsp;</div>").collect(Collectors.joining(", "));
        assertEquals("How, are, you", line1);
        assertEquals("How, are", line2);
        assertEquals("How, are", line3);
    }

    @Test
    public void parse_text_with_nested_tags_test(){
        String line1 = lineParser.parse("<div>How <span>are</span> you</div>").collect(Collectors.joining(", "));
        String line2 = lineParser.parse("<div>How<span>are</span> you</div>").collect(Collectors.joining(", "));
        String line3 = lineParser.parse("<div>How<span>are</span>you</div>").collect(Collectors.joining(", "));
        String line4 = lineParser.parse("<div>How&nbsp;<span>are</span>&nbsp;you</div>").collect(Collectors.joining(", "));
        String line5 = lineParser.parse("<li>&nbsp;Chapter 5 of Roy Fielding&rsquo;s dissertation&nbsp;<a rel=\"nofollow\" href=\"http://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm\">&laquo;Representational State Transfer (REST)&raquo;</a></li>\n")
                .collect(Collectors.joining(", "));
        assertEquals("How, are, you", line1);
        assertEquals("Howare, you", line2);
        assertEquals("Howareyou", line3);
        assertEquals("How, are, you", line4);
        assertEquals("Chapter, 5, of, Roy, Fielding’s, dissertation, State, Transfer, REST", line5);
    }

}
