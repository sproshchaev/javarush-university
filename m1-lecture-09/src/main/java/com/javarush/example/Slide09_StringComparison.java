package com.javarush.example;

/**
 * Примеры сравнения строк в Java
 */
public class Slide09_StringComparison {

    public static void main(String[] args) {
        System.out.println("=== Сравнение строк ===\n");

        String s1 = "Привет";
        String s2 = "Привет";
        String s3 = new String("Привет");
        String s4 = "привет";
        String s5 = "Привет, мир!";
        String s6 = "Привет, Мир!";

        // 1. equals() — точное сравнение
        System.out.println("s1.equals(s2): " + s1.equals(s2)); // ✅ true — одинаковые строки
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // ✅ true — содержимое одинаково
        System.out.println("s1 == s3: " + (s1 == s3));         // ❌ false — разные объекты

        // 2. equalsIgnoreCase() — игнорирует регистр
        System.out.println("\ns1.equalsIgnoreCase(s4): " + s1.equalsIgnoreCase(s4)); // ✅ true

        // 3. compareTo() — лексикографическое сравнение
        System.out.println("\ns1.compareTo(s4): " + s1.compareTo(s4)); // > 0 — 'П' > 'п' (по ASCII)
        System.out.println("s4.compareTo(s1): " + s4.compareTo(s1));   // < 0 — 'п' < 'П'
        System.out.println("s1.compareTo(s1): " + s1.compareTo(s1));   // 0 — строки равны

        // 4. compareToIgnoreCase() — лексикографическое, игнорируя регистр
        System.out.println("\ns1.compareToIgnoreCase(s4): " + s1.compareToIgnoreCase(s4)); // 0 — равны без учёта регистра

        // 5. startsWith() — начинается с...
        System.out.println("\ns5.startsWith(\"Привет\"): " + s5.startsWith("Привет")); // ✅ true
        System.out.println("s5.startsWith(\"привет\"): " + s5.startsWith("привет")); // ❌ false — регистр важен

        // 6. endsWith() — заканчивается на...
        System.out.println("\ns5.endsWith(\"мир!\"): " + s5.endsWith("мир!")); // ✅ true
        System.out.println("s5.endsWith(\"Мир!\"): " + s5.endsWith("Мир!")); // ❌ false — регистр важен

        // 7. regionMatches() — сравнение части строк
        System.out.println("\ns5.regionMatches(0, \"Привет\", 0, 6): " + s5.regionMatches(0, "Привет", 0, 6)); // ✅ true
        System.out.println("s5.regionMatches(8, \"мир!\", 0, 4): " + s5.regionMatches(8, "мир!", 0, 4));       // ✅ true

        // regionMatches с игнорированием регистра
        System.out.println("\ns5.regionMatches(true, 8, \"МИР!\", 0, 4): " + s5.regionMatches(true, 8, "МИР!", 0, 4)); // ✅ true

        // 8. Пример использования в условии
        String userInput = "ВЫХОД";
        if (userInput.equalsIgnoreCase("выход")) {
            System.out.println("Пользователь хочет выйти.");
        }

        // 9. Важный вывод: всегда используй equals(), а не ==
        System.out.println("\n--- Важно: == сравнивает ссылки, equals() — содержимое ---");
        System.out.println("s1 == s2: " + (s1 == s2)); // ✅ true — обе ссылки на String Pool
        System.out.println("s1 == s3: " + (s1 == s3)); // ❌ false — s3 создана через new
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // ✅ true — содержимое одинаково
    }
}