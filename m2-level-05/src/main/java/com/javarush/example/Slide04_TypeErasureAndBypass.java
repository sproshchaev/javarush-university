package com.javarush.example;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Подробный пример, демонстрирующий стирание типов (Type Erasure) и
 * классический способ его обхода с помощью передачи Class<T>.
 * Объединяет темы слайдов 4, 10 и 11.
 */
public class Slide04_TypeErasureAndBypass {

    // ========== 1. ПРОСТОЙ GENERIC-КЛАСС (демонстрация стирания) ==========
    static class SimpleStorage<T> {
        private List<T> items = new ArrayList<>();

        public void addItem(T item) {
            items.add(item);
        }

        public T getItem(int index) {
            return items.get(index);
        }

        // ПРОБЛЕМА: Мы не можем создать экземпляр T из-за стирания типов.
        // Следующий метод НЕВОЗМОЖЕН:
        // public T createNewItem() {
        //     return new T(); // Ошибка компиляции: Type parameter 'T' cannot be instantiated directly
        // }

        // Демонстрация: во время выполнения мы не знаем T
        public void printItemType() {
            if (!items.isEmpty()) {
                T item = items.get(0);
                System.out.println("Тип элемента во время выполнения: " + item.getClass().getSimpleName());
                // Но это тип конкретного объекта, а не параметр T самого класса!
            } else {
                System.out.println("Хранилище пусто. Тип T неизвестен (стерт).");
            }
        }
    }

    // ========== 2. GENERIC-КЛАСС С ВОЗМОЖНОСТЬЮ СОЗДАНИЯ ЭКЗЕМПЛЯРОВ (обход стирания) ==========
    static class AdvancedStorage<T> {
        private Class<T> clazz; // Храним ссылку на класс типа T
        private List<T> items = new ArrayList<>();

        // КОНСТРУКТОР принимает Class<T>. Это ключевой момент!
        public AdvancedStorage(Class<T> clazz) {
            this.clazz = clazz;
        }

        public void addItem(T item) {
            items.add(item);
        }

        public T getItem(int index) {
            return items.get(index);
        }

        // РЕШЕНИЕ: Теперь мы можем создать новый экземпляр T!
        public T createNewItem() throws NoSuchMethodException,
                InvocationTargetException,
                InstantiationException,
                IllegalAccessException {
            // Используем рефлексию и переданный Class<T>
            T newItem = clazz.getDeclaredConstructor().newInstance();
            items.add(newItem);
            return newItem;
        }

        public void printStoredType() {
            System.out.println("Параметр T этого хранилища: " + clazz.getSimpleName());
        }
    }

    // ========== 3. КЛАССЫ ДЛЯ ТЕСТИРОВАНИЯ ==========
    static class Book {
        String title = "Неизвестная книга";
        public Book() {}
        public Book(String title) { this.title = title; }
        @Override
        public String toString() { return "Книга: '" + title + "'"; }
    }

    static class Phone {
        String model = "Стандартная модель";
        public Phone() {}
        public Phone(String model) { this.model = model; }
        @Override
        public String toString() { return "Телефон: '" + model + "'"; }
    }

    // ========== ТОЧКА ВХОДА ==========
    public static void main(String[] args) throws Exception {
        System.out.println("========== ЧАСТЬ 1: Демонстрация стирания типов ==========\n");

        SimpleStorage<Book> bookStorage = new SimpleStorage<>();
        bookStorage.addItem(new Book("Java. Эффективное программирование"));
        bookStorage.printItemType(); // Выведет: Book

        SimpleStorage<Phone> phoneStorage = new SimpleStorage<>();
        phoneStorage.addItem(new Phone("Android"));
        phoneStorage.printItemType(); // Выведет: Phone

        // Доказательство стирания: классы хранилищ одинаковые
        System.out.println("\nbookStorage класс: " + bookStorage.getClass());
        System.out.println("phoneStorage класс: " + phoneStorage.getClass());
        System.out.println("Это один и тот же класс? " +
                (bookStorage.getClass() == phoneStorage.getClass()));

        System.out.println("\n========== ЧАСТЬ 2: Обход стирания с помощью Class<T> ==========\n");

        // Создаем AdvancedStorage, передавая Class объекта в конструктор.
        // Теперь хранилище "знает" свой тип T во время выполнения.
        AdvancedStorage<Book> advancedBookStorage = new AdvancedStorage<>(Book.class);
        AdvancedStorage<Phone> advancedPhoneStorage = new AdvancedStorage<>(Phone.class);

        advancedBookStorage.printStoredType(); // Выведет: Book
        advancedPhoneStorage.printStoredType(); // Выведет: Phone

        // Используем фабричный метод createNewItem
        Book newBook = advancedBookStorage.createNewItem();
        Phone newPhone = advancedPhoneStorage.createNewItem();
        System.out.println("\nСоздано через createNewItem():");
        System.out.println(" - " + newBook);
        System.out.println(" - " + newPhone);

        // Также можем добавлять объекты вручную
        advancedBookStorage.addItem(new Book("Clean Code"));
        System.out.println("\nВсе книги в хранилище:");
        for (int i = 0; i < 2; i++) { // У нас там теперь 2 книги
            System.out.println(" - " + advancedBookStorage.getItem(i));
        }

        System.out.println("\n========== ВЫВОД ==========");
        System.out.println("1. Generics обеспечивают безопасность типов на этапе компиляции.");
        System.out.println("2. Во время выполнения (Runtime) информация о типе-параметре стирается (Type Erasure).");
        System.out.println("3. Чтобы работать с типом T во время выполнения (например, создавать его экземпляры),");
        System.out.println("   можно передать Class<T> в конструктор и использовать рефлексию.");
    }
}