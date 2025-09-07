package eu.luktronic.logblock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents a horizontal section inside a log block.
 */
@Slf4j
class HorizontalLogBlockSection {

    private final List<LogBlockLine> lines;

    HorizontalLogBlockSection(List<LogBlockLine> lines) {
        Objects.requireNonNull(lines, "Received null lines in LogBlockSection constructor!");
        this.lines = new ArrayList<>(lines);
    }

    public List<LogBlockLine> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
