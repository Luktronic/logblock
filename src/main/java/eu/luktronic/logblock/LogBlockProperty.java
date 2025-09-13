package eu.luktronic.logblock;

import lombok.Getter;

import java.util.Optional;
import java.util.function.Predicate;

/// Defines all the configurable LogBlock properties, together with how they are validated
/// and which [LogBlockSystemProperties] they use.
enum LogBlockProperty {

    BORDER_DELIMITER(LogBlockSystemProperties.BORDER_DELIMITER, null, "="),
    BORDER_LENGTH(LogBlockSystemProperties.BORDER_LENGTH, Validation.GREATER_THAN_ZERO_INTEGER, 80),
    BORDER_THICKNESS(LogBlockSystemProperties.BORDER_THICKNESS, Validation.GREATER_THAN_ZERO_INTEGER, 1),
    LINE_PREFIX(LogBlockSystemProperties.LINE_PREFIX, null, "|"),
    PADDING_LEFT(LogBlockSystemProperties.PADDING_LEFT, Validation.POSITIVE_INTEGER, 2),
    PADDING_TOP(LogBlockSystemProperties.PADDING_TOP, Validation.POSITIVE_INTEGER, 1),
    PADDING_BOTTOM(LogBlockSystemProperties.PADDING_BOTTOM, Validation.POSITIVE_INTEGER, 1);

    @Getter
    private final String systemProperty;
    private final Validation validation;
    @Getter
    private final Object defaultValue;

    LogBlockProperty(String systemProperty, Validation validation, Object defaultValue) {
        this.systemProperty = systemProperty;
        this.validation = validation;
        this.defaultValue = defaultValue;
    }

    public Optional<Validation> getValidation() {
        return Optional.ofNullable(this.validation);
    }

    /// Defines standardized validations for properties by providing the error message to be logged
    /// as well as a [Predicate] which will perform the actual validation check.
    @Getter
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
}
