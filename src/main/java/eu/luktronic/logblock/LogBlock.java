package eu.luktronic.logblock;

import lombok.val;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.function.BiConsumer;

/// Welcome to **LogBlock**!
///
/// This class allows you to print nice-looking blocks of logs using SLF4J.
///
/// These Javadocs will give you a quick overview on how to use *LogBlock*.
/// For more in-depth documentation, take a look at the [GitHub repository](https://github.com/Luktronic/logblock).
///
/// ## How to use
///
/// First, create an instance of this `LogBlock` class using either the constructor or the
/// more fluent-API-like [#withLogger(Logger)] method:
/// 
/// ```java
/// Logger log = LoggerFactory.getLogger(MyClass.class);
/// LogBlock logBlock = LogBlock.withLogger(log);
/// logBlock = new LogBlock(log); // This is also possible
/// ```
///
/// You can then use the regular log methods that you already know from your SLF4J [Logger]:
/// ```java
///     logBlock.error("Hello World from LogBlock!");
/// ```
///
/// The following lines will be logged:
/// ```text
/// [INFO] MyClass - |================================================================================
/// [INFO] MyClass - |
/// [INFO] MyClass - |  Hello World from LogBlock!
/// [INFO] MyClass - |
/// [INFO] MyClass - |================================================================================
/// ```
///
/// ### Multiline messages
///
/// LogBlock will parse every `\n` inside of your log message,
/// and treat the new line as a separate log statement.
///
/// Example for a multiline log message:
/// ```java
/// String hello = "Hello";
/// String world = "World";
/// logBlock.info("To everyone who reads this,\n" +
///         "I wish to you a very dear:\n" +
///         "{} {}!", hello, world);
///
/// // If using JDK 15+, you can also use a text block for easier formatting!
/// logBlock.info("""
///        To everyone who reads this,
///        I wish to you a very dear:
///        {} {}!
///        """, hello, world);
/// ```
///
/// The log message will look like this:
///
/// ```text
/// [INFO] MyClass - |================================================================================
/// [INFO] MyClass - |
/// [INFO] MyClass - |  To everyone who reads this,
/// [INFO] MyClass - |  I wish to you a very dear:
/// [INFO] MyClass - |  Hello World!
/// [INFO] MyClass - |
/// [INFO] MyClass - |================================================================================
/// ```
///
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

    /// @see LogBlockSystemProperties#BORDER_DELIMITER
    public LogBlock withBorderDelimiter(String delimiter) {
        tempFormat.getBorderFormat().setDelimiter(delimiter);
        return this;
    }

    /// @see LogBlockSystemProperties#BORDER_LENGTH
    public LogBlock withBorderLength(int length) {
        tempFormat.getBorderFormat().setLength(length);
        return this;
    }

    /// @see LogBlockSystemProperties#BORDER_THICKNESS
    public LogBlock withBorderThickness(int thickness) {
        tempFormat.getBorderFormat().setThickness(thickness);
        return this;
    }

    /// @see LogBlockSystemProperties#LINE_PREFIX
    public LogBlock withLinePrefix(String linePrefix) {
        tempFormat.setLinePrefix(linePrefix);
        return this;
    }

    /// @see LogBlockSystemProperties#PADDING_LEFT
    public LogBlock withPaddingLeft(int paddingLeft) {
        tempFormat.setPaddingLeft(paddingLeft);
        return this;
    }

    /// @see LogBlockSystemProperties#PADDING_TOP
    public LogBlock withPaddingTop(int paddingTop) {
        tempFormat.setPaddingTop(paddingTop);
        return this;
    }

    /// @see LogBlockSystemProperties#PADDING_BOTTOM
    public LogBlock withPaddingBottom(int paddingBottom) {
        tempFormat.setPaddingBottom(paddingBottom);
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
        val lines = new LogBlockSections(tempFormat, msg, params).getRootSection().getLines();
        lines.forEach(line -> logLine(logConsumer, line));
        tempFormat = new LogBlockFormat(baseFormat);
    }

    private void logLine(BiConsumer<String, Object[]> logConsumer, LogBlockLine line) {
        logConsumer.accept(line.getLine(), line.getParams().toArray());
    }

}
