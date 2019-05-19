package by.andreisergeichyk.util.matcher;

import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public final class PhoneMather {

    private static final String PHONE_PATTERN = "^\\+375\\s?\\((29|33|44|25)\\)\\s?\\d{3}-\\d{2}-\\d{2}$";

    private static final Pattern pattern = Pattern.compile(PHONE_PATTERN);

    public static boolean validatePhone(String phone) {
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }
}
