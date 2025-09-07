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
    private int paddingTop;
    private int paddingLeft;
    private int paddingBottom;

    /**
     * Creates an instance with all default formats.
     */
    public LogBlockFormat() {
        this.borderFormat = new BorderFormat();
        //TODO: change value to be read from config
        this.linePrefix = "|";
        this.paddingTop = 1;
        this.paddingBottom = 1;
        this.paddingLeft = 2;
    }

    /**
     * Copy constructor used for internal operations.
     * @param other The format to copy.
     */
    LogBlockFormat(LogBlockFormat other) {
        Objects.requireNonNull(other, "Received null format in LogBlockFormat constructor!");
        this.borderFormat = new BorderFormat(other.borderFormat);
        this.linePrefix = other.linePrefix;
        this.paddingTop = other.paddingTop;
        this.paddingBottom = other.paddingBottom;
        this.paddingLeft = other.paddingLeft;
    }

    public void setLinePrefix(String linePrefix) {
        //TODO: add validation
        this.linePrefix = linePrefix;
    }

    public void setPaddingLeft(int paddingLeft) {
        //TODO: add validation
        this.paddingLeft = paddingLeft;
    }

    public void setPaddingTop(int paddingTop) {
        //TODO: add validation
        this.paddingTop = paddingTop;
    }

    public void setPaddingBottom(int paddingBottom) {
        //TODO: add validation
        this.paddingBottom = paddingBottom;
    }
}
