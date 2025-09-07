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

    private final HorizontalLogBlockSection prefixSection;
    private final HorizontalLogBlockSection borderSection;
    private final HorizontalLogBlockSection paddingTopSection;
    private final VerticalLogBlockSection paddingLeftSection;
    private final HorizontalLogBlockSection paddingBottomSection;
    private final HorizontalLogBlockSection msgSection;

    public LogBlockSections(LogBlockFormat format, String msg, Object... params) {
        Objects.requireNonNull(format, "Received null format in LogBlockSections constructor!");
        this.prefixSection = new HorizontalLogBlockSection(Collections.singletonList(new LogBlockLine(format.getLinePrefix())));
        this.borderSection = new BorderSectionBuilder(format.getBorderFormat()).build();
        this.paddingTopSection = new HorizontalPaddingSectionBuilder(format.getPaddingTop()).build();
        this.paddingBottomSection = new HorizontalPaddingSectionBuilder(format.getPaddingBottom()).build();
        this.msgSection = new MsgSectionBuilder(msg, params).build();
        this.paddingLeftSection = new VerticalPaddingSectionBuilder(format, msgSection).build();
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
