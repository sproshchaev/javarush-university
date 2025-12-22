package com.javarush.erasetype;

import java.lang.reflect.InvocationTargetException;

public class ErasureTypeDemo {

    // Generic-класс
    static class Box<T> {
        private T item;

        public void set(T item) {
            this.item = item;
        }
        public T get() {
            return item;
        }

//        public T createItem() {
//            return new T(); // Ошибка! не работает из-за стирания типов
//        }
    }

    // Generic-класс с обходом стирания
    static class SmartBox<T> {
        private Class<T> clazz; // Храним Class объекта

        public SmartBox(Class<T> clazz) {
            this.clazz = clazz; // сохраняем тип
        }

        // Мы можем создать T
        public T createItem() throws Exception {
            return clazz.getDeclaredConstructor().newInstance();
        }

    }

    static class Cat {
        public Cat() {
            System.out.println("Создан кот!");
        }
        @Override
        public String toString() {
            return "Кот";
        }
    }

    public static void main(String[] args) throws Exception {

        // Box не может создать T
        Box<String> stringBox = new Box<>();
        stringBox.set("Text");
        System.out.println("Ящик содержит: " + stringBox.get());

        // SmartBox
        SmartBox<Cat> catBox = new SmartBox<>(Cat.class); // Передаем тип!

        Cat cat1 = catBox.createItem(); // Создали кота
        Cat cat2 = catBox.createItem(); // Создали еще кота

        System.out.println("Коты созданы: " + cat1 + ", " + cat2);
        System.out.println("Тип Class: " + Cat.class);

    }


}
