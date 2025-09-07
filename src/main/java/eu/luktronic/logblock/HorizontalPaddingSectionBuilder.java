package eu.luktronic.logblock;

import lombok.val;

import java.util.ArrayList;

/// Class responsible for building the [LogBlockSection] of horizontal paddings (top/bottom) using the specified
/// [BorderFormat].
class HorizontalPaddingSectionBuilder implements SectionBuilder{

    private final int padding;

    HorizontalPaddingSectionBuilder(int padding) {
        this.padding = padding;
    }

    @Override
    public LogBlockSection build() {
        val lines = new ArrayList<LogBlockLine>(padding);
        for(int i = 0; i < padding; i++) {
            lines.add(new LogBlockLine(""));
        }
        return new LogBlockSection(lines);
    }
}
