package eu.luktronic.logblock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents a horizontal section inside a log block.
 */
class HorizontalLogBlockSection extends LogBlockSection {

    private final List<LogBlockLine> lines;

    HorizontalLogBlockSection(List<LogBlockLine> lines) {
        Objects.requireNonNull(lines, "Received null lines in LogBlockSection constructor!");
        this.lines = new ArrayList<>(lines);
    }

    @Override
    public List<LogBlockLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    @Override
    int lineCount() {
        return lines.size();
    }
}
