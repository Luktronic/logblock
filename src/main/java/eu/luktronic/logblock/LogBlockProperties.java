package eu.luktronic.logblock;

/// Defines the system properties used for the [LogBlockConfig].
public class LogBlockProperties {

    private static final String PREFIX = "eu.luktronic.logblock";

    private static final String BORDER = PREFIX + ".border";
    /// The String used for the border itself.
    ///
    /// Expects: [String]<br>
    /// If set to blank [String], will default to `=`.
    ///
    /// In this example, the delimiter would be `+`:
    /// ```text
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// [INFO] | This is my LogBlock!
    /// [INFO] |++++++++++++++++++++++++++++++++++++++
    /// ```
    public static final String BORDER_DELIMITER = BORDER + ".delimiter";
    /// Specifies how many times the [delimiter][#BORDER_DELIMITER] will be repeated.
    ///
    /// Expects: `int` > 0<br>
    /// If set to value <=0, will default to `30`.
    ///
    /// In this example, the length would be `20` (leading to 20x`=` being logged):
    /// ```text
    /// [INFO] |====================
    /// [INFO] | This is my LogBlock!
    /// [INFO] |====================
    /// ```
    public static final String BORDER_LENGTH = BORDER + ".length";
    /// Specifies how many lines "thick" the border should be (meaning how often the border-line gets logged).
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
    public static final String BORDER_THICKNESS = BORDER + ".thickness";


    /// The [String] that will be printed at the start of every line of the LogBlock.
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
    public static final String LINE_PREFIX = PREFIX + ".line-prefix";
}
