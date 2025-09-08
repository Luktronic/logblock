package eu.luktronic.logblock;

import java.util.List;
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
        return sections.getPrefixSection().getLines();
    }

    private List<LogBlockLine> applyPadding(String padding, List<LogBlockLine> lines) {
        return lines.stream()
                .map(line -> line.prepend(padding))
                .collect(Collectors.toList());
    }
}
