package com.javarush.notification;

// Четкий определенный интерфейс
// для различных способов отправки
public interface NotificationService {

    // что отправляем и кому
    void send(String message, String recipient);

}
