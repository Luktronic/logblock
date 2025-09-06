package eu.luktronic.logblock;

import lombok.Getter;

import java.util.Objects;

/**
 * Builds all the sections that are part of a {@link LogBlock}.
 */
@Getter
class LogBlockSections {

    private final LogBlockSection borderSection;
    private final LogBlockSection msgSection;

    public LogBlockSections(LogBlockFormat format, String msg, Object... params) {
        Objects.requireNonNull(format, "Received null format in LogBlockSections constructor!");
        this.borderSection = new BorderBuilder(format.getBorderFormat()).build();
        //TODO: Introduce MsgSectionBuilder
        this.msgSection = new BorderBuilder(format.getBorderFormat()).build();
    }
}
