package com.javarush.example;

public class Status {

    public static final Status VALID = new Status(0);
    public static final Status INVALID = new Status(1);
    public static final Status BUSY = new Status(2);
    public static final Status ERROR = new Status(3);

    private static final Status[] values = {VALID, INVALID, BUSY, ERROR};

    private final int value;

    private Status(int value) {
        this.value = value;
    }

    public int ordinal() {
        return this.value;
    }

    public static Status[] value() {
        return values.clone();
    }

    @Override
    public String toString() {
        if (this == VALID) {
            return "ДЕЙСТВУЮЩИЙ";
        }
        if (this == INVALID) {
            return "НЕ ДЕЙСТВУЮЩИЙ";
        }
        if (this == BUSY) {
            return "ЗАНЯТ";
        }
        if (this == ERROR) {
            return "ОШИБКА";
        }
        return "НЕИЗВЕСТНЫЙ СТАТУС";
    }
}
