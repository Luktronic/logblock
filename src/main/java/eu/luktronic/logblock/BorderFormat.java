package eu.luktronic.logblock;

import lombok.Getter;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Defines the format of the <b>border</b> of a log block.
 */
@Getter
public class BorderFormat {

    private String delimiter;
    private int delimiterCount;
    private int thickness;

    public BorderFormat(String delimiter, int delimiterCount, int thickness) {
        this.delimiter = delimiter;
        this.delimiterCount = delimiterCount;
        this.thickness = thickness;
    }

    public BorderFormat() {
        //TODO: Change values to be queried from configs instead of hard coded
        this(
                "=",
                50,
                1
        );
    }

    /**
     * Copy constructor used for internal operations.
     * @param other The format to copy.
     */
    BorderFormat(BorderFormat other) {
        Objects.requireNonNull(other, "Received null format in BlockFormat constructor!");
        this.delimiter = other.delimiter;
        this.delimiterCount = other.delimiterCount;
        this.thickness = other.thickness;
    }

    /**
     * Builds a single line of the border with this format.
     * <p>
     *     <b>Note:</b> This method will only return a single line of the border. If you want all lines of the border, see {@link #buildBorder()}.
     * </p>
     * @return A single line of the border with this format.
     */
    public String buildBorderLine() {
        val builder = new StringBuilder(delimiterCount);
        for(int i = 0; i < delimiterCount; i++) {
            builder.append(delimiter);
        }
        return builder.toString();
    }

    /**
     * Builds {@code n} lines of the border with this format, where {@code n} is equal to the {@link #getThickness() border thickness}.
     * @return A {@link List} containing all lines of the border with this format.
     */
    public List<String> buildBorder() {
        val allLines = new ArrayList<String>(thickness);
        for(int i = 0; i < thickness; i++) {
            allLines.add(buildBorderLine());
        }
        return allLines;
    }

    public void setDelimiter(String delimiter) {
        //TODO: add validation
        this.delimiter = delimiter;
    }

    public void setDelimiterCount(int delimiterCount) {
        //TODO: add validation
        this.delimiterCount = delimiterCount;
    }

    public void setThickness(int thickness) {
        //TODO: add validation
        this.thickness = thickness;
    }
}
