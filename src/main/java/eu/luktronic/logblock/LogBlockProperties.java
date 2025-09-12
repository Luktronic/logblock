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
    /// If set to value <=0, will default to `80`.
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


    private static final String PADDING = PREFIX + ".padding";
    /// Specifies how many whitespace characters should be printed before the actual
    /// log message.<br>
    /// The border <b>is not</b> affected by this! Only the message that you pass to the LogBlock logger itself will get
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
    public static final String PADDING_LEFT = PADDING + ".left";
    /// Specifies how many lines should be logged after the top border, but before the actual
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
    public static final String PADDING_TOP = PADDING + ".top";
    /// Specifies how many lines should be logged after the log message, but before the
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
    public static final String PADDING_BOTTOM = PADDING + ".top";
}
