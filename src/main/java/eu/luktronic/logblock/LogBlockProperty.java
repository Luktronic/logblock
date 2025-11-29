package eu.luktronic.logblock;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Optional;
import java.util.function.Predicate;

/// Defines all the configurable LogBlock properties, together with how they are validated
/// and which [LogBlockSystemProperties] they use.
@Slf4j
enum LogBlockProperty {

    BORDER_DELIMITER(String.class, LogBlockSystemProperties.BORDER_DELIMITER, Validation.NON_BLANK_STRING, "="),
    BORDER_LENGTH(Integer.class, LogBlockSystemProperties.BORDER_LENGTH, Validation.GREATER_THAN_ZERO_INTEGER, 80),
    BORDER_THICKNESS(Integer.class, LogBlockSystemProperties.BORDER_THICKNESS, Validation.GREATER_THAN_ZERO_INTEGER, 1),
    LINE_PREFIX(String.class, LogBlockSystemProperties.LINE_PREFIX, null, "|"),
    PADDING_LEFT(Integer.class, LogBlockSystemProperties.PADDING_LEFT, Validation.POSITIVE_INTEGER, 2),
    PADDING_TOP(Integer.class, LogBlockSystemProperties.PADDING_TOP, Validation.POSITIVE_INTEGER, 1),
    PADDING_BOTTOM(Integer.class, LogBlockSystemProperties.PADDING_BOTTOM, Validation.POSITIVE_INTEGER, 1);

    private final Class<?> type;
    @Getter
    private final String systemProperty;
    @Getter
    private final Validation validation;
    @Getter
    private final Object defaultValue;

    LogBlockProperty(Class<?> type, String systemProperty, Validation validation, Object defaultValue) {
        this.type = type;
        this.systemProperty = systemProperty;
        this.validation = validation;
        this.defaultValue = defaultValue;
    }

    /// Returns the passed `value` falls back to [#defaultValue].
    ///
    /// Falling back to default value will happen if System property:
    /// - is not set
    /// - is not an `int`
    /// - does not pass the specified [LogBlockProperty.Validation] check.
    public Object getValueOrDefault(Object value) {
        if(value == null)
            return this.defaultValue;
        if(!this.type.equals(value.getClass()))
            return this.defaultValue;

        if(this.validation == null)
            return value;

        val isValueValid = validation.getValidationPredicate().test(value);
        if(isValueValid)
            return value;

        log.warn("Reverting value for '{}' to default '{}' - custom value did not pass validation: {}", this.systemProperty, this.defaultValue, this.validation.getErrorMsg());
        return this.defaultValue;
    }

    /// Defines standardized validations for properties by providing the error message to be logged
    /// as well as a [Predicate] which will perform the actual validation check.
    @Getter
    enum Validation {
        NON_BLANK_STRING("Must be a non-blank String", (s) -> s != null && !((String) s).trim().isEmpty()),
        GREATER_THAN_ZERO_INTEGER("Must be greater than 0 integer", (x) -> (Integer) x > 0),
        POSITIVE_INTEGER("Must be positive (>=0) integer", (x) -> (Integer) x >= 0),
        ;

        private final String errorMsg;
        private final Predicate<Object> validationPredicate;

        Validation(String errorMsg, Predicate<Object> validationPredicate) {
            this.errorMsg = errorMsg;
            this.validationPredicate = validationPredicate;
        }
    }
}
