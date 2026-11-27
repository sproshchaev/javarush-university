package com.javarush.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class CatPropertiesDemo {

    public static void main(String[] args) {
        System.out.println("Литерал класса = " + Cat.class.getSimpleName());
        System.out.println("Читаем Cat.namePrefix");
        System.out.println("Префикс = " + Cat.namePrefix);

        new Cat("Барсик");
        new Cat("Мурзик");

    }

    private static class Animal {
        static {
            System.out.println("Animal: static");
        }
    }

    private static class Cat extends Animal {
        private static final String namePrefix;

        static {
            System.out.println("Cat: читаем cat.properties");
            Properties properties = new Properties();

            try (InputStream inputStream = Cat.class.getResourceAsStream("/cat.properties")) {
                if (inputStream == null) {
                    throw new IllegalStateException("Ресурс cat.properties не найден");
                }
                properties.load(inputStream);
            } catch (IOException e) {
                throw new UncheckedIOException("Ошибка чтения cat.properties", e);
            }

            namePrefix = properties.getProperty("name-prefix");
            if (namePrefix == null) {
                throw new IllegalStateException("В cat.properties отсуствует ключ name-prefix");
            }
        }

        Cat(String name) {
            System.out.println("Конструктор Cat: " + namePrefix + name);
        }

    }

}
