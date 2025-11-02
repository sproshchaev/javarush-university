package com.javarush.example;

enum DayOfWeek {
    ПОНЕДЕЛЬНИК, ВТОРНИК, СРЕДА, ЧЕТВЕРГ, ПЯТНИЦА, СУББОТА, ВОСКРЕСЕНЬЕ;

    // Метод для получения русского названия дня
    public String getRussianName() {
        return switch (this) {
            case ПОНЕДЕЛЬНИК -> "Понедельник";
            case ВТОРНИК -> "Вторник";
            case СРЕДА -> "Среда";
            case ЧЕТВЕРГ -> "Четверг";
            case ПЯТНИЦА -> "Пятница";
            case СУББОТА -> "Суббота";
            case ВОСКРЕСЕНЬЕ -> "Воскресенье";
        };
    }

    // Метод для определения, рабочий ли день
    public boolean isWorkingDay() {
        return this != СУББОТА && this != ВОСКРЕСЕНЬЕ;
    }
}