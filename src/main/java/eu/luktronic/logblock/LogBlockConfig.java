package eu.luktronic.logblock;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.function.Predicate;

/// Class responsible for reading all the configs of LogBlock.
@Slf4j
class LogBlockConfig {

    static final String BORDER_DELIMITER = System.getProperty(LogBlockProperties.BORDER_DELIMITER, "=");
    static final int  BORDER_LENGTH = getIntProperty(LogBlockProperties.BORDER_LENGTH, 30, Validation.GREATER_THAN_ZERO_INTEGER);
    static final int  BORDER_THICKNESS = getIntProperty(LogBlockProperties.BORDER_THICKNESS, 1, Validation.GREATER_THAN_ZERO_INTEGER);

    static final String LINE_PREFIX = System.getProperty(LogBlockProperties.LINE_PREFIX, "|");

    static final int PADDLING_LEFT = getIntProperty(LogBlockProperties.PADDING_LEFT, 2, Validation.POSITIVE_INTEGER);
    static final int PADDLING_TOP = getIntProperty(LogBlockProperties.PADDING_TOP, 1, Validation.POSITIVE_INTEGER);
    static final int PADDLING_BOTTOM = getIntProperty(LogBlockProperties.PADDING_BOTTOM, 1, Validation.POSITIVE_INTEGER);

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

    /// Defines standardized validations for properties by providing the error message to be logged
    /// as well as a [Predicate] which will perform the actual validation check.
    private enum Validation {
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
}
