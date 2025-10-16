package com.javarush.example;

/**
 * Слайд 10: Переменные-ссылки.
 * Демонстрация работы с переменными-ссылками, их значением null и различием от примитивов.
 */
public class Slide10_ReferenceVariables {

    // Поле класса - ссылочного типа, значение по умолчанию будет null
    static String defaultString;

    public static void main(String[] args) {
        System.out.println("=== Слайд 10: Переменные-ссылки ===\n");

        // 1. Объявление и инициализация примитива
        System.out.println("1. Примитивная переменная:");
        int primitiveInt = 42; // Значение 42 хранится НЕПОСРЕДСТВЕННО в переменной
        System.out.printf("int primitiveInt = %d;\n", primitiveInt);
        System.out.println();

        // 2. Объявление и инициализация ссылочной переменной
        System.out.println("2. Ссылочная переменная (String):");
        String referenceString = "Hello, World!"; // Переменная хранит АДРЕС объекта String в памяти
        System.out.printf("String referenceString = \"%s\";\n", referenceString);
        System.out.println();

        // 3. Значение по умолчанию для ссылочной переменной
        System.out.println("3. Значение по умолчанию (поле класса):");
        System.out.printf("static String defaultString; -> defaultString = %s\n", defaultString);
        System.out.println("Значение по умолчанию для ссылочных типов — null.");
        System.out.println();

        // 4. Работа с null (Опасно!)
        System.out.println("4. Работа с null (осторожно!):");
        String nullString = null;
        System.out.printf("String nullString = null; -> nullString = %s\n", nullString);

        // Попытка вызвать метод на null приведет к NullPointerException!
        try {
            int length = nullString.length(); // ОШИБКА! nullString не ссылается на объект!
            System.out.println("Этот код не выполнится.");
        } catch (NullPointerException e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.out.println("Попытка вызвать метод на переменной со значением null!");
        }
        System.out.println();

        // 5. Аналогия с примитивами и ссылками
        System.out.println("5. Аналогия: Примитив vs Ссылка:");
        int x = 10;
        int y = x; // Копируется ЗНАЧЕНИЕ
        y = 20; // Изменяем y
        System.out.printf("x = %d, y = %d (изменение y не повлияло на x)\n", x, y);

        String str1 = "Original";
        String str2 = str1; // Копируется ССЫЛКА (адрес)
        // str2 = "Modified"; // Если раскомментировать, str2 будет ссылаться на новый объект
        str2 = str2 + " Modified"; // Создаем НОВЫЙ объект, str2 теперь ссылается на него
        System.out.printf("str1 = \"%s\", str2 = \"%s\"\n", str1, str2);
        System.out.println("str1 и str2 изначально ссылались на один объект, но после изменения str2 они стали разными.");
        System.out.println();

        // 6. Проверка на null
        System.out.println("6. Проверка на null:");
        if (nullString == null) {
            System.out.println("Переменная nullString имеет значение null. Не пытайтесь обращаться к ней!");
        }

        String safeString = "Safe Value";
        if (safeString != null) {
            System.out.printf("safeString не null, можно безопасно использовать: %s\n", safeString);
        }

        System.out.println("\n--- Запомни ---");
        System.out.println("Переменные-ссылки хранят адрес объекта, а не сам объект.");
        System.out.println("Значение по умолчанию для ссылочных переменных — null.");
        System.out.println("Обращение к методам или полям через null приводит к NullPointerException.");
        System.out.println("Всегда проверяйте переменную на null перед использованием, если не уверены в её инициализации.");
    }
}