package com.javarush.example;


/**
 * Слайд 16: Наследование.
 * Демонстрация механизма наследования на примере классов Car и его подклассов.
 */
public class Slide16_Inheritance {

    public static void main(String[] args) {
        System.out.println("=== Слайд 16: Наследование ===\n");

        // 1. Создание объекта родительского класса Car
        System.out.println("1. Создание объекта родительского класса Car:");
        Car genericCar = new Car();
        genericCar.setColor("Красный");
        genericCar.setEngineVolume(2.0);
        genericCar.setDoors(4);

        System.out.printf("Общий автомобиль: %s\n", genericCar);
        System.out.println();

        // 2. Создание объекта дочернего класса Sedan (наследуется от Car)
        System.out.println("2. Создание объекта дочернего класса Sedan:");
        Sedan sedan = new Sedan();
        sedan.setColor("Синий");
        sedan.setEngineVolume(1.8);
        sedan.setDoors(4);
        sedan.setTrunkCapacity(500); // Уникальное поле для Sedan

        System.out.printf("Седан: %s\n", sedan);
        System.out.println();

        // 3. Создание объекта дочернего класса SUV (наследуется от Car)
        System.out.println("3. Создание объекта дочернего класса SUV:");
        SUV suv = new SUV();
        suv.setColor("Чёрный");
        suv.setEngineVolume(3.5);
        suv.setDoors(5);
        suv.setFourWheelDrive(true); // Уникальное поле для SUV

        System.out.printf("Внедорожник: %s\n", suv);
        System.out.println();

        // 4. Демонстрация наследования: доступ к методам родителя
        System.out.println("4. Демонстрация наследования (доступ к методам родителя):");
        System.out.printf("%s готовится к движению.\n", sedan.getModel());
        sedan.drive(80); // Вызываем метод, он печатает сообщение внутри себя
        System.out.printf("%s тормозит.\n", suv.getModel());
        suv.brake(); // Метод наследован от Car
        System.out.println();

        // 5. Переопределение метода (полиморфизм)
        System.out.println("5. Переопределение метода (полиморфизм):");
        System.out.printf("Звук двигателя обычного автомобиля: %s\n", genericCar.engineSound());
        System.out.printf("Звук двигателя седана: %s\n", sedan.engineSound()); // Переопределённый метод
        System.out.printf("Звук двигателя внедорожника: %s\n", suv.engineSound()); // Переопределённый метод
        System.out.println();

        System.out.println("\n--- Запомни ---");
        System.out.println("Наследование — это механизм создания нового класса на основе существующего.");
        System.out.println("Дочерний класс наследует поля и методы родительского класса (кроме private).");
        System.out.println("Это позволяет избежать дублирования кода и создавать логические иерархии классов.");
        System.out.println("Дочерний класс может добавлять новые поля/методы или переопределять методы родителя.");
    }

    // Родительский класс Car
    static class Car {
        protected String color;         // Цвет (защищённое поле - доступно в подклассах)
        protected double engineVolume;  // Объём двигателя
        protected int doors;            // Количество дверей

        // Методы родителя
        public void drive(int speed) {
            System.out.printf("Автомобиль движется со скоростью %d км/ч.\n", speed);
        }

        public void brake() {
            System.out.println("Автомобиль тормозит.");
        }

        public String engineSound() {
            return "Работает двигатель";
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

        // Метод для получения модели (по умолчанию)
        public String getModel() {
            return "Общий автомобиль";
        }

        @Override
        public String toString() {
            return String.format("%s [Цвет: %s, Объём двигателя: %.1f, Дверей: %d]",
                    getModel(), color, engineVolume, doors);
        }
    }

    // Дочерний класс Sedan (наследуется от Car)
    static class Sedan extends Car {
        private int trunkCapacity; // Уникальное поле для седана

        // Уникальный метод для седана
        public void openTrunk() {
            System.out.printf("Багажник седана открыт. Вместимость: %d литров.\n", trunkCapacity);
        }

        // Переопределение метода родителя
        @Override
        public String engineSound() {
            return "Тихий звук двигателя седана";
        }

        // Переопределение метода для получения модели
        @Override
        public String getModel() {
            return "Седан";
        }

        // Геттер и сеттер для уникального поля
        public int getTrunkCapacity() {
            return trunkCapacity;
        }

        public void setTrunkCapacity(int trunkCapacity) {
            this.trunkCapacity = trunkCapacity;
        }

        @Override
        public String toString() {
            return super.toString() + String.format(", Багажник: %d л", trunkCapacity);
        }
    }

    // Дочерний класс SUV (наследуется от Car)
    static class SUV extends Car {
        private boolean fourWheelDrive; // Уникальное поле для SUV

        // Уникальный метод для SUV
        public void activateFourWheelDrive() {
            if (fourWheelDrive) {
                System.out.println("Полный привод активирован.");
            } else {
                System.out.println("У этого SUV нет полного привода.");
            }
        }

        // Переопределение метода родителя
        @Override
        public String engineSound() {
            return "Мощный звук двигателя внедорожника";
        }

        // Переопределение метода для получения модели
        @Override
        public String getModel() {
            return "Внедорожник";
        }

        // Геттер и сеттер для уникального поля
        public boolean isFourWheelDrive() {
            return fourWheelDrive;
        }

        public void setFourWheelDrive(boolean fourWheelDrive) {
            this.fourWheelDrive = fourWheelDrive;
        }

        @Override
        public String toString() {
            return super.toString() + String.format(", Полный привод: %s", fourWheelDrive ? "да" : "нет");
        }
    }
}
