package eu.luktronic.logblock;

import lombok.Getter;

import java.util.Objects;

/**
 * Defines the format that should be used when a {@link LogBlock} is being logged.
 *
 * <p>
 * A Format includes things like:
 * <ul>
 *     <li>block border</li>
 *     <li>line prefix</li>
*     <li>top/bottom/left padding</li>
 * </ul>
 * ...and many more!
 * </p>
 */
@Getter
public class LogBlockFormat {

    private BorderFormat borderFormat;
    private String linePrefix;
    private int paddingLeft;

    /**
     * Creates an instance with all default formats.
     */
    public LogBlockFormat() {
        this.borderFormat = new BorderFormat();
        //TODO: change value to be read from config
        this.linePrefix = "|";
        this.paddingLeft = 1;
    }

    /**
     * Copy constructor used for internal operations.
     * @param other The format to copy.
     */
    LogBlockFormat(LogBlockFormat other) {
        Objects.requireNonNull(other, "Received null format in LogBlockFormat constructor!");
        this.borderFormat = new BorderFormat(other.borderFormat);
        this.linePrefix = other.linePrefix;
    }
}
