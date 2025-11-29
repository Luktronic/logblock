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
            return getStringProperty(LogBlockProperty.BORDER_DELIMITER);
        }

        public int readBorderLength() {
            return getIntProperty(LogBlockProperty.BORDER_LENGTH);
        }

        public int readBorderThickness() {
            return getIntProperty(LogBlockProperty.BORDER_THICKNESS);
        }

        public String readLinePrefix() {
            return getStringProperty(LogBlockProperty.LINE_PREFIX);
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
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        private static int getIntProperty(LogBlockProperty logBlockProperty) {
            val key = logBlockProperty.getSystemProperty();
            val def = (Integer) logBlockProperty.getDefaultValue();
            val property = System.getProperty(key, Integer.toString(def));

            int finalValue = def;
            try {
                finalValue = (Integer) logBlockProperty.getValueOrDefault(Integer.parseInt(property));

            } catch (NumberFormatException numberFormatException) {
                val propertyValue = System.getProperty(key);
                log.warn("Reverting value for '{}' to default '{}' - custom value '{}' could not be parsed to int", key, def, propertyValue);
            }
            return finalValue;
        }

        private static String getStringProperty(LogBlockProperty logBlockProperty) {
            val key = logBlockProperty.getSystemProperty();
            val def = (String) logBlockProperty.getDefaultValue();
            val property = System.getProperty(key, def);

            return (String) logBlockProperty.getValueOrDefault(property);
        }
    }
}
