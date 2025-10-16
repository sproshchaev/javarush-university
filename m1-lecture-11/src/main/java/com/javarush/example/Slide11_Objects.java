package com.javarush.example;

import java.util.Scanner;

/**
 * Слайд 11: Объекты.
 * Демонстрация создания объектов с помощью оператора new и понимания терминологии.
 */
public class Slide11_Objects {

    public static void main(String[] args) {
        System.out.println("=== Слайд 11: Объекты ===\n");

        // 1. Создание объекта String
        System.out.println("1. Создание объекта String:");
        String greeting = new String("Привет, мир!"); // Явное создание через new String(...)
        String anotherGreeting = "Привет, мир!"; // Более распространённый способ (строковый литерал)

        System.out.printf("String greeting = new String(\"Привет, мир!\"); -> greeting = \"%s\"\n", greeting);
        System.out.printf("String anotherGreeting = \"Привет, мир!\"; -> anotherGreeting = \"%s\"\n", anotherGreeting);
        System.out.println();

        // 2. Создание объекта Scanner
        System.out.println("2. Создание объекта Scanner:");
        Scanner scanner = new Scanner(System.in); // Создаём объект Scanner для чтения с консоли
        System.out.printf("Scanner scanner = new Scanner(System.in); -> scanner = %s\n", scanner);
        System.out.println("Объект Scanner готов к использованию.");
        // Для демонстрации не будем читать ввод, чтобы не блокировать выполнение
        // scanner.close(); // В реальном коде нужно закрывать сканнер!

        // 3. Создание объекта массива (массивы - тоже объекты!)
        System.out.println("3. Создание объекта массива (int[]):");
        int[] numbers = new int[5]; // Создаём массив на 5 элементов

        System.out.printf("int[] numbers = new int[5]; -> numbers = %s\n", numbers);
        System.out.println("Массив создан. По умолчанию все элементы инициализированы нулями:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("numbers[%d] = %d\n", i, numbers[i]);
        }
        System.out.println();

        // 4. Понятие "экземпляр класса"
        System.out.println("4. Понятие \"экземпляр класса\":");
        // Мы создали несколько объектов:
        // - greeting - это экземпляр класса String
        // - scanner - это экземпляр класса Scanner
        // - numbers - это экземпляр класса int[] (массив целых чисел)
        System.out.println("Переменная 'greeting' ссылается на экземпляр класса String.");
        System.out.println("Переменная 'scanner' ссылается на экземпляр класса Scanner.");
        System.out.println("Переменная 'numbers' ссылается на экземпляр класса int[].");
        System.out.println();

        // 5. Простой пример собственного класса (для иллюстрации)
        System.out.println("5. Пример создания объекта своего класса:");
        Person person1 = new Person("Алексей", 25); // Создаём объект класса Person
        Person person2 = new Person("Екатерина", 30); // Создаём ещё один объект

        System.out.printf("Person person1 = new Person(\"Алексей\", 25); -> %s\n", person1);
        System.out.printf("Person person2 = new Person(\"Екатерина\", 30); -> %s\n", person2);

        System.out.println("\n--- Запомни ---");
        System.out.println("Объект — это экземпляр класса, созданный с помощью оператора new.");
        System.out.println("Ссылочная переменная хранит адрес этого объекта.");
        System.out.println("Массивы в Java также являются объектами.");
        System.out.println("Понятия \"объект класса\" и \"экземпляр класса\" — синонимы.");
    }

    // Вложенный класс для демонстрации
    static class Person {
        private String name;
        private int age;

        // Конструктор
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Переопределение toString() для красивого вывода
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
}