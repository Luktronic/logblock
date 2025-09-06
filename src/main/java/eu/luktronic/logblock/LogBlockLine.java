package eu.luktronic.logblock;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a single line inside a log block.
 */
class LogBlockLine {

    @Getter
    private final String line;
    private final List<Object> params;

    LogBlockLine(String line, List<Object> params) {
        Objects.requireNonNull(params, "Received null params in LogBlockLine constructor!");

        this.line = line;
        this.params = params;
    }

    /**
     * Creates a {@link LogBlockLine} without any parameters.
     *
     * <p>
     *     <b>Note:</b> If the passed {@code line} contains any anchors ({@code {}}), they will simply be included out as plain {@code "{}"}.
     * </p>
     * @param line The text of the block line.
     */
    LogBlockLine(String line) {
        this(line, new ArrayList<>(0));
    }

    public List<Object> getParams() {
        return Collections.unmodifiableList(params);
    }

    @Override
    public String toString() {
        return line;
    }
}
