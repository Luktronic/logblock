package eu.luktronic.logblock;

import org.junit.jupiter.api.*;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.*;

class LogBlockConfigTest {

    static final LogBlockConfig.ConfigReader configReader = new LogBlockConfig.ConfigReader();

    @Nested
    @DisplayName("Border Delimiter")
    class BorderDelimiter {

        static final String expectedDefaultValue = "=";
        static final String property = LogBlockProperties.BORDER_DELIMITER;
        static final Supplier<?> testedMethod = configReader::readBorderDelimiter;

        @Nested
        @DisplayName("with no custom config")
        class WithNoCustomConfig {

            @Test
            @DisplayName("should have correct default value")
            void shouldHaveCorrectDefaultValue() {
                assertThat(testedMethod.get()).isEqualTo(expectedDefaultValue);
            }
        }

        @Nested
        @DisplayName("with '" + property + "' set to")
        class WithPropertySetTo {

            @Nested
            @DisplayName("valid value")
            class ValidValue extends AbstractSetPropertyTest {

                @Override
                String getPropertyKey() {
                    return property;
                }

                @Override
                String getNewValue() {
                    return "+";
                }

                @Test
                @DisplayName("should set delimiter to configured value")
                void shouldSetDelimiterToValue() {
                    assertThat(testedMethod.get()).isEqualTo(getNewValue());
                }
            }
        }
    }

    @Nested
    @DisplayName("Border Length")
    class BorderLength {

        static final int expectedDefaultValue = 30;
        static final String property = LogBlockProperties.BORDER_LENGTH;
        static final Supplier<?> testedMethod = configReader::readBorderLength;

        @Nested
        @DisplayName("with no custom config")
        class WithNoCustomConfig {

            @Test
            @DisplayName("should have correct default value")
            void shouldHaveCorrectDefaultValue() {
                assertThat(testedMethod.get()).isEqualTo(expectedDefaultValue);
            }
        }

        @Nested
        @DisplayName("with '" + property + "' set to")
        class WithPropertySetTo {

            @Nested
            @DisplayName("valid value")
            class ValidValue extends AbstractSetPropertyTest {

                @Override
                String getPropertyKey() {
                    return property;
                }

                @Override
                Integer getNewValue() {
                    return 50;
                }

                @Test
                @DisplayName("should set delimiter to configured value")
                void shouldSetDelimiterToValue() {
                    assertThat(testedMethod.get()).isEqualTo(getNewValue());
                }
            }

            @Nested
            @DisplayName("invalid value")
            class InvalidValue extends AbstractSetPropertyTest {

                @Override
                String getPropertyKey() {
                    return property;
                }

                @Override
                Integer getNewValue() {
                    return -13;
                }

                @Test
                @DisplayName("should fall back to default value")
                void shouldSetDelimiterToValue() {
                    assertThat(testedMethod.get()).isEqualTo(expectedDefaultValue);
                }
            }
        }
    }

    @Nested
    @DisplayName("Border Thickness")
    class BorderThickness {

        static final int expectedDefaultValue = 1;
        static final String property = LogBlockProperties.BORDER_THICKNESS;
        static final Supplier<?> testedMethod = configReader::readBorderThickness;

        @Nested
        @DisplayName("with no custom config")
        class WithNoCustomConfig {

            @Test
            @DisplayName("should have correct default value")
            void shouldHaveCorrectDefaultValue() {
                assertThat(testedMethod.get()).isEqualTo(expectedDefaultValue);
            }
        }

        @Nested
        @DisplayName("with '" + property + "' set to")
        class WithPropertySetTo {

            @Nested
            @DisplayName("valid value")
            class ValidValue extends AbstractSetPropertyTest {

                @Override
                String getPropertyKey() {
                    return property;
                }

                @Override
                Integer getNewValue() {
                    return 13;
                }

                @Test
                @DisplayName("should set delimiter to configured value")
                void shouldSetDelimiterToValue() {
                    assertThat(testedMethod.get()).isEqualTo(getNewValue());
                }
            }

            @Nested
            @DisplayName("invalid value")
            class InvalidValue extends AbstractSetPropertyTest {

                @Override
                String getPropertyKey() {
                    return property;
                }

                @Override
                Integer getNewValue() {
                    return -13;
                }

                @Test
                @DisplayName("should fall back to default value")
                void shouldSetDelimiterToValue() {
                    assertThat(testedMethod.get()).isEqualTo(expectedDefaultValue);
                }
            }
        }
    }

    abstract static class AbstractSetPropertyTest {
        abstract String getPropertyKey();
        abstract Object getNewValue();

        @BeforeEach
        void setup() {
            System.setProperty(getPropertyKey(), getNewValue().toString());
        }

        @AfterEach
        void cleanup() {
            System.clearProperty(getPropertyKey());
        }
    }
}