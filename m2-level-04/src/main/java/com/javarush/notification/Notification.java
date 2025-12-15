package com.javarush.notification;

// Интерфейс с дефолтным методом
public interface Notification {

    // Обычный абстрактный метод
    void send(String message);

    // Дефолтный метод с реализацией
    default void sendUrgent(String message) {
        System.out.println("СРОЧНО: " + message.toUpperCase() + "!");
    }

}
