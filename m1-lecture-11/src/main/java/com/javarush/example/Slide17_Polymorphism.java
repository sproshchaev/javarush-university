package com.javarush.example;

/**
 * Слайд 17: Полиморфизм.
 * Демонстрация принципа полиморфизма на примере классов Car и его подклассов.
 */
public class Slide17_Polymorphism {

    public static void main(String[] args) {
        System.out.println("=== Слайд 17: Полиморфизм ===\n");

        // 1. Создание объектов разных типов
        System.out.println("1. Создание объектов разных типов:");
        SportsCar sportsCar = new SportsCar();
        sportsCar.setColor("Красный");
        sportsCar.setEngineVolume(3.0);
        sportsCar.setDoors(2);

        Truck truck = new Truck();
        truck.setColor("Белый");
        truck.setEngineVolume(6.0);
        truck.setDoors(4);

        System.out.printf("Спортивный автомобиль: %s\n", sportsCar);
        System.out.printf("Грузовой автомобиль: %s\n", truck);
        System.out.println();

        // 2. Полиморфизм: переменная родительского типа ссылается на объект дочернего типа
        System.out.println("2. Полиморфизм: использование переменной родительского типа:");
        Car car1 = sportsCar; // Переменная Car ссылается на объект SportsCar
        Car car2 = truck;     // Переменная Car ссылается на объект Truck

        System.out.printf("car1 (тип Car, но реальный объект SportsCar): %s\n", car1);
        System.out.printf("car2 (тип Car, но реальный объект Truck): %s\n", car2);
        System.out.println();

        // 3. Вызов переопределённого метода (ключевой момент полиморфизма!)
        System.out.println("3. Вызов переопределённого метода startEngine():");
        System.out.println("Вызываем startEngine() через переменную родительского типа:");
        car1.startEngine(); // Вызовется метод из SportsCar!
        car2.startEngine(); // Вызовется метод из Truck!

        System.out.println();

        // 4. Демонстрация "одного интерфейса — разные реализации"
        System.out.println("4. Демонстрация \"одного интерфейса — разные реализации\":");
        System.out.println("Мы можем создать массив объектов Car и вызвать startEngine() для каждого:");
        Car[] cars = {sportsCar, truck};

        for (Car car : cars) {
            System.out.print("  ");
            car.startEngine(); // Полиморфизм! Вызовется правильная версия метода для каждого объекта!
        }
        System.out.println();

        // 5. Дополнительный пример: метод drive()
        System.out.println("5. Пример с методом drive():");
        car1.drive(150); // Вызовется метод из Car (не переопределён в SportsCar)
        car2.drive(80);  // Вызовется метод из Car (не переопределён в Truck)

        System.out.println("\n--- Запомни ---");
        System.out.println("Полиморфизм — это возможность работать с объектами разных типов как с одним типом.");
        System.out.println("Поведение объекта определяется его РЕАЛЬНЫМ типом, а не типом переменной.");
        System.out.println("Это достигается через наследование и переопределение методов.");
        System.out.println("Полиморфизм делает код более гибким, расширяемым и удобным для работы с иерархиями классов.");
    }

    // Абстрактный класс Car (можно сделать обычным, но для примера показываем абстракцию)
    static abstract class Car {
        protected String color;
        protected double engineVolume;
        protected int doors;

        // Абстрактный метод (обязательно должен быть реализован в подклассах)
        public abstract void startEngine();

        // Обычный метод (может быть переопределён, но не обязан)
        public void drive(int speed) {
            System.out.printf("Автомобиль движется со скоростью %d км/ч.\n", speed);
        }

        // Геттеры и сеттеры
        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public double getEngineVolume() {
            return engineVolume;
        }

        public void setEngineVolume(double engineVolume) {
            this.engineVolume = engineVolume;
        }

        public int getDoors() {
            return doors;
        }

        public void setDoors(int doors) {
            this.doors = doors;
        }

        @Override
        public String toString() {
            return String.format("%s [Цвет: %s, Объём двигателя: %.1f, Дверей: %d]",
                    getClass().getSimpleName(), color, engineVolume, doors);
        }
    }

    // Конкретный класс SportsCar
    static class SportsCar extends Car {
        @Override
        public void startEngine() {
            System.out.println("Завожу спортивный автомобиль: нажимаю кнопку старта!");
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    // Конкретный класс Truck
    static class Truck extends Car {
        @Override
        public void startEngine() {
            System.out.println("Завожу грузовик: поворачиваю ключ зажигания!");
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}