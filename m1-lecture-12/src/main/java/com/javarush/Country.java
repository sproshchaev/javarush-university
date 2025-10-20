package com.javarush;

public class Country {

    // Поля (состояние)
    private String name;

    // Конструктора нет

    // Геттеры-сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }
}
