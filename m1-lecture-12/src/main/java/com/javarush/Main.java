package com.javarush;

import com.javarush.util.File;

/**
 * JavaRush-University
 */
public class Main {

    public static void main(String[] args) {

        Color carColor = new Color("Белый");
        City city = new City("Мюнхен");
        Brand carBrand = new Brand("bmw", city);

        Car car = new Car(1L, carColor, carBrand);
        Car car2 = new Car(2L, new Color("Черный"), new Brand("bmw", new City("Мюнхен")));

        System.out.println("car: " + car);
        System.out.println("car2: " + car2);

        System.out.println("Автомобиль #1:" + car.getBrand());
        car.setColor(new Color("Зеленый"));
        System.out.println("Изменили цвет у Автомобиля #1:" + car.getColor());

        car.runEngine();

        // Класс без конструктора
        Country country = new Country();
        country.setName("Уругвай");
        System.out.println("country: " + country);


        // Два автомобиля
        Car car3 = new Car(3L, new Color("Черный"), new Brand("bmw", new City("Мюнхен")));
        Car car4 = new Car(3L, new Color("Черный"), new Brand("bmw", new City("Мюнхен")));

        // Переопределение equals и hashCode
        // (1) hashCode - уникальное числовое целочисленное значение объекта
        // (2) equals - метод возвращающий true/false для двух объектов при сравнении
        // (3) эти два метода всегда переопределяются вместе
        // (4) основное свойство сравнения объектов:
        // - если 2 объекта равный по equals - то они всегда будут равны по hashCode
        // - если 2 объекта равный по hashCode - то они не всегда равны по equals (это может быть коллизия!)
        // (5): коллизия - это когда 2 разных объекта имеют одинаковый hashCode
        System.out.println(car3 == car4);      // false
        System.out.println(car3.equals(car4)); // true

        // Утилитарный класс - нельзя создать экземпляр, но можно вызвать методы
        // File file = new File(); // java: File() has private access in com.javarush.util.File
        File.copyFile();

    }

}
