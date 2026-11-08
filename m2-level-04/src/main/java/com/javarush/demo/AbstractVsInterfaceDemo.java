package com.javarush.demo;

public class AbstractVsInterfaceDemo {
    public static void main(String[] args) {
        Truck truck = new Truck("КамАЗ", 90);
        System.out.println(truck.info());
        System.out.println("Ограничение из интерфейса: " + SpeedLimited.MAX_SPEED);

        // SpeedLimited.MAX_SPEED = 200;          // ошибка: данные интерфейса final
        // SpeedLimited limit = new SpeedLimited();   // ошибка: объект интерфейса создать нельзя
    }
}

// Интерфейс: данные только public static final, методы без тела
interface SpeedLimited {
    int MAX_SPEED = 120;

    boolean isAllowed(int speed);
}

// Абстрактный класс: любые поля, конструктор, готовые методы
abstract class Transport {
    private String model;
    protected int speed;

    Transport(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }

    String info() {
        return model + ", скорость " + speed + ", " + kind()
                + ", разрешено: " + check();
    }

    abstract String kind();

    abstract boolean check();
}

// Один класс-родитель и интерфейс
class Truck extends Transport implements SpeedLimited {
    Truck(String model, int speed) {
        super(model, speed);
    }

    @Override
    String kind() {
        return "грузовик";
    }

    @Override
    public boolean isAllowed(int speed) {
        return speed <= MAX_SPEED;
    }

    @Override
    boolean check() {
        return isAllowed(speed);
    }
}
