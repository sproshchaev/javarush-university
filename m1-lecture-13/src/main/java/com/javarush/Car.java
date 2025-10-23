package com.javarush;

/**
 * Использование внутренних классов
 */
public class Car {   // внешний класс

    // Поле внешнего класса
    String brand = "Toyota";

    public Car() {
        Webasto webasto = new Webasto();
        webasto.start();
    }

    // Внутренний класс
    public class Engine {
       public void start() {
           System.out.println("Двигатель запущен!");
       }
    }

    // Внутренний класс
    private class Webasto {
        public void start() {
            System.out.println("Двигатель: прогрев запущен!");
        }
    }


}
