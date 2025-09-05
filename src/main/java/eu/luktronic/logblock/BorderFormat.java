package eu.luktronic.logblock;

import lombok.Getter;
import lombok.Setter;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the format of the <b>border</b> of a log block.
 */
@Getter
public class BorderFormat {

    private String borderString;
    private int borderStringRepeat;
    private int thickness;

    public BorderFormat(String borderString, int borderStringRepeat, int thickness) {
        this.borderString = borderString;
        this.borderStringRepeat = borderStringRepeat;
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
     * Builds a single line of the border with this format.
     * <p>
     *     <b>Note:</b> This method will only return a single line of the border. If you want all lines of the border, see {@link #buildBorder()}.
     * </p>
     * @return A single line of the border with this format.
     */
    public String buildBorderLine() {
        val builder = new StringBuilder(borderStringRepeat);
        for(int i = 0; i < borderStringRepeat; i++) {
            builder.append(borderString);
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

    public void setBorderString(String borderString) {
        //TODO: add validation
        this.borderString = borderString;
    }

    public void setBorderStringRepeat(int borderStringRepeat) {
        //TODO: add validation
        this.borderStringRepeat = borderStringRepeat;
    }

    public void setThickness(int thickness) {
        //TODO: add validation
        this.thickness = thickness;
    }
}
