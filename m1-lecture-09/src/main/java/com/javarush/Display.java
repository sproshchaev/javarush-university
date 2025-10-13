package com.javarush;


public class Display {

    // Обычная переменная minWidth -> константа MIN_WIDTH

    // (Блок констант) Глобальные константы класса: мин и макс разрешения
    // static - принадлежит классу (не объекту)
    private static final int MIN_WIDTH = 800;
    private static final int MAX_WIDTH = 3840;
    private static final int MIN_HEIGHT = 600;
    private static final int MAX_HEIGHT = 2160;

    // Публичная статическая константа
    public static final String DISPLAY_NAME = "My Display #1";

    // Публичная константа экземпляра класса
    public final String DISPLAY_SN = "1234567";

    // (Блок состояния или полей класса)
    private int width;
    private int height;

    // Вызывается при создании экземпляра класса (объект)
    public Display(int width, int height) {
        // Приведение к минимальному и макс. значению
        this.width = Math.max(MIN_WIDTH, Math.min(width, MAX_WIDTH));
        this.height = Math.max(MIN_HEIGHT, Math.min(height, MAX_HEIGHT));
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Display{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
