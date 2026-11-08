package com.javarush.demo;

public class ImplementStepsDemo {
    public static void main(String[] args) {
        Walker robot = new Robot();
        robot.walk();
        robot.stop();
    }
}

interface Walker {
    void walk();

    void stop();
}

// Шаг 1: implements, шаг 2: оба метода, шаг 3: методы делают свое дело
class Robot implements Walker {
    @Override
    public void walk() {
        System.out.println("Робот шагает");
    }

    @Override
    public void stop() {
        System.out.println("Робот остановился");
    }
}

// class LazyRobot implements Walker {
//     @Override
//     public void walk() {
//         System.out.println("Иду");
//     }
// }
