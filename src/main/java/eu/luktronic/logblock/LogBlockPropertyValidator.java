package eu.luktronic.logblock;

import lombok.Getter;

import java.util.function.Predicate;

/// Class responsible for validating the [LogBlockSystemProperties].
public class LogBlockPropertyValidator {


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
