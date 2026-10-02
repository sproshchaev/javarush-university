package com.javarush.demo.singletone;

public class AppLogger {

    private static final AppLogger INSTANCE = new AppLogger();

    private int count;

    private AppLogger() {
        System.out.println("Создан объект AppLoger");
    }

    public static AppLogger getInstance() {
        return INSTANCE;
    }

    public void log(String mes) {
        count++;
        System.out.println("[" + count +"] " + mes);
    }

}
