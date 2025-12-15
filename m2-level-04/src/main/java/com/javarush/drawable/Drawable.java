package com.javarush.drawable;

// Интерфейс - способность
public interface Drawable {

    // абстрактный метод
    void draw();

    // default - метод
    default void erase() {
        System.out.println("I'm erasing");
    }
}
