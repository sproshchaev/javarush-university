package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Слайд 10. Множественное наследование интерфейсов.
 * Демонстрирует: реализацию нескольких интерфейсов одним классом,
 * работу через разные интерфейсные ссылки, композицию поведения.
 */
public class Slide10_MultipleInterfaces {

    // Интерфейсы-роли для устройства
    interface Powerable {
        void turnOn();
        void turnOff();
        default void checkPower() {
            System.out.println("Проверка питания... OK");
        }
    }

    interface Connectable {
        void connect();
        void disconnect();
    }

    interface Displayable {
        void showInfo();
    }

    interface Updatable {
        void updateFirmware();
        default boolean canUpdate() {
            return true;
        }
    }

    // Класс, реализующий ВСЕ интерфейсы
    static class SmartTV implements Powerable, Connectable, Displayable, Updatable {
        private String model;
        private boolean isOn = false;
        private boolean isConnected = false;

        public SmartTV(String model) {
            this.model = model;
        }

        // Реализация Powerable
        @Override
        public void turnOn() {
            isOn = true;
            System.out.println(model + ": Включен");
        }

        @Override
        public void turnOff() {
            isOn = false;
            System.out.println(model + ": Выключен");
        }

        // Реализация Connectable
        @Override
        public void connect() {
            if (isOn) {
                isConnected = true;
                System.out.println(model + ": Подключен к интернету");
            } else {
                System.out.println("Сначала включите устройство!");
            }
        }

        @Override
        public void disconnect() {
            isConnected = false;
            System.out.println(model + ": Отключен от интернета");
        }

        // Реализация Displayable
        @Override
        public void showInfo() {
            System.out.println("=== Информация о " + model + " ===");
            System.out.println("Состояние: " + (isOn ? "ВКЛ" : "ВЫКЛ"));
            System.out.println("Интернет: " + (isConnected ? "ПОДКЛ" : "ОТКЛ"));
        }

        // Реализация Updatable
        @Override
        public void updateFirmware() {
            if (isConnected) {
                System.out.println(model + ": Обновление прошивки...");
                System.out.println("Прошивка успешно обновлена!");
            } else {
                System.out.println("Невозможно обновить: нет подключения к интернету");
            }
        }

        // Свой собственный метод класса
        public void playChannel(int channel) {
            if (isOn) {
                System.out.println("Просмотр канала " + channel);
            }
        }
    }

    // Другой класс с другим набором интерфейсов
    static class SmartBulb implements Powerable, Updatable {
        private String location;
        private int brightness = 50;

        public SmartBulb(String location) {
            this.location = location;
        }

        @Override
        public void turnOn() {
            System.out.println("Лампочка в " + location + ": Зажглась");
        }

        @Override
        public void turnOff() {
            System.out.println("Лампочка в " + location + ": Погасла");
        }

        @Override
        public void updateFirmware() {
            System.out.println("Обновление лампочки...");
            brightness = 70;
            System.out.println("Яркость увеличена до " + brightness + "%");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Множественное наследование интерфейсов ===\n");

        SmartTV myTV = new SmartTV("Samsung QLED");
        SmartBulb kitchenLight = new SmartBulb("кухне");

        System.out.println("1. Один объект - много возможностей:");
        myTV.turnOn();
        myTV.checkPower(); // default-метод
        myTV.connect();
        myTV.showInfo();
        myTV.updateFirmware();
        myTV.playChannel(5);

        System.out.println("\n2. Работа через конкретные интерфейсы:");

        // Powerable - видим только методы включения/выключения
        Powerable powerDevice = myTV;
        powerDevice.turnOff();

        // Connectable - видим только методы подключения
        Connectable networkDevice = myTV;
        networkDevice.disconnect();

        System.out.println("\n3. Универсальная обработка устройств:");

        List<Powerable> allDevices = new ArrayList<>();
        allDevices.add(myTV);
        allDevices.add(kitchenLight);

        System.out.println("Включаем все устройства:");
        for (Powerable device : allDevices) {
            device.turnOn();
        }

        System.out.println("\n4. Обновляем всё, что можно обновить:");
        List<Updatable> updatableDevices = new ArrayList<>();
        updatableDevices.add(myTV);
        updatableDevices.add(kitchenLight);

        for (Updatable device : updatableDevices) {
            if (device.canUpdate()) { // default-метод
                device.updateFirmware();
            }
        }

        System.out.println("\n5. Проверка через instanceof:");
        if (myTV instanceof Connectable) {
            System.out.println("Телевизор можно подключить к сети");
        }

        if (kitchenLight instanceof Connectable) {
            System.out.println("Лампочку можно подключить к сети");
        } else {
            System.out.println("Лампочка НЕ поддерживает подключение к сети");
        }
    }
}