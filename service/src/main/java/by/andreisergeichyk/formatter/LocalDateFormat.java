package by.andreisergeichyk.formatter;

import by.andreisergeichyk.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocalDateFormat {

    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDate format(String date) {
        LocalDate localDate = null;
        if (!StringUtil.isEmpty(date)) {
            localDate = LocalDate.parse(date, FORMATTER);
        }

        return localDate;
    }

    public static String format(LocalDate localDate) {
        String date = null;
        if (localDate != null) {
            date = FORMATTER.format(localDate);
        }

        return date;
    }
}
