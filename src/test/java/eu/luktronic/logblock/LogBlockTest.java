package eu.luktronic.logblock;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class LogBlockTest {

    @Test
    void ensureInfoWorks() {
        LogBlock.withLogger(log).info("{} {} {}\n{}", 1, 2, 3, 4);
    }

    @Test
    void ensureInfoWorksWithTextBlock() {
        LogBlock.withLogger(log).error("""
                HELLO THIS IS A TEXTBLOCK TEST {}
                      even with formatting!
                
                This is insane {}
                """, 1, 2, 3, 4);
    }
}