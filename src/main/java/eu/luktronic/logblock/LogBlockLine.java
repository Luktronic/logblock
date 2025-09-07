package eu.luktronic.logblock;

import lombok.Getter;
import lombok.val;

import java.util.*;
import java.util.stream.Collectors;

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
        this.params = new ArrayList<>(params);
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

    /// Returns a new [LogBlockLine] that is prepended by the passed `prefixes`.
    /// @return New [LogBlockLine] prepended by the passed `prefixes`.
    public LogBlockLine prepend(String prefix, String... prefixes) {
        val finalPrefix = prefix + String.join("", prefixes);

        return new LogBlockLine(finalPrefix + line, params);
    }

    @Override
    public String toString() {
        return line;
    }
}
