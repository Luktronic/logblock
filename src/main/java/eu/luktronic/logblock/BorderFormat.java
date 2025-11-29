package eu.luktronic.logblock;

import lombok.Getter;

import java.util.Objects;

/**
 * Defines the format of the <b>border</b> of a log block.
 */
@Getter
public class BorderFormat {

    private String delimiter;
    private int length;
    private int thickness;

    public BorderFormat(String delimiter, int length, int thickness) {
        setDelimiter(delimiter);
        setLength(length);
        setThickness(thickness);
    }

    public BorderFormat() {
        this(
                LogBlockConfig.BORDER_DELIMITER,
                LogBlockConfig.BORDER_LENGTH,
                LogBlockConfig.BORDER_THICKNESS
        );
    }

    /**
     * Copy constructor used for internal operations.
     * @param other The format to copy.
     */
    BorderFormat(BorderFormat other) {
        Objects.requireNonNull(other, "Received null format in BlockFormat constructor!");
        this.delimiter = other.delimiter;
        this.length = other.length;
        this.thickness = other.thickness;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = (String) LogBlockProperty.BORDER_DELIMITER.getValueOrDefault(delimiter);
    }

    public void setLength(int length) {
        this.length = (Integer) LogBlockProperty.BORDER_LENGTH.getValueOrDefault(length);
    }

    public void setThickness(int thickness) {
        this.thickness = (Integer) LogBlockProperty.BORDER_THICKNESS.getValueOrDefault(thickness);
    }
}
