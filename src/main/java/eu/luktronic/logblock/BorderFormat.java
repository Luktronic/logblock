package eu.luktronic.logblock;

import lombok.Getter;

import java.util.Objects;

/// Defines the format of the **border** of a log block.
///
/// To create an instance, use the available constructors:
/// 1. [#BorderFormat()] for default format.
/// 2. [#BorderFormat(String, int, int)] for custom format.
/// ```java
/// BorderFormat defaultFormat = new BorderFormat();
/// BorderFormat customFormat = new BorderFormat("-", 100, 1);
/// ```
@Getter
public class BorderFormat {

    private String delimiter;
    private int length;
    private int thickness;

    /// Constructs an instance of this class using the passed values
    /// or their default values if an invalid value is passed.
    ///
    /// All configurable properties and their default values are defined as static constants
    /// in the [LogBlockSystemProperties] class.
    public BorderFormat(String delimiter, int length, int thickness) {
        setDelimiter(delimiter);
        setLength(length);
        setThickness(thickness);
    }

    /// Constructs an instance of this class using default formatting.
    ///
    /// The default values are:
    /// - Delimiter: `=`
    /// - Length: 80
    /// - Thickness: 1
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

    /// Sets the border delimiter to the passed value, or falls back
    /// to the default if an invalid value is passed.
    ///
    /// The delimiter is the String used for the border itself.
    ///
    /// Expects: non-null and non-blank [String]<br>
    /// If set to blank [String], will default to `=`.
    ///
    /// In this example, the delimiter would be `+`:
    /// ```text
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// [INFO] | This is my LogBlock!
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// ```
    public void setDelimiter(String delimiter) {
        this.delimiter = (String) LogBlockProperty.BORDER_DELIMITER.getValueOrDefault(delimiter);
    }

    /// Sets the border length to the passed value, or falls back
    /// to the default if an invalid value is passed.
    ///
    /// The length specifies how many times the [delimiter][#setDelimiter(String)] will be repeated.
    ///
    /// Expects: `int` > 0<br>
    /// If set to value <=0, will default to `80`.
    ///
    /// In this example, the length would be `20` (leading to 20x`=` being logged):
    /// ```text
    /// [INFO] |====================
    /// [INFO] | This is my LogBlock!
    /// [INFO] |====================
    /// ```
    public void setLength(int length) {
        this.length = (Integer) LogBlockProperty.BORDER_LENGTH.getValueOrDefault(length);
    }

    /// Sets the border thickness to the passed value, or falls back
    /// to the default if an invalid value is passed.
    ///
    /// The thickness specifies how many lines "thick" the border should be
    /// (meaning how often the border-line gets logged).
    ///
    /// Expects: `int` > 0<br>
    /// If set to value <=0, will default to `1`.
    ///
    /// In this example, the thickness would be `3` (leading to 3 lines of border being logged):
    /// ```text
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// [INFO] |
    /// [INFO] | This is my LogBlock!
    /// [INFO] |
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// ```
    public void setThickness(int thickness) {
        this.thickness = (Integer) LogBlockProperty.BORDER_THICKNESS.getValueOrDefault(thickness);
    }
}
