package eu.luktronic.logblock;

import lombok.Getter;
import lombok.val;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Builds all the sections that are part of a {@link LogBlock}.
 */
@Getter
class LogBlockSections {

    private final VerticalLogBlockSection prefixSection;

    public LogBlockSections(LogBlockFormat format, String msg, Object... params) {
        Objects.requireNonNull(format, "Received null format in LogBlockSections constructor!");
        val borderSection = new BorderSectionBuilder(format.getBorderFormat()).build();
        val paddingTopSection = new HorizontalPaddingSectionBuilder(format.getPaddingTop()).build();
        val paddingBottomSection = new HorizontalPaddingSectionBuilder(format.getPaddingBottom()).build();
        val msgSection = new MsgSectionBuilder(msg, params).build();
        val paddingLeftSection = new VerticalPaddingSectionBuilder(format, msgSection).build();

        this.prefixSection = new VerticalLogBlockSection(format.getLinePrefix(), borderSection, paddingTopSection, paddingLeftSection, paddingBottomSection, borderSection);
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
