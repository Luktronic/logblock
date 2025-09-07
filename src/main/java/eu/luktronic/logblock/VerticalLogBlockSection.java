package eu.luktronic.logblock;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class that represents a vertical section inside a log block.
 */
class VerticalLogBlockSection extends LogBlockSection {

    private final String prefix;
    private final List<LogBlockSection> children;

    VerticalLogBlockSection(LogBlockSection... children) {
        this("", children);
    }

    VerticalLogBlockSection(String prefix, LogBlockSection... children) {
        Objects.requireNonNull(children, "Received null children in LogBlockSection constructor!");
        this.children = Arrays.asList(children);
        this.prefix = prefix == null ? "null" : prefix;
    }

    @Override
    public List<LogBlockLine> getLines() {
        val lineCount = lineCount();
        val lines = new ArrayList<LogBlockLine>(lineCount);

        children.stream()
                .map(LogBlockSection::getLines)
                .flatMap(Collection::stream)
                .map(line -> line.prepend(prefix))
                .forEach(lines::add);

        return lines;
    }

    @Override
    int lineCount() {
        return children.stream()
                .mapToInt(LogBlockSection::lineCount)
                .sum();
    }
}
