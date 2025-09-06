package eu.luktronic.logblock;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents a section inside a log block.
 */
@Slf4j
class LogBlockSection {

    private final List<String> lines;
    private final List<List<Object>> lineParams;

    LogBlockSection(List<String> lines, List<List<Object>> lineParams) {
        Objects.requireNonNull(lines, "Received null lines in LogBlockSection constructor!");
        Objects.requireNonNull(lineParams, "Received null lineParams in LogBlockSection constructor!");

        val lineCount = lines.size();
        this.lines = new ArrayList<>(lineCount);
        this.lines.addAll(lines);
        this.lineParams = new ArrayList<>(lineParams);
    }
}
