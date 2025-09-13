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
        static final String property = LogBlockSystemProperties.BORDER_DELIMITER;
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
                void shouldSetToNewValue() {
                    assertThat(testedMethod.get()).isEqualTo(getNewValue());
                }
            }
        }
    }

    @Nested
    @DisplayName("Border Length")
    class BorderLength {

        static final int expectedDefaultValue = 80;
        static final String property = LogBlockSystemProperties.BORDER_LENGTH;
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
                void shouldSetToNewValue() {
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
                void shouldFallBackToDefaultValue() {
                    assertThat(testedMethod.get()).isEqualTo(expectedDefaultValue);
                }
            }
        }
    }

    @Nested
    @DisplayName("Border Thickness")
    class BorderThickness {

        static final int expectedDefaultValue = 1;
        static final String property = LogBlockSystemProperties.BORDER_THICKNESS;
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
                void shouldSetToNewValue() {
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
                void shouldFallBackToDefaultValue() {
                    assertThat(testedMethod.get()).isEqualTo(expectedDefaultValue);
                }
            }
        }
    }

    @Nested
    @DisplayName("Line Prefix")
    class LinePrefix {

        static final String expectedDefaultValue = "|";
        static final String property = LogBlockSystemProperties.LINE_PREFIX;
        static final Supplier<?> testedMethod = configReader::readLinePrefix;

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
                    return "/";
                }

                @Test
                @DisplayName("should set delimiter to configured value")
                void shouldSetToNewValue() {
                    assertThat(testedMethod.get()).isEqualTo(getNewValue());
                }
            }
        }
    }

    @Nested
    @DisplayName("Padding Left")
    class PaddingLeft {

        static final int expectedDefaultValue = 2;
        static final String property = LogBlockSystemProperties.PADDING_LEFT;
        static final Supplier<?> testedMethod = configReader::readPaddingLeft;

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
                void shouldSetToNewValue() {
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
                void shouldFallBackToDefaultValue() {
                    assertThat(testedMethod.get()).isEqualTo(expectedDefaultValue);
                }
            }
        }
    }

    @Nested
    @DisplayName("Padding Top")
    class PaddingTop {

        static final int expectedDefaultValue = 1;
        static final String property = LogBlockSystemProperties.PADDING_TOP;
        static final Supplier<?> testedMethod = configReader::readPaddingTop;

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
                void shouldSetToNewValue() {
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
                void shouldFallBackToDefaultValue() {
                    assertThat(testedMethod.get()).isEqualTo(expectedDefaultValue);
                }
            }
        }
    }

    @Nested
    @DisplayName("Padding Bottom")
    class PaddingBottom {

        static final int expectedDefaultValue = 1;
        static final String property = LogBlockSystemProperties.PADDING_BOTTOM;
        static final Supplier<?> testedMethod = configReader::readPaddingBottom;

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
                void shouldSetToNewValue() {
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
                void shouldFallBackToDefaultValue() {
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