package eu.luktronic.logblock;

import lombok.val;

import java.util.Arrays;
import java.util.Collections;

/// Class responsible for building the [LogBlockSection] of the left padding using the specified
/// [BorderFormat].
class PaddingLeftSectionBuilder implements SectionBuilder{

    private final LogBlockFormat format;

    PaddingLeftSectionBuilder(LogBlockFormat format) {
        this.format = format;
    }

    @Override
    public LogBlockSection build() {
        val paddingBuilder = new StringBuilder(format.getPaddingLeft());
        for(int i = 0; i < format.getPaddingLeft(); i++) {
            paddingBuilder.append(" ");
        }
        val padding = paddingBuilder.toString();
        return new LogBlockSection(Collections.singletonList(new LogBlockLine(padding)));
    }
}
