package eu.luktronic.logblock;

import lombok.Getter;
import lombok.val;

import java.util.Objects;

/// Defines the format that should be used when a [LogBlock] is being logged.
///
///
/// A Format includes things like:
///
///   - block border
///   - line prefix
///   - top/bottom/left padding
///
/// ...and many more!
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
        this.linePrefix = LogBlockConfig.LINE_PREFIX;
        this.paddingTop = LogBlockConfig.PADDLING_TOP;
        this.paddingBottom = LogBlockConfig.PADDLING_BOTTOM;
        this.paddingLeft = LogBlockConfig.PADDLING_LEFT;
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
        this.linePrefix = (String) LogBlockProperty.LINE_PREFIX.getValueOrDefault(linePrefix);
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = (Integer) LogBlockProperty.PADDING_LEFT.getValueOrDefault(paddingLeft);
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = (Integer) LogBlockProperty.PADDING_TOP.getValueOrDefault(paddingTop);
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = (Integer) LogBlockProperty.PADDING_BOTTOM.getValueOrDefault(paddingBottom);
    }

    /// @throws NullPointerException If `borderFormat` is `null`
    public void setBorderFormat(BorderFormat borderFormat) {
        Objects.requireNonNull(borderFormat, "Received null borderFormat in LogBlockFormat.setBorderFormat!");
        this.borderFormat = borderFormat;
    }
}
