package com.javarush.live;

import java.util.Locale;

public class StringPatternDemo {

    public static void main(String[] args) {

        for (Object o : new Object[]{"JAVA", "", 42, null}) {
            System.out.printf("%s: %s / %s%n ",
                    o, lowerWithCast(o), lowerWithPattern(o));
        }

    }

    private static String lowerWithCast(Object o) {
        if (o instanceof String && !((String) o).isEmpty()) {
            return ((String) o).toLowerCase(Locale.ROOT);
        }
        return "(пропуск)";
    }

    private static String lowerWithPattern(Object o) {
        if (o instanceof String s && !s.isEmpty()) {
            return s.toLowerCase(Locale.ROOT);
        }
        return "(пропуск)";
    }

}
