package com.javarush.example;

/**
 * Слайд 3, 4, 5, 8.
 * Базовый пример объявления и реализации интерфейса.
 * Демонстрирует: объявление интерфейса, его реализацию в классе,
 * работу через ссылку на интерфейс (полиморфизм) и преимущества такого подхода.
 */
public class Slide03_BasicInterface {

    // ------------------ ИНТЕРФЕЙСЫ (Слайд 3,5) ------------------
    // Интерфейс описывает роль/способность, а не конкретный объект.
    interface Transport {
        // Абстрактный метод (public abstract необязательно писать)
        void move();
    }

    interface Cargo {
        void loadCargo();
        void unloadCargo();
    }
    // -------------------------------------------------------------

    // ------------------ КЛАССЫ (Слайд 5) -------------------------
    // Классы описывают конкретные объекты.
    static class Truck implements Transport, Cargo { // (Слайд 8,10: реализация 2х интерфейсов)
        private String model;

        public Truck(String model) {
            this.model = model;
        }

        // (Слайд 8) Реализуем методы интерфейсов.
        @Override
        public void move() {
            System.out.println(model + " едет по дороге.");
        }

        @Override
        public void loadCargo() {
            System.out.println(model + " загружает товары.");
        }

        @Override
        public void unloadCargo() {
            System.out.println(model + " разгружает товары.");
        }

        // Свой собственный метод класса
        public void honk() {
            System.out.println(model + " сигналит: Би-бип!");
        }
    }

    static class Ship implements Transport, Cargo {
        private String name;

        public Ship(String name) {
            this.name = name;
        }

        @Override
        public void move() {
            System.out.println(name + " плывет по волнам.");
        }

        @Override
        public void loadCargo() {
            System.out.println(name + " принимает контейнеры в трюм.");
        }

        @Override
        public void unloadCargo() {
            System.out.println(name + " выгружает контейнеры в порту.");
        }
    }
    // -------------------------------------------------------------

    public static void main(String[] args) {
        System.out.println("=== Демонстрация базовых интерфейсов ===");

        // (Слайд 4) ПРЕИМУЩЕСТВО: Работаем через интерфейс.
        // Мы не привязаны к конкретному классу Truck или Ship.
        Transport myTransport1 = new Truck("Грузовик 'Волга'");
        Transport myTransport2 = new Ship("Корабль 'Аврора'");

        // Полиморфизм: один метод move(), но разное поведение.
        startJourney(myTransport1);
        startJourney(myTransport2);

        System.out.println("\n--- Работа с ролью 'Cargo' ---");
        // Один объект может поддерживать несколько ролей (интерфейсов).
        Truck kamaz = new Truck("КАМАЗ");
        Cargo cargoVehicle = kamaz; // Теперь видим только методы Cargo

        cargoVehicle.loadCargo();
        // cargoVehicle.honk(); // Ошибка компиляции! Через Cargo не виден метод honk()

        // Но через исходную ссылку на Truck - видно всё.
        kamaz.loadCargo();
        kamaz.honk(); // Свой метод класса

        System.out.println("\n--- Пример смены реализации (Слайд 4) ---");
        Transport logistics = getBestTransport(true); // Меняем логику "на лету"
        logistics.move();
    }

    /**
     * (Слайд 4) Метод зависит от интерфейса, а не от конкретных классов.
     * Это делает код гибким и расширяемым.
     * @param transport любой объект, реализующий Transport
     */
    private static void startJourney(Transport transport) {
        System.out.print("Начинаем путь: ");
        transport.move();
    }

    /**
     * (Слайд 4) Фабричный метод. Возвращает конкретную реализацию,
     * но внешнему миру виден только интерфейс.
     */
    private static Transport getBestTransport(boolean roadAvailable) {
        if (roadAvailable) {
            return new Truck("Экспресс-доставка");
        } else {
            return new Ship("Морской экспресс");
        }
    }
}