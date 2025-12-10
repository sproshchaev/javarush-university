package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Подробный пример создания своего generic-класса.
 * 1. Простой класс с параметром типа (Слайд 5).
 * 2. Класс с ограничением (bound) через 'extends' (Слайд 6).
 * Объединяет темы слайдов 5 и 6.
 */
public class Slide05_GenericClassWithBounds {

    // ========== 1. ПРОСТОЙ GENERIC-КЛАСС (Слайд 5) ==========
    static class SimpleZoo<T> {
        private List<T> animals = new ArrayList<>();

        public void addAnimal(T animal) {
            animals.add(animal);
        }

        public T getAnimal(int index) {
            return animals.get(index);
        }

        public void feedAll() {
            System.out.println("Кормим всех животных в простом зоопарке...");
            for (T animal : animals) {
                // Мы не знаем, что это за животное. Можем только вызвать Object.toString()
                System.out.println("  - Покормили: " + animal.toString());
            }
        }
    }

    // ========== 2. GENERIC-КЛАСС С ОГРАНИЧЕНИЕМ (Слайд 6) ==========
    // Иерархия классов для демонстрации
    static abstract class Animal {
        abstract String getName();
        abstract void makeSound();
    }

    static class Cat extends Animal {
        private String name;
        public Cat(String name) { this.name = name; }
        @Override String getName() { return name; }
        @Override void makeSound() { System.out.println(name + " говорит: Мяу!"); }
        public void purr() { System.out.println(name + " мурлычет: Мрррр..."); }
    }

    static class Dog extends Animal {
        private String name;
        public Dog(String name) { this.name = name; }
        @Override String getName() { return name; }
        @Override void makeSound() { System.out.println(name + " говорит: Гав!"); }
        public void wagTail() { System.out.println(name + " виляет хвостом."); }
    }

    // Класс Zoo с ограничением T extends Animal
    // Это значит: "T может быть Animal или любой его подкласс"
    static class Zoo<T extends Animal> {
        private List<T> animals = new ArrayList<>();

        public void addAnimal(T animal) {
            animals.add(animal);
        }

        public T getAnimal(int index) {
            return animals.get(index);
        }

        // КЛЮЧЕВОЕ ПРЕИМУЩЕСТВО: Теперь мы ЗНАЕМ, что T - это Animal.
        // Мы можем вызывать методы класса Animal!
        public void makeAllSounds() {
            System.out.println("В зоопарке раздаются звуки:");
            for (T animal : animals) {
                animal.makeSound(); // Безопасный вызов!
            }
        }

        // Мы также можем использовать специфичные для Animal методы
        public void feedAll() {
            System.out.println("Кормим животных...");
            for (T animal : animals) {
                System.out.println("  - Даем корм " + animal.getName());
            }
        }

        // Пример: мы не можем создать Zoo для чего-то, что не Animal
        // Zoo<String> stringZoo = new Zoo<>(); // ОШИБКА КОМПИЛЯЦИИ!
    }

    // ========== 3. GENERIC-МЕТОД (будет подробнее на слайде 8, но уже здесь) ==========
    // Метод, который работает с любым типом, но в рамках своего вызова
    static <T> T getFirstElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    // ========== ТОЧКА ВХОДА ==========
    public static void main(String[] args) {
        System.out.println("========== ЧАСТЬ 1: Простой generic-класс (SimpleZoo) ==========\n");

        SimpleZoo<Object> anythingZoo = new SimpleZoo<>(); // Может хранить что угодно
        anythingZoo.addAnimal("Строка - не животное"); // Это скомпилируется!
        anythingZoo.addAnimal(42);                     // И это тоже!
        anythingZoo.feedAll(); // Но мы не можем работать с этими объектами как с животными

        System.out.println("\n========== ЧАСТЬ 2: Generic-класс с ограничением (Zoo<T extends Animal>) ==========\n");

        // Создаем специализированные зоопарки
        Zoo<Cat> catZoo = new Zoo<>();
        catZoo.addAnimal(new Cat("Барсик"));
        catZoo.addAnimal(new Cat("Мурзик"));

        Zoo<Dog> dogZoo = new Zoo<>();
        dogZoo.addAnimal(new Dog("Шарик"));
        dogZoo.addAnimal(new Dog("Бобик"));

        // А это НЕ скомпилируется:
        // Zoo<String> stringZoo = new Zoo<>(); // Ошибка: Type parameter 'java.lang.String' is not within its bound

        System.out.println("--- Кошачий зоопарк ---");
        catZoo.makeAllSounds();
        catZoo.feedAll();

        System.out.println("\n--- Собачий зоопарк ---");
        dogZoo.makeAllSounds();
        dogZoo.feedAll();

        System.out.println("\n========== ЧАСТЬ 3: Преимущество ограничений ==========\n");

        // Создаем зоопарк для любых животных
        Zoo<Animal> mixedZoo = new Zoo<>();
        mixedZoo.addAnimal(new Cat("Рыжик"));
        mixedZoo.addAnimal(new Dog("Дружок"));
        // Можем добавить любого наследника Animal

        mixedZoo.makeAllSounds(); // Работает полиморфизм!
        mixedZoo.feedAll();

        System.out.println("\n========== ЧАСТЬ 4: Generic-метод (предварительный взгляд) ==========\n");

        List<String> names = List.of("Анна", "Борис", "Виктор");
        List<Integer> numbers = List.of(10, 20, 30);

        String firstName = getFirstElement(names);
        Integer firstNumber = getFirstElement(numbers);

        System.out.println("Первый элемент списка имен: " + firstName);
        System.out.println("Первый элемент списка чисел: " + firstNumber);

        System.out.println("\n========== ВЫВОД ==========");
        System.out.println("1. Можно создавать свои generic-классы (class MyClass<T>).");
        System.out.println("2. Ограничение <T extends SomeClass> сужает допустимые типы и позволяет");
        System.out.println("   вызывать методы SomeClass внутри generic-класса.");
        System.out.println("3. Без ограничения T может быть любым, но мы ничего о нем не знаем.");
    }
}