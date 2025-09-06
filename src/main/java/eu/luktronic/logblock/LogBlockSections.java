package eu.luktronic.logblock;

import lombok.Getter;

import java.util.Objects;

/**
 * Defines all the sections that are part of a {@link LogBlock}.
 */
@Getter
class LogBlockSections {

    private final LogBlockSection borderSection;
    private final LogBlockSection msgSection;

    public LogBlockSections(LogBlockSection borderSection, LogBlockSection msgSection) {
        Objects.requireNonNull(borderSection, "Received null borderSection in LogBlockSections constructor!");
        Objects.requireNonNull(msgSection, "Received null msgSection in LogBlockSections constructor!");
        this.borderSection = borderSection;
        this.msgSection = msgSection;
    }
}
