package eu.luktronic.logblock;

import lombok.val;

/// Class responsible for building the [VerticalLogBlockSection] of the left padding using the specified
/// [BorderFormat].
class VerticalPaddingSectionBuilder implements SectionBuilder{

    private final LogBlockFormat format;
    private final LogBlockSection[] children;

    VerticalPaddingSectionBuilder(LogBlockFormat format, LogBlockSection... children) {
        this.format = format;
        this.children = children;
    }

    @Override
    public VerticalLogBlockSection build() {
        val paddingBuilder = new StringBuilder(format.getPaddingLeft());
        for(int i = 0; i < format.getPaddingLeft(); i++) {
            paddingBuilder.append(" ");
        }
        val padding = paddingBuilder.toString();
        return new VerticalLogBlockSection(padding, children);
    }
}
