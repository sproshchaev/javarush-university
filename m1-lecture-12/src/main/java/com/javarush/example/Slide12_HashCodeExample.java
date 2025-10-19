package com.javarush.example;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

/**
 * Пример, демонстрирующий работу метода hashCode() и его связь с equals().
 * Показывает, почему важно соблюдать контракт между ними.
 */
public class Slide12_HashCodeExample {

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
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Person person = (Person) obj;
            return age == person.age && Objects.equals(name, person.name);
        }

        // --- Переопределение метода hashCode() ---
        @Override
        public int hashCode() {
            // Используем Objects.hash для генерации хэш-кода на основе тех же полей, что и в equals()
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
     * Демонстрирует работу hashCode() и его связь с equals().
     */
    public static void main(String[] args) {
        System.out.println("=== Работа метода hashCode() ===");

        // Создаем два объекта Person с одинаковыми данными
        Person person1 = new Person("Анна", 25);
        Person person2 = new Person("Анна", 25);

        // Создаем еще один объект, который ссылается на person1
        Person person3 = person1;

        System.out.println("\nОбъекты:");
        System.out.println("person1: " + person1);
        System.out.println("person2: " + person2);
        System.out.println("person3: " + person3); // Это та же ссылка, что и person1

        // --- Сравнение по значению (equals) ---
        System.out.println("\n--- Сравнение по значению (equals) ---");
        System.out.println("person1.equals(person2): " + person1.equals(person2)); // true
        System.out.println("person1.equals(person3): " + person1.equals(person3)); // true

        // --- Сравнение хэш-кодов ---
        System.out.println("\n--- Хэш-коды ---");
        System.out.println("person1.hashCode(): " + person1.hashCode());
        System.out.println("person2.hashCode(): " + person2.hashCode()); // Одинаковый, потому что equals() возвращает true
        System.out.println("person3.hashCode(): " + person3.hashCode()); // Одинаковый с person1, потому что это один объект

        // --- Демонстрация важности контракта ---
        System.out.println("\n=== Демонстрация важности контракта hashCode() и equals() ===");

        // Создаем HashSet, который использует hashCode() и equals() для хранения уникальных элементов
        Set<Person> personSet = new HashSet<>();

        // Добавляем объекты
        personSet.add(person1);
        personSet.add(person2); // Этот объект логически равен person1, поэтому не должен добавляться повторно
        personSet.add(person3); // Это тот же объект, что и person1, поэтому не должен добавляться повторно

        System.out.println("\nМножество после добавления person1, person2, person3:");
        for (Person p : personSet) {
            System.out.println(p);
        }
        System.out.println("Размер множества: " + personSet.size());

        // Объяснение
        System.out.println("\nВывод:");
        System.out.println("Хотя мы добавили три объекта, в множестве остался только один.");
        System.out.println("Это произошло потому, что person1 и person2 логически равны (equals() == true),");
        System.out.println("и их hashCode() одинаковы, что соответствует контракту.");
        System.out.println("Поэтому HashSet понимает, что person2 — это дубликат person1 и не добавляет его.");
        System.out.println("person3 — это та же ссылка, что и person1, поэтому он тоже не добавляется.");

        // --- Нарушение контракта (для демонстрации) ---
        System.out.println("\n=== Что будет, если нарушить контракт? ===");
        System.out.println("(В этом примере мы НЕ нарушили контракт, но представим, что сделали.)");
        System.out.println("Если бы мы переопределили equals(), но забыли переопределить hashCode(),");
        System.out.println("то person1 и person2 имели бы разные хэш-коды, даже если equals() возвращает true.");
        System.out.println("Тогда HashSet мог бы добавить оба объекта, считая их разными,");
        System.out.println("что привело бы к ошибке и нарушению логики программы.");
    }
}