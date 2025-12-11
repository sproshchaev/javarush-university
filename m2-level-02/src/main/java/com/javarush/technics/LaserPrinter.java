package com.javarush.technics;

public class LaserPrinter extends Printer {

    private String config = "Laser Config";

    // Расширение видимости protected -> public (можно!)
    // Модификатор доступа переопределяющего метода может быть таким же или более открытым, но не более строгим (нельзя сужать)
    @Override
    public void printDoc() {
        System.out.println("Печатаем на Laser принтере");
    }

    // Сужение типа результата Object -> String
    // В Java можно вернуть более конкретный подтип от объявленного в абстрактном классе
    @Override
    public String getConfiguration() {
        return config;
    }

    @Override
    public String getType() {
        return "Лазерный принтер";
    }
}
