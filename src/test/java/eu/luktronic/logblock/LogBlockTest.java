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
        LogBlock.withLogger(log).info("""
                HELLO THIS IS A TEXTBLOCK TEST {}
                      even with formatting!
                
                This is insane {}
                """, 1, 2, 3, 4);
    }

    @Test
    void ensureFluentSettingsWork() {
        val logBlock = LogBlock.withLogger(log);
        logBlock.withBorderDelimiter("+-")
                .withBorderThickness(5)
                .withPaddingLeft(30)
                .info("""
                        HELLO THIS IS A TEXTBLOCK TEST {}
                              even with formatting!
                        
                        This is insane {}
                        """, 1, 2, 3, 4);

        logBlock.info("This should be normal again");
    }
}