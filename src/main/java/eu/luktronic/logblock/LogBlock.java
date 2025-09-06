package eu.luktronic.logblock;

import lombok.val;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public void info(String msg) {
        if(msg == null)
            msg = "null";
        val lines = msg.split("\n");
        val prefix = baseFormat.getLinePrefix();
        val paddingBuilder = new StringBuilder(baseFormat.getPaddingLeft());
        for(int i = 0; i < baseFormat.getPaddingLeft(); i++) {
            paddingBuilder.append(" ");
        }
        val padding = paddingBuilder.toString();
        val border = baseFormat.getBorderFormat().buildBorder();
        border.forEach(line -> log.info("{}{}", prefix, line));
        Arrays.stream(lines)
                .forEach(line -> log.info("{}{}{}", prefix, padding, line));
        border.forEach(line -> log.info("{}{}", prefix, line));
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


}
