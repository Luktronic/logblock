package eu.luktronic.logblock;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

/**
 * Class that represents a section inside a log block.
 */
@Slf4j
class LogBlockSection {

    LogBlockSection(List<LogBlockLine> lines) {
        Objects.requireNonNull(lines, "Received null lines in LogBlockSection constructor!");
    }
}
