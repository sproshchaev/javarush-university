package com.javarush;

/**
 * Порядок инициализации внешнего и внутреннего класса
 */
public class InitializationOrder {

    // -- Внешний класс ---

    // 1) Статическое поле внешнего класса (выполняется первым) (встречается часто)
    public static String staticFieldOuter = initializerStaticFieldOuter();
    public static String staticField;
    public static String[] staticCountryArray;

    // 2) Статический блок внешнего класса (встречается часто)
    static {
        staticField = "staticField"; // Инициализация для статических полей класса
        staticCountryArray = new String[]{"FR", "DE", "TH"};
        System.out.println("2) Внешний статический блок внешнего класса static {...}");
    }

    // 3) Нестатическое поле внешенего класса (встречается часто)
    String instanceFieldOuter = initializerInstanseFieldOuter();
    String field;
    String field2;

    // 4) Блок инициализации внешнего класса (встречается не часто)
    {
        field2 = "field2";
        System.out.println("4) Внешний блок инициализации на объекте {...}");
    }

    // 5) Конструктор внешнего класса (встречается часто)
    public InitializationOrder() {
        field = "field"; // Инициализация полей класса - в конструкторе!
        System.out.println("5) Конструктор внешнего класса");
    }

    // --- Методы инициализации ---
    private static String initializerStaticFieldOuter() {
        System.out.println("1) init Статическое поле внешнего класса - initializerStaticFieldOuter()");
        return "staticFieldOuter";
    }

    private String initializerInstanseFieldOuter() {
        System.out.println("3) init Инициализация поля экземпляра класса - initializerInstanseFieldOuter()");
        return "instanceFieldOuter";
    }

}
