package eu.luktronic.logblock;

import lombok.val;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that is responsible for building the {@link HorizontalLogBlockSection} of the border using the specified
 * {@link BorderFormat}.
 */
class BorderSectionBuilder implements SectionBuilder {

    private final BorderFormat format;

    BorderSectionBuilder(BorderFormat format) {
        Objects.requireNonNull(format, "Received null format in BorderBuilder constructor!");
        this.format = format;
    }

    @Override
    public HorizontalLogBlockSection build() {
        val delimiter = format.getDelimiter();
        val length = format.getLength();
        val thickness = format.getThickness();

        val stringBuilder = new StringBuilder(length);
        for(int i = 0; i < length; i++) {
            stringBuilder.append(delimiter);
        }
        val baseLine = stringBuilder.toString();
        val lines = new ArrayList<LogBlockLine>(thickness);

        for(int i = 0; i < thickness; i++) {
            val line = new LogBlockLine(baseLine);
            lines.add(line);
        }

        return new HorizontalLogBlockSection(lines);
    }
}
