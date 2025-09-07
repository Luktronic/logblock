package eu.luktronic.logblock;

/**
 * An interface defining a method that can build a {@link HorizontalLogBlockSection}.
 */
@FunctionalInterface
interface SectionBuilder {

    /**
     * Builds a {@link HorizontalLogBlockSection} and returns it.
     * @return The built {@link HorizontalLogBlockSection}.
     */
    HorizontalLogBlockSection build();
}
