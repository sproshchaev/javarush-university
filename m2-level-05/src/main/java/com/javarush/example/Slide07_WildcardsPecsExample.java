package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Подробный пример, объясняющий wildcards (? extends и ? super) и принцип PECS.
 * Объединяет темы слайдов 7 и 9.
 */
public class Slide07_WildcardsPecsExample {

    // ========== ИЕРАРХИЯ КЛАССОВ ==========
    static class Vehicle {
        String name;
        Vehicle(String name) { this.name = name; }
        void move() { System.out.println(name + " двигается."); }
        @Override public String toString() { return name; }
    }

    static class Car extends Vehicle {
        Car(String name) { super(name); }
        void honk() { System.out.println(name + " сигналит: Би-бип!"); }
    }

    static class SportsCar extends Car {
        SportsCar(String name) { super(name); }
        void turbo() { System.out.println(name + " включает турбо!"); }
    }

    static class Bike extends Vehicle {
        Bike(String name) { super(name); }
        void ringBell() { System.out.println(name + " звонит в звонок!"); }
    }

    // ========== МЕТОДЫ ДЛЯ ДЕМОНСТРАЦИИ ==========

    // 1. ПРОИЗВОДИТЕЛЬ (PRODUCER) - только читает, использует ? extends
    // "Дайте мне коллекцию, из которой я могу ПРОЧИТАТЬ объекты типа Vehicle"
    static void processAllVehicles(List<? extends Vehicle> vehicles) {
        System.out.println("\n--- Обработка всех транспортных средств (Producer Extends) ---");
        for (Vehicle v : vehicles) { // Безопасное чтение
            v.move();
            // v.honk(); // Ошибка! Не все Vehicle - Car.
        }
    }

    // 2. ПОТРЕБИТЕЛЬ (CONSUMER) - только пишет, использует ? super
    // "Дайте мне коллекцию, в которую я могу ЗАПИСАТЬ объекты типа Car"
    static void addNewCars(List<? super Car> carList) {
        System.out.println("\n--- Добавление новых машин (Consumer Super) ---");
        carList.add(new Car("Новая Toyota"));
        carList.add(new SportsCar("Новый Ferrari"));
        // carList.add(new Vehicle("Грузовик")); // Ошибка! Vehicle не обязательно Car.
        System.out.println("В список добавлены 2 новые машины.");
    }

    // 3. МЕТОД, КОТОРЫЙ И ЧИТАЕТ, И ПИШЕТ - НЕ использует wildcard
    // "Дайте мне List<Car> и я сделаю с ним что хочу"
    static void repairCars(List<Car> cars) {
        for (Car car : cars) {
            System.out.println("Ремонтируем " + car.name);
        }
    }

    // ========== ТОЧКА ВХОДА ==========
    public static void main(String[] args) {
        System.out.println("========== Создаем коллекции ==========");

        List<Vehicle> vehiclePark = new ArrayList<>();
        vehiclePark.add(new Vehicle("Грузовик"));
        vehiclePark.add(new Car("Седан"));

        List<Car> carGarage = new ArrayList<>();
        carGarage.add(new Car("Хэтчбек"));
        carGarage.add(new SportsCar("Спорткар"));

        List<SportsCar> sportsCollection = new ArrayList<>();
        sportsCollection.add(new SportsCar("Lamborghini"));
        sportsCollection.add(new SportsCar("Porsche"));

        // ========== ДЕМОНСТРАЦИЯ ? extends (PRODUCER) ==========
        System.out.println("\n========== ? extends Vehicle (Можно читать) ==========");

        // Мы можем передать List<SportsCar>, List<Car> или List<Vehicle>
        processAllVehicles(sportsCollection); // ОК: SportsCar extends Vehicle
        processAllVehicles(carGarage);        // ОК: Car extends Vehicle
        processAllVehicles(vehiclePark);      // ОК: Vehicle extends Vehicle

        // Ограничение: нельзя добавлять
        List<? extends Vehicle> readOnlyList = sportsCollection;
        // readOnlyList.add(new Car("Новая")); // ОШИБКА КОМПИЛЯЦИИ!

        // ========== ДЕМОНСТРАЦИЯ ? super (CONSUMER) ==========
        System.out.println("\n========== ? super Car (Можно писать) ==========");

        // Мы можем передать List<Car>, List<Vehicle> или List<Object>
        addNewCars(carGarage);          // ОК: Car super Car
        addNewCars(vehiclePark);        // ОК: Vehicle super Car

        // Ограничение: нельзя передать List<SportsCar>
        // addNewCars(sportsCollection); // ОШИБКА КОМПИЛЯЦИИ: SportsCar не super Car

        System.out.println("\nСодержимое vehiclePark после addNewCars:");
        for (Object obj : vehiclePark) { // Читаем как Object
            System.out.println(" - " + obj);
        }

        // Ограничение: из List<? super Car> читать можно только как Object
        List<? super Car> writeList = vehiclePark;
        Object firstItem = writeList.get(0); // Только Object!
        // Car c = writeList.get(0); // ОШИБКА!

        // ========== ПРИНЦИП PECS ==========
        System.out.println("\n========== ПРИНЦИП PECS (Producer Extends, Consumer Super) ==========");
        System.out.println("PECS — мнемоническое правило для выбора wildcard:");
        System.out.println("- Если параметр только ПРОИЗВОДИТ (отдает) значения -> используй ? extends");
        System.out.println("- Если параметр только ПОТРЕБЛЯЕТ (принимает) значения -> используй ? super");
        System.out.println("- Если параметр и производит, и потребляет -> не используй wildcard.");

        // ========== КОГДА НЕЛЬЗЯ ИСПОЛЬЗОВАТЬ WILDCARD ==========
        System.out.println("\n========== Пример без wildcard (метод repairCars) ==========");

        repairCars(carGarage); // ОК
        // repairCars(sportsCollection); // ОШИБКА: нужен именно List<Car>
        // repairCars(vehiclePark);      // ОШИБКА: нужен именно List<Car>

        System.out.println("\n========== ВЫВОД ==========");
        System.out.println("1. List<Child> не является подтипом List<Parent> (инвариантность Generics).");
        System.out.println("2. ? extends T: позволяет читать элементы как T, но не добавлять (кроме null).");
        System.out.println("3. ? super T: позволяет добавлять объекты T и его подтипов, но читать только как Object.");
        System.out.println("4. Применяйте принцип PECS для проектирования гибких и безопасных API.");
    }
}