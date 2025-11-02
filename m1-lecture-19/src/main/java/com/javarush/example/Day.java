package com.javarush.example;


/**
 * Пример объявления и использования enum типа Day.
 * Демонстрирует синтаксис объявления, присваивание значений,
 * вывод на экран и базовые операции.
 */
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    // Переопределяем toString() для более читаемого вывода
    @Override
    public String toString() {
        return switch (this) {
            case MONDAY -> "Понедельник";
            case TUESDAY -> "Вторник";
            case WEDNESDAY -> "Среда";
            case THURSDAY -> "Четверг";
            case FRIDAY -> "Пятница";
            case SATURDAY -> "Суббота";
            case SUNDAY -> "Воскресенье";
        };
    }
}