package com.javarush.someinterface;

public class DuckDemo {

    public static void main(String[] args) {
        Duck donald = new Duck();

        // один объект - много ролей
        donald.fly(); // I'm flying
        donald.swim(); // I'm swiming
        donald.speak(); // I'm speaking

        Flyable flyableDonald = new Duck();

        // Можем работать с уткой через любую роль
        Flyable flyable = donald;
        Swimmable swimmable = donald;
        Speakable speakable = donald;

        // Через интерфейсы мы вызываем методы объекта класса Duck
        flyable.fly();     // I'm flying
        swimmable.swim();  // I'm swiming
        speakable.speak(); // I'm speaking

        // Массив летающих объектов
        Flyable[] flyObject = {donald, new Flyable() {
            @Override
            public void fly() {
                System.out.println("Самолет летит");
            }
        }
        };

        // Перебор элементов массива
        for (Flyable f : flyObject) {
            f.fly();
        }

    }
}


