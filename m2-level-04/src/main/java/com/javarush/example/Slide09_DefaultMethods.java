package com.javarush.example;

/**
 * Слайд 9. Дефолтные методы в интерфейсах.
 * Демонстрирует: default-методы, их переопределение,
 * проблему "ромба" и использование статических методов в интерфейсах.
 */
public class Slide09_DefaultMethods {

    // Интерфейс древнего устройства
    interface OldDevice {
        void turnOn();

        // Новый default-метод, добавленный в Java 8
        // Старые классы не сломаются!
        default void beep() {
            System.out.println("Устройство издает стандартный звук: Бип!");
        }

        // Статический метод в интерфейсе (тоже с Java 8)
        static void printInfo() {
            System.out.println("Интерфейс OldDevice");
        }
    }

    // Современный интерфейс
    interface ModernDevice {
        void connectToWiFi();

        default void beep() {
            System.out.println("Современное устройство: Дзинь-дзинь!");
        }
    }

    // Класс, реализующий только OldDevice
    static class SimpleBeeper implements OldDevice {
        @Override
        public void turnOn() {
            System.out.println("Простое устройство включено");
        }
        // beep() не переопределяем - используем default-реализацию
    }

    // Класс, реализующий оба интерфейса - проблема "ромба"!
    static class SmartDevice implements OldDevice, ModernDevice {
        @Override
        public void turnOn() {
            System.out.println("Умное устройство загружается...");
        }

        @Override
        public void connectToWiFi() {
            System.out.println("Подключаемся к Wi-Fi...");
        }

        // ОБЯЗАНЫ переопределить beep(), т.к. он есть в обоих интерфейсах
        @Override
        public void beep() {
            // Можем выбрать одну из реализаций
            OldDevice.super.beep(); // Вызываем default-метод из OldDevice
            // Или ModernDevice.super.beep();
            // Или написать свою реализацию
            System.out.println("Умное устройство: Мелодичный сигнал!");
        }
    }

    // Класс, который переопределяет default-метод
    static class CustomBeeper implements OldDevice {
        @Override
        public void turnOn() {
            System.out.println("Кастомное устройство готово");
        }

        @Override
        public void beep() {
            System.out.println("Мой уникальный звук: Ту-тууу!");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Дефолтные методы в интерфейсах ===\n");

        System.out.println("1. Использование default-реализации:");
        SimpleBeeper simple = new SimpleBeeper();
        simple.turnOn();
        simple.beep(); // Используется default-метод из интерфейса

        System.out.println("\n2. Переопределение default-метода:");
        CustomBeeper custom = new CustomBeeper();
        custom.turnOn();
        custom.beep(); // Используется своя реализация

        System.out.println("\n3. Проблема 'ромба' (два default-метода):");
        SmartDevice smart = new SmartDevice();
        smart.turnOn();
        smart.connectToWiFi();
        smart.beep(); // Используется своя реализация, решающая конфликт

        System.out.println("\n4. Статический метод в интерфейсе:");
        // Вызывается без создания объекта, через имя интерфейса
        OldDevice.printInfo();

        System.out.println("\n--- Практический пример ---");
        OldDevice[] devices = {simple, custom, smart};

        for (OldDevice device : devices) {
            System.out.print("Устройство: ");
            device.beep(); // Полиморфизм работает и с default-методами!
        }
    }
}