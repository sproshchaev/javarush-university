package com.javarush.example;

/**
 * Детальное рассмотрение класса Character.
 * Показывает методы проверки символов и преобразования регистра.
 */
public class Slide07_CharacterDetailsExample {

    public static void main(String[] args) {
        demonstrateBasicChecks();
        demonstrateUnicodeSupport();
        demonstrateCaseConversion();
        demonstrateWhitespaceAndSpace();
        demonstratePracticalUseCases();
    }

    private static void demonstrateBasicChecks() {
        System.out.println("=== Базовые проверки ===");

        char letter = 'A';
        char digit = '7';
        char symbol = '@';

        System.out.println("'" + letter + "' is letter: " + Character.isLetter(letter));
        System.out.println("'" + digit + "' is digit: " + Character.isDigit(digit));
        System.out.println("'" + symbol + "' is letter: " + Character.isLetter(symbol)); // false
        System.out.println("'" + symbol + "' is digit: " + Character.isDigit(symbol));   // false

        // isAlphabetic — более широкая проверка
        System.out.println("'" + letter + "' is alphabetic: " + Character.isAlphabetic(letter));
        System.out.println("'" + symbol + "' is alphabetic: " + Character.isAlphabetic(symbol)); // false
    }

    private static void demonstrateUnicodeSupport() {
        System.out.println("\n=== Поддержка Unicode ===");

        char greekAlpha = 'α'; // греческая альфа
        char cyrillicA = 'А';  // кириллическая А
        char japaneseKa = 'か'; // японская ка

        System.out.println("'" + greekAlpha + "' is letter: " + Character.isLetter(greekAlpha));
        System.out.println("'" + greekAlpha + "' is alphabetic: " + Character.isAlphabetic(greekAlpha));
        System.out.println("'" + cyrillicA + "' is letter: " + Character.isLetter(cyrillicA));
        System.out.println("'" + japaneseKa + "' is letter: " + Character.isLetter(japaneseKa)); // false — иероглиф, не буква!
        System.out.println("'" + japaneseKa + "' is alphabetic: " + Character.isAlphabetic(japaneseKa)); // false

        // Проверка цифры в другом языке
        char arabicDigit = '٢'; // арабская цифра 2
        System.out.println("'" + arabicDigit + "' is digit: " + Character.isDigit(arabicDigit)); // true
    }

    private static void demonstrateCaseConversion() {
        System.out.println("\n=== Преобразование регистра ===");

        char upper = 'B';
        char lower = 'b';

        System.out.println("'" + upper + "' to lower: " + Character.toLowerCase(upper));
        System.out.println("'" + lower + "' to upper: " + Character.toUpperCase(lower));

        // Для Unicode
        char cyrillicUpper = 'А';
        char cyrillicLower = 'а';
        System.out.println("'" + cyrillicUpper + "' to lower: " + Character.toLowerCase(cyrillicUpper));
        System.out.println("'" + cyrillicLower + "' to upper: " + Character.toUpperCase(cyrillicLower));

        // Не все символы имеют регистр
        char digit = '5';
        System.out.println("'" + digit + "' to upper: " + Character.toUpperCase(digit)); // остается '5'
        System.out.println("'" + digit + "' to lower: " + Character.toLowerCase(digit)); // остается '5'

        // Символ без регистра (например, знак препинания)
        char punctuation = '!';
        System.out.println("'" + punctuation + "' to upper: " + Character.toUpperCase(punctuation)); // остается '!'
    }

    private static void demonstrateWhitespaceAndSpace() {
        System.out.println("\n=== Пробелы и символы разделения ===");

        char space = ' ';
        char tab = '\t';
        char newline = '\n';
        char formFeed = '\f';
        char carriageReturn = '\r';

        System.out.println("'" + space + "' is whitespace: " + Character.isWhitespace(space));
        System.out.println("'" + tab + "' is whitespace: " + Character.isWhitespace(tab));
        System.out.println("'" + newline + "' is whitespace: " + Character.isWhitespace(newline));
        System.out.println("'" + formFeed + "' is whitespace: " + Character.isWhitespace(formFeed));
        System.out.println("'" + carriageReturn + "' is whitespace: " + Character.isWhitespace(carriageReturn));

        // isSpaceChar — более узкое определение (включает неразрывный пробел и др.)
        System.out.println("'" + space + "' is space char: " + Character.isSpaceChar(space));
        System.out.println("'" + tab + "' is space char: " + Character.isSpaceChar(tab)); // false — tab не считается "space" по этому методу!

        // Проверка на "разделитель"
        char nonBreakingSpace = '\u00A0'; // неразрывный пробел
        System.out.println("'" + nonBreakingSpace + "' is whitespace: " + Character.isWhitespace(nonBreakingSpace));
        System.out.println("'" + nonBreakingSpace + "' is space char: " + Character.isSpaceChar(nonBreakingSpace));
    }

    private static void demonstratePracticalUseCases() {
        System.out.println("\n=== Практические примеры ===");

        // Валидация имени (только буквы и пробелы)
        String name = "Иван Иванов";
        boolean isValidName = true;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                isValidName = false;
                break;
            }
        }
        System.out.println("Имя '" + name + "' валидно? " + isValidName);

        // Преобразование строки в нижний регистр (ручной способ)
        String input = "Hello World!";
        StringBuilder lowerCase = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            lowerCase.append(Character.toLowerCase(c));
        }
        System.out.println("В нижнем регистре: " + lowerCase.toString());

        // Подсчёт цифр в строке
        String text = "abc123def456";
        int digitCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                digitCount++;
            }
        }
        System.out.println("Цифр в строке: " + digitCount);
    }
}
