package eu.luktronic.logblock;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BorderFormatTest {

    @Test
    void ensureBuildLineWorks() {
        val format = new BorderFormat("=", 5, 10);

        val borderLine = format.buildBorderLine();
        assertThat(borderLine).isEqualTo("=====");
    }

    @Test
    void ensureBuildBorderWorks() {
        val thickness = 10;
        val format = new BorderFormat("=", 5, thickness);

        val borderLine = format.buildBorder();
        assertThatList(borderLine).hasSize(thickness);
        assertThatList(borderLine).allMatch(str -> str.equals("====="));
    }
}