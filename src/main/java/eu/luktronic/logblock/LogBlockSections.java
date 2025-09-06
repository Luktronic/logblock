package eu.luktronic.logblock;

import lombok.Getter;
import lombok.val;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        this.msgSection = new LogBlockSection(buildMsgSection(format, msg));
    }

    private List<LogBlockLine> buildMsgSection(LogBlockFormat format, String msg) {
        if(msg == null)
            msg = "null";
        val lines = msg.split("\n");

        val prefix = format.getLinePrefix();
        val padding = buildPaddingLeft(format.getPaddingLeft());

        return Arrays.stream(lines)
                .map(line -> prefix + padding + line)
                .map(LogBlockLine::new)
                .collect(Collectors.toList());
    }

    private String buildPaddingLeft(int paddingLeft) {
        val paddingBuilder = new StringBuilder(paddingLeft);
        for(int i = 0; i < paddingLeft; i++) {
            paddingBuilder.append(" ");
        }
        return paddingBuilder.toString();
    }
}
