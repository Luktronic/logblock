package eu.luktronic.logblock;

import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        val borderLines = sections.getBorderSection().getLines();
        val msgLines = sections.getMsgSection().getLines();

        val lineCount = 2 * borderLines.size() + msgLines.size();
        val lines = new ArrayList<LogBlockLine>(lineCount);
        lines.addAll(borderLines);
        lines.addAll(msgLines);
        lines.addAll(borderLines);
        return lines;
    }
}
