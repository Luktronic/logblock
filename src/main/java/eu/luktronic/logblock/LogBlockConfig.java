package eu.luktronic.logblock;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.function.Predicate;

/// Class responsible for reading all the configs of LogBlock.
@Slf4j
class LogBlockConfig {

    private static final ConfigReader configReader = new ConfigReader();

    static final String BORDER_DELIMITER = configReader.readBorderDelimiter();
    static final int  BORDER_LENGTH = configReader.readBorderLength();
    static final int  BORDER_THICKNESS = configReader.readBorderThickness();

    static final String LINE_PREFIX = configReader.readLinePrefix();

    static final int PADDLING_LEFT = configReader.readPaddingLeft();
    static final int PADDLING_TOP = configReader.readPaddingTop();
    static final int PADDLING_BOTTOM = configReader.readPaddingBottom();

    static class ConfigReader {

        public String readBorderDelimiter() {
            return System.getProperty(LogBlockProperty.BORDER_DELIMITER.getSystemProperty(), (String) LogBlockProperty.BORDER_DELIMITER.getDefaultValue());
        }

        public int readBorderLength() {
            return getIntProperty(LogBlockProperty.BORDER_LENGTH);
        }

        public int readBorderThickness() {
            return getIntProperty(LogBlockProperty.BORDER_THICKNESS);
        }

        public String readLinePrefix() {
            return System.getProperty(LogBlockProperty.LINE_PREFIX.getSystemProperty(), (String) LogBlockProperty.LINE_PREFIX.getDefaultValue());
        }

        public int readPaddingLeft() {
            return getIntProperty(LogBlockProperty.PADDING_LEFT);
        }

        public int readPaddingTop() {
            return getIntProperty(LogBlockProperty.PADDING_TOP);
        }

        public int readPaddingBottom() {
            return getIntProperty(LogBlockProperty.PADDING_BOTTOM);
        }
        /// Reads the `int` value of System property with the passed `key` or falls back to
        /// a default value `def`.
        ///
        /// Falling back to default value will happen if System property:
        /// - is not set
        /// - is not an `int`
        /// - does not pass the specified [LogBlockProperty.Validation] check.
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        private static int getIntProperty(LogBlockProperty logBlockProperty) {
            val key = logBlockProperty.getSystemProperty();
            val def = (Integer) logBlockProperty.getDefaultValue();
            val validation = logBlockProperty.getValidation().get();
            val property = System.getProperty(key, Integer.toString(def));

            int finalValue = def;
            try {
                val propertyIntValue = Integer.parseInt(property);
                if(validation.getValidationPredicate().test(propertyIntValue))
                    finalValue = propertyIntValue;
                else
                    log.warn("Reverting value for '{}' to default '{}' - custom value did not pass validation: {}", key, def, validation.getErrorMsg());

            } catch (NumberFormatException numberFormatException) {
                val propertyValue = System.getProperty(key);
                log.warn("Reverting value for '{}' to default '{}' - custom value '{}' could not be parsed to int", key, def, propertyValue);
            }
            return finalValue;
        }
    }
}
