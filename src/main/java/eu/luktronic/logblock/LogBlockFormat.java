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

    /// Sets the line prefix to the passed value, or falls back
    /// to the default if an invalid value is passed.
    ///
    /// The line prefix is the [String] that will be printed at
    /// the start of every line of the LogBlock.
    ///
    /// Expects: [String]<br>
    /// If set to blank [String], will default to `|`.
    ///
    /// In this example, the line prefix would be `|`:
    /// ```text
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// [INFO] | This is my LogBlock!
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// ```
    public void setLinePrefix(String linePrefix) {
        this.linePrefix = (String) LogBlockProperty.LINE_PREFIX.getValueOrDefault(linePrefix);
    }

    /// Sets the left padding to the passed value, or falls back
    /// to the default if an invalid value is passed.
    ///
    /// The left padding specifies how many whitespace characters should be printed before the actual
    /// log message.<br>
    /// The border <b>is not</b> affected by this!
    /// Only the message that you pass to the LogBlock logger itself will get
    /// spaced.
    ///
    /// Expects: `int` >= 0<br>
    /// If set to value <0, will default to `2`.
    ///
    /// In this example, the left padding would be `4` (leading to 4 whitespaces being printed before the actual message):
    /// ```text
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// [INFO] |
    /// [INFO] |    This is my LogBlock!
    /// [INFO] |    And this is my message for the block!
    /// [INFO] |      Keep in mind that a line of a message may still start with whitespaces!
    /// [INFO] |      For example, this line starts with 2 whitespaces.
    /// [INFO] |      But the padding moves the entire message to the right by 4 whitespaces,
    /// [INFO] |      so the spaces add up to 6!
    /// [INFO] |    But a normal message that starts without any spaces looks like this.
    /// [INFO] |
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// ```
    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = (Integer) LogBlockProperty.PADDING_LEFT.getValueOrDefault(paddingLeft);
    }

    /// Sets the top padding to the passed value, or falls back
    /// to the default if an invalid value is passed.
    ///
    /// The top padding specifies how many lines should be logged after the top border, but before the actual
    /// log message.<br>
    ///
    /// Expects: `int` >= 0<br>
    /// If set to value <0, will default to `1`.
    ///
    /// In this example, the top padding would be `2` (leading to 2 lines logged before the actual message):
    /// ```text
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// [INFO] |
    /// [INFO] |
    /// [INFO] |    This is my LogBlock!
    /// [INFO] |
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// ```
    public void setPaddingTop(int paddingTop) {
        this.paddingTop = (Integer) LogBlockProperty.PADDING_TOP.getValueOrDefault(paddingTop);
    }

    /// Sets the bottom padding to the passed value, or falls back
    /// to the default if an invalid value is passed.
    ///
    /// The bottom padding specifies how many lines should be logged after the log message, but before the
    /// bottom border.<br>
    ///
    /// Expects: `int` >= 0<br>
    /// If set to value <0, will default to `1`.
    ///
    /// In this example, the bottom padding would be `2` (leading to 2 lines logged after the actual message):
    /// ```text
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// [INFO] |
    /// [INFO] |    This is my LogBlock!
    /// [INFO] |
    /// [INFO] |
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// ```
    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = (Integer) LogBlockProperty.PADDING_BOTTOM.getValueOrDefault(paddingBottom);
    }

    /// Sets the [BorderFormat].
    /// This class contains all possible format configurations for the
    /// LogBlock's border.
    /// @throws NullPointerException If `borderFormat` is `null`
    /// @see BorderFormat
    public void setBorderFormat(BorderFormat borderFormat) {
        Objects.requireNonNull(borderFormat, "Received null borderFormat in LogBlockFormat.setBorderFormat!");
        this.borderFormat = borderFormat;
    }
}
