package eu.luktronic.logblock;

import lombok.val;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.function.BiConsumer;

public class LogBlock {

    private final Logger log;
    private final LogBlockFormat baseFormat;
    private LogBlockFormat tempFormat;

    /**
     * Creates a new instance of {@link LogBlock} with the passed {@link Logger} and the default {@link LogBlockFormat} as the base format.
     *
     * <p>
     *     For more information on the base format, see {@link LogBlock}.
     * </p>
     * @param logger The SLF4J {@link Logger} that should be used for logging.
     * @throws NullPointerException If the passed {@code logger} is {@code null}.
     */
    public LogBlock(Logger logger) {
        this(logger, new LogBlockFormat());
    }

    /**
     * Creates a new instance of {@link LogBlock} with the passed {@link Logger} and the passed {@link LogBlockFormat} as the base format.
     *
     * <p>
     *     For more information on the base format, see {@link LogBlock}.
     * </p>
     * @param logger The SLF4J {@link Logger} that should be used for logging.
     * @param blockFormat The {@link LogBlockFormat} to use for the formatting of the log block.
     * @throws NullPointerException If either the passed {@code logger} or {@code blockFormat} are {@code null}.
     */
    public LogBlock(Logger logger, LogBlockFormat blockFormat) {
        Objects.requireNonNull(logger, "Received null SLF4J Logger in LogBlock constructor!");
        Objects.requireNonNull(blockFormat, "Received null blockFormat in LogBlock constructor!");
        this.log = logger;
        this.baseFormat = new LogBlockFormat();
        this.tempFormat = new LogBlockFormat(baseFormat);
    }

    public void trace(String msg, Object... params) {
        executeLogging(log::trace, msg, params);
    }

    public void debug(String msg, Object... params) {
        executeLogging(log::debug, msg, params);
    }

    public void info(String msg, Object... params) {
        executeLogging(log::info, msg, params);
    }

    public void warn(String msg, Object... params) {
        executeLogging(log::warn, msg, params);
    }

    public void error(String msg, Object... params) {
        executeLogging(log::error, msg, params);
    }

    public LogBlock withBorderDelimiter(String delimiter) {
        tempFormat.getBorderFormat().setDelimiter(delimiter);
        return this;
    }

    public LogBlock withBorderDelimiterCount(int delimiterCount) {
        tempFormat.getBorderFormat().setDelimiterCount(delimiterCount);
        return this;
    }

    public LogBlock withBorderThickness(int thickness) {
        tempFormat.getBorderFormat().setThickness(thickness);
        return this;
    }

    public LogBlock withPaddingLeft(int paddingLeft) {
        tempFormat.setPaddingLeft(paddingLeft);
        return this;
    }

    public LogBlock withLinePrefix(String linePrefix) {
        tempFormat.setLinePrefix(linePrefix);
        return this;
    }

    /**
     * Creates a new {@link LogBlock} instance providing a more fluent-API-like style.
     * @param logger The SLF4J {@link Logger} that should be used for logging.
     * @return A new {@link LogBlock} instance.
     * @throws NullPointerException If the passed {@code logger} is {@code null}.
     */
    public static LogBlock withLogger(Logger logger) {
        return new LogBlock(logger);
    }

    private void executeLogging(BiConsumer<String, Object[]> logConsumer, String msg, Object... params) {
        val lines = new LogBlockSections(tempFormat, msg, params).getPrefixSection().getLines();
        lines.forEach(line -> logLine(logConsumer, line));
        tempFormat = new LogBlockFormat(baseFormat);
    }

    private void logLine(BiConsumer<String, Object[]> logConsumer, LogBlockLine line) {
        logConsumer.accept(line.getLine(), line.getParams().toArray());
    }

}
