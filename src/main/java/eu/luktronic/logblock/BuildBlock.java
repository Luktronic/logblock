package eu.luktronic.logblock;

import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Builds a block and renders it
 * into a single list of {@link LogBlockLine lines}.
 */
class BuildBlock {

    private final LogBlockSections sections;

    public BuildBlock(LogBlockFormat format, String msg, Object... params) {
        this.sections = new LogBlockSections(format, msg, params);
    }

    /**
     * Returns all of the {@link LogBlockLine lines} of the block in order.
     * @return An ordered {@link List} of all the {@link LogBlockLine lines} of the block.
     */
    public List<LogBlockLine> andGetLines() {
        val prefixline = sections.getPrefixSection().getLines().get(0);
        val borderLines = sections.getBorderSection().getLines();
        val paddingTopLines = sections.getPaddingTopSection().getLines();
        val paddingBottomLines = sections.getPaddingBottomSection().getLines();
        val paddingLeftLine = sections.getPaddingLeftSection().getLines().get(0);
        val msgLines = applyPadding(paddingLeftLine, sections.getMsgSection().getLines());

        val lineCount = 2 * borderLines.size() + msgLines.size();
        val lines = new ArrayList<LogBlockLine>(lineCount);
        lines.addAll(borderLines);
        lines.addAll(paddingTopLines);
        lines.addAll(msgLines);
        lines.addAll(paddingBottomLines);
        lines.addAll(borderLines);

        return lines.stream()
                .map(line -> line.prepend(prefixline))
                .collect(Collectors.toList());
    }

    private List<LogBlockLine> applyPadding(LogBlockLine padding, List<LogBlockLine> lines) {
        return lines.stream()
                .map(line -> line.prepend(padding))
                .collect(Collectors.toList());
    }
}
