package eu.luktronic.logblock;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

/// Class responsible for reading all the configs of LogBlock.
@Slf4j
class LogBlockConfig {

    private static final String customBorderDelimiter = System.getProperty("");

    private static int getIntProperty(String key, int def) {
        val property = System.getProperty(key, Integer.toString(def));

        int finalValue = def;
        try {
            val intValue = Integer.parseInt(property);
            //TODO: do validations
            finalValue = intValue;

        } catch (NumberFormatException numberFormatException) {
            log.warn("Encountered exception");
        }

        return finalValue;
    }
}
