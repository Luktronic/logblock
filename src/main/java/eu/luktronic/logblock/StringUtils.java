package eu.luktronic.logblock;

import lombok.val;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Provides some String utilities that are frequently needed
 * in the project.
 */
class StringUtils {

    private final String str;

    StringUtils(String str) {
        Objects.requireNonNull(str, "Received null str in StringUtils constructor!");
        this.str = str;
    }

    /**
     * Counts how often the passed {@code regex} is contained inside the String.
     * @param regex Regular Expression that should be counted.
     * @return Amount of matches of the passed {@code regex} against the String.
     */
    public int countOccurrencesOf(String regex) {
        val matcher = Pattern.compile(regex).matcher(str);
        int count = 0;
        while(matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * Counts how many anchors ({@code {}}) are inside the String <b>without</b> them being escaped with {@code \}.
     *
     * <p>
     *     For example, this counts as 1 occurrence:<br>
     *     {@code "Hello! \{} My {name} is {{}}"}
     * </p>
     * @return The amount of anchors ({@code {}}) that are in the String.
     */
    public int countAnchors() {
        return countOccurrencesOf("(?<!\\\\)\\{}");
    }
}
