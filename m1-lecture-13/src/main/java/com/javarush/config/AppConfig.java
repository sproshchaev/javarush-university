package com.javarush.config;

public class AppConfig {
    public static final String APPLICATION_NAME = "My Application";
    public static int count_run = 0;

    // Метод инициализации приложения
    public static void init(int initCount) {
        count_run = initCount;
    }

}
