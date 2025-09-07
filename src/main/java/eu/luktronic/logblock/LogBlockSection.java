package eu.luktronic.logblock;

import java.util.List;

/// Parent class for all types of sections for a block.
abstract class LogBlockSection {

    abstract List<LogBlockLine> getLines();
    abstract int lineCount();
}
