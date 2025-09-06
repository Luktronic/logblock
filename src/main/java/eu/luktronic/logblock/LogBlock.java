package eu.luktronic.logblock;

import lombok.val;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class LogBlock {

    private final Logger log;
    private final LogBlockFormat baseFormat;

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
    }

    public void info(String msg, Object... params) {
        executeLogging(log::info, msg, params);
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
        if(msg == null)
            msg = "null";
        val lines = msg.split("\n");
        val prefix = baseFormat.getLinePrefix();
        val padding = buildPaddingLeft(baseFormat.getPaddingLeft());

        val borderLines = getBorderLines(new BorderBuilder(baseFormat.getBorderFormat()).build());
        val borderMsgs = borderLines.stream()
                .map(line -> prefix + line)
                .collect(Collectors.toList());

        val contentMsgs = Arrays.stream(lines)
                .map(line -> prefix + padding + line)
                .collect(Collectors.toList());

        borderMsgs.forEach(borderMsg -> logLine(logConsumer, borderMsg));
        contentMsgs.forEach(contentMsg -> logLine(logConsumer, contentMsg));
        borderMsgs.forEach(borderMsg -> logLine(logConsumer, borderMsg));
    }

    private void logLine(BiConsumer<String, Object[]> logConsumer, String msg, Object... params) {
        logConsumer.accept(msg, params);
    }

    private List<String> getBorderLines(LogBlockSection border) {
        return border.getLines().stream()
                .map(LogBlockLine::getLine)
                .collect(Collectors.toList());
    }

    private String buildPaddingLeft(int paddingLeft) {
        val paddingBuilder = new StringBuilder(paddingLeft);
        for(int i = 0; i < paddingLeft; i++) {
            paddingBuilder.append(" ");
        }
        return paddingBuilder.toString();
    }
}
