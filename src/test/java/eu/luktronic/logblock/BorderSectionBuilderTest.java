package eu.luktronic.logblock;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class BorderSectionBuilderTest {

    @Nested
    @DisplayName("Constructor")
    class Constructor {

        @Nested
        @DisplayName("with null BorderFormat")
        class WithNullBorderFormat {

            @Test
            @DisplayName("should throw NullPointerException")
            void shouldThrowNullPointerException() {
                assertThatNullPointerException()
                        .isThrownBy(() -> new BorderSectionBuilder(null));
            }
        }
    }

    @Nested
    @DisplayName("build()")
    class Build {
        final BorderFormat format = new BorderFormat("=", 30, 4);
        final BorderSectionBuilder borderSectionBuilder = new BorderSectionBuilder(format);

        @Nested
        @DisplayName("should successfully return section")
        class ShouldReturnSection {
            LogBlockSection section = borderSectionBuilder.build();

            @Test
            @DisplayName("with correct thickness")
            void withCorrectThickness() {
                assertThatList(section.getLines())
                        .hasSize(format.getThickness());
            }

            @Test
            @DisplayName("with correct delimiter")
            void withCorrectDelimiter() {
                val delimiterOccurrence = Pattern.quote(format.getDelimiter());

                // Replace all the occurrences of the delimiter with ""
                // If no other characters were used, this should leave back an empty string
                val replacedLines = section.getLines().stream()
                        .map(LogBlockLine::getLine)
                        .map(line -> line.replaceAll(delimiterOccurrence, ""));

                assertThatStream(replacedLines)
                        .containsOnly("");
            }

            @Test
            @DisplayName("with correct delimiter count")
            void withCorrectDelimiterCount() {
                val delimiter = Pattern.quote(format.getDelimiter());

                val delimiterCounts = section.getLines().stream()
                        .map(LogBlockLine::getLine)
                        .map(StringUtils::new)
                        .map(str -> str.countOccurrencesOf(delimiter))
                        .collect(Collectors.toList());

                assertThatList(delimiterCounts)
                        .allMatch(count -> count.equals(format.getDelimiterCount()));
            }
        }
    }

}