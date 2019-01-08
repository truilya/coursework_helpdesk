package coursework_helpdesk.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Dates {
    private Dates() {}

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String localDateTime, String pattern){
        return LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern(pattern));
    }
}