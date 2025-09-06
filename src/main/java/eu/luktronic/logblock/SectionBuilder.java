package eu.luktronic.logblock;

/**
 * An interface defining a method that can build a {@link LogBlockSection}.
 */
@FunctionalInterface
interface SectionBuilder {

    /**
     * Builds a {@link LogBlockSection} and returns it.
     * @return The built {@link LogBlockSection}.
     */
    LogBlockSection build();
}
