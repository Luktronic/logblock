package eu.luktronic.logblock;

import org.slf4j.Logger;

import java.util.Objects;

public class LogBlock {

    private final Logger log;

    /**
     * Creates a new instance of {@link LogBlock} with the passed {@link Logger}.
     * @param logger The SLF4J {@link Logger} that should be used for logging.
     * @throws NullPointerException If the passed {@code logger} is {@code null}.
     */
    public LogBlock(Logger logger) {
        Objects.requireNonNull(logger, "Received null SLF4J Logger in LogBlock constructor!");
        this.log = logger;
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
