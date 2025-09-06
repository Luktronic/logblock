package eu.luktronic.logblock;

import lombok.val;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that is responsible for building the {@link LogBlockSection} of the border using the specified
 * {@link BorderFormat}.
 */
class BorderBuilder implements SectionBuilder {

    private final BorderFormat format;

    BorderBuilder(BorderFormat format) {
        Objects.requireNonNull(format, "Received null format in BorderBuilder constructor!");
        this.format = format;
    }

    @Override
    public LogBlockSection build() {
        val delimiter = format.getDelimiter();
        val delimiterCount = format.getDelimiterCount();
        val thickness = format.getThickness();

        val stringBuilder = new StringBuilder(delimiterCount);
        for(int i = 0; i < delimiterCount; i++) {
            stringBuilder.append(delimiter);
        }
        val baseLine = stringBuilder.toString();
        val lines = new ArrayList<LogBlockLine>(thickness);

        for(int i = 0; i < thickness; i++) {
            val line = new LogBlockLine(baseLine);
            lines.add(line);
        }

        return new LogBlockSection(lines);
    }
}
