package com.javarush.demo.singletone;

public class SingletonDemo {

    public static void main(String[] args) {

        AppLogger first = AppLogger.getInstance();
        AppLogger second = AppLogger.getInstance();

        // AppLogger logger = new AppLogger();

        System.out.println(first == second); // true

        first.log("Пользователь вошел");
        second.log("Пользователь открыл заказ");


    }

}
