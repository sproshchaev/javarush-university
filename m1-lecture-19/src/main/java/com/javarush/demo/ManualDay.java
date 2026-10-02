package com.javarush.demo;

public class ManualDay {
    public static final ManualDay MONDAY = new ManualDay(0);    // 0
    public static final ManualDay TUESDAY = new ManualDay(1);   // 1
    public static final ManualDay WEDNESDAY = new ManualDay(2); // 2
    public static final ManualDay THURSDAY = new ManualDay(3);  // 3
    public static final ManualDay FRIDAY = new ManualDay(4);    // 4
    public static final ManualDay SATURDAY = new ManualDay(5);  // 5
    public static final ManualDay SUNDAY = new ManualDay(6);

    private static final ManualDay[] array = {MONDAY, TUESDAY, WEDNESDAY,
            THURSDAY, FRIDAY, SATURDAY, SUNDAY};

    private final int value;

    private ManualDay(int value) {
        this.value = value;
    }

    public int ordinal() {
        return this.value;
    }

    public static ManualDay[] values() {
        return array;
    }
}
