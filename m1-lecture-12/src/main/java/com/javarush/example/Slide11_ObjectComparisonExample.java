package com.javarush.example;

import java.util.Objects;

/**
 * Пример, демонстрирующий сравнение объектов в Java по ссылке и по значению.
 * Показывает, почему необходимо переопределять методы equals() и hashCode().
 */
public class Slide11_ObjectComparisonExample {

    // --- Вложенный класс Person ---
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // --- Переопределение метода equals() ---
        @Override
        public boolean equals(Object obj) {
            // 1. Проверка на null и одинаковый тип
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            // 2. Приведение типа
            Person person = (Person) obj;
            // 3. Сравнение полей по значению
            return age == person.age && Objects.equals(name, person.name);
        }

        // --- Переопределение метода hashCode() ---
        @Override
        public int hashCode() {
            // Используем Objects.hash для генерации хэш-кода на основе полей
            return Objects.hash(name, age);
        }

        // --- Метод toString() для удобного вывода ---
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    /**
     * Метод main - точка входа в программу.
     * Демонстрирует сравнение объектов.
     */
    public static void main(String[] args) {
        System.out.println("=== Сравнение объектов в Java ===");

        // Создаем два объекта Person с одинаковыми данными
        Person person1 = new Person("Анна", 25);
        Person person2 = new Person("Анна", 25);

        // Создаем еще один объект, который ссылается на person1
        Person person3 = person1;

        System.out.println("\nОбъекты:");
        System.out.println("person1: " + person1);
        System.out.println("person2: " + person2);
        System.out.println("person3: " + person3); // Это та же ссылка, что и person1

        // --- Сравнение по ссылке (==) ---
        System.out.println("\n--- Сравнение по ссылке (==) ---");
        System.out.println("person1 == person2: " + (person1 == person2)); // false (разные объекты в памяти)
        System.out.println("person1 == person3: " + (person1 == person3)); // true (один и тот же объект)

        // --- Сравнение по значению (equals) ---
        System.out.println("\n--- Сравнение по значению (equals) ---");
        System.out.println("person1.equals(person2): " + person1.equals(person2)); // true (одинаковые данные)
        System.out.println("person1.equals(person3): " + person1.equals(person3)); // true (один и тот же объект, но логически равен сам себе)

        // --- Демонстрация работы hashCode() ---
        System.out.println("\n--- Хэш-коды ---");
        System.out.println("person1.hashCode(): " + person1.hashCode());
        System.out.println("person2.hashCode(): " + person2.hashCode()); // Одинаковый, потому что equals() возвращает true
        System.out.println("person3.hashCode(): " + person3.hashCode()); // Одинаковый с person1, потому что это один объект

        // --- Важное замечание ---
        System.out.println("\nВажно:");
        System.out.println("Если бы мы НЕ переопределили equals() и hashCode(),");
        System.out.println("то person1.equals(person2) вернул бы false,");
        System.out.println("и hashCode() мог бы быть разным для логически равных объектов.");
        System.out.println("Это привело бы к ошибкам при использовании этих объектов в коллекциях (например, HashMap).");
    }
}