package eu.luktronic.logblock;

import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/// Class that is responsible for building the [LogBlockSection] of the message section using the passed
/// message.
///
/// ## Parameter handling
/// When parameters are passed to this builder, it will assign each parameter to the next *anchor* (`{}`)
/// it finds.<br>
/// If there are more anchors than parameters, the excess anchors are simply ignored.<br>
/// If there are more parameters than anchors, the excess parameters be associated with the <b>last line</b>
/// of the section and <b>passed to SLF4J</b>. This is because we want the underlying logging library to handle
/// the excess parameters instead of doing it ourselves.
///
/// ### Examples
///
/// ##### 1. Anchors > Parameters
/// Assuming these parameters are passed into this builder: `{"first", "second", "third"}`<br>
/// And this text block as the `msg`:
/// ```text
/// This is the {} line, but the {} anchor.
/// The {} anchor is on the {} line.
/// ```
///
/// The text block has <b>4</b> anchors but only <b>3</b> parameters, resulting in the following output logs:
/// ```text
/// [INFO] This is the first line, but the second anchor.
/// [INFO] The third anchor is on the {} line.
/// ```
///
/// ##### 2. Parameters > Anchors
/// Assuming these parameters: `{"first", "second", "third", "fourth"}`<br>
/// And this text block as the `msg`:
/// ```text
/// This is the {} line.
/// This is the {} line.
/// This is the {} line.
/// ```
///
/// The text block has <b>4</b> parameters but only <b>3</b> anchors.
/// The first 3 parameters get associated with the 3 anchors. However, in addition to the `"third"` parameter,
/// the `"fourth"` parameter is <b>also</b> passed to the last log statement.<br>
/// This is done so that we can let the underlying logging framework handle the excessive parameter.
///
/// Usually, logging frameworks simply omit these parameters, resulting in these log statements:
/// ```text
/// [INFO] This is the first line.
/// [INFO] This is the second line.
/// [INFO] This is the third line.
/// ```
class MsgSectionBuilder implements SectionBuilder{

    private final String msg;
    private final List<Object> params;

    public MsgSectionBuilder(String msg, Object[] params) {
        this.msg = msg == null ? "null" : msg;
        this.params = Arrays.asList(params);
    }

    @Override
    public LogBlockSection build() {
        val msgLines = msg.split("\n");

        val availableParams = new ArrayList<>(params);

        val lines = Arrays.stream(msgLines)
                .map(line -> buildBlockLineForLine(line, availableParams))
                .collect(Collectors.toList());

        return new LogBlockSection(lines);
    }

    private LogBlockLine buildBlockLineForLine(String line, List<Object> availableParams) {
        val anchorCount = new StringUtils(line).countAnchors();
        val availableParamsCount = availableParams.size();

        val chosenParamsCount = Math.min(anchorCount, availableParamsCount);
        val chosenParams = new ArrayList<>(chosenParamsCount);

        for(int i = 0; i < chosenParamsCount; i++) {
            chosenParams.add(availableParams.remove(i));
        }

        return new LogBlockLine(line, chosenParams);
    }
}
