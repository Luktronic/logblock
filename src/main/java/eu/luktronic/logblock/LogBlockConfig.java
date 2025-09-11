package eu.luktronic.logblock;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.function.Predicate;

/// Class responsible for reading all the configs of LogBlock.
@Slf4j
class LogBlockConfig {

    private static final ConfigReader configReader = new ConfigReader();

    static final String BORDER_DELIMITER = configReader.readBorderDelimiter();
    static final int  BORDER_LENGTH = configReader.raedBorderLength();
    static final int  BORDER_THICKNESS = configReader.readBorderThickness();

    static final String LINE_PREFIX = configReader.readLinePrefix();

    static final int PADDLING_LEFT = configReader.readPaddingLeft();
    static final int PADDLING_TOP = configReader.readPaddingTop();
    static final int PADDLING_BOTTOM = configReader.readPaddingBottom();

    /// Defines standardized validations for properties by providing the error message to be logged
    /// as well as a [Predicate] which will perform the actual validation check.
    enum Validation {
        GREATER_THAN_ZERO_INTEGER("Must be greater than 0 integer", (x) -> x > 0),
        POSITIVE_INTEGER("Must be positive (>=0) integer", (x) -> x >= 0),
        ;

        private final String errorMsg;
        private final Predicate<Integer> validationPredicate;

        Validation(String errorMsg, Predicate<Integer> validationPredicate) {
            this.errorMsg = errorMsg;
            this.validationPredicate = validationPredicate;
        }
    }

    static class ConfigReader {

        public String readBorderDelimiter() {
            return System.getProperty(LogBlockProperties.BORDER_DELIMITER, "=");
        }

        public int raedBorderLength() {
            return getIntProperty(LogBlockProperties.BORDER_LENGTH, 30, Validation.GREATER_THAN_ZERO_INTEGER);
        }

        public int readBorderThickness() {
            return getIntProperty(LogBlockProperties.BORDER_THICKNESS, 1, Validation.GREATER_THAN_ZERO_INTEGER);
        }

        public String readLinePrefix() {
            return System.getProperty(LogBlockProperties.LINE_PREFIX, "|");
        }

        public int readPaddingLeft() {
            return getIntProperty(LogBlockProperties.PADDING_LEFT, 2, Validation.POSITIVE_INTEGER);
        }

        public int readPaddingTop() {
            return getIntProperty(LogBlockProperties.PADDING_TOP, 1, Validation.POSITIVE_INTEGER);
        }

        public int readPaddingBottom() {
            return getIntProperty(LogBlockProperties.PADDING_BOTTOM, 1, Validation.POSITIVE_INTEGER);
        }
        /// Reads the `int` value of System property with the passed `key` or falls back to
        /// a default value `def`.
        ///
        /// Falling back to default value will happen if System property:
        /// - is not set
        /// - is not an `int`
        /// - does not pass the specified [Validation] check.
        private static int getIntProperty(String key, int def, Validation validation) {
            val property = System.getProperty(key, Integer.toString(def));

            int finalValue = def;
            try {
                val propertyIntValue = Integer.parseInt(property);
                if(validation.validationPredicate.test(propertyIntValue))
                    finalValue = propertyIntValue;
                else
                    log.warn("Reverting value for '{}' to default '{}' - custom value did not pass validation: {}", key, def, validation.errorMsg);

            } catch (NumberFormatException numberFormatException) {
                val propertyValue = System.getProperty(key);
                log.warn("Reverting value for '{}' to default '{}' - custom value '{}' could not be parsed to int", key, def, propertyValue);
            }
            return finalValue;
        }
    }
}
