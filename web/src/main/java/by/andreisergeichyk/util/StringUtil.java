package by.andreisergeichyk.util;

import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor
public final class StringUtil {

    private static final String EMPTY = "";

    public static boolean isEmpty(String value) {
        return isNull(value) || EMPTY.equals(value);
    }
}
