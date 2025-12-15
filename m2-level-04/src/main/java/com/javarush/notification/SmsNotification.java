package com.javarush.notification;

public class SmsNotification implements Notification{

    @Override
    public void send(String message) {
        System.out.println("Отправляем SMS: " + message);
    }
    @Override
    public void sendUrgent(String message) {
        System.out.println("СМС ТРЕВОГА: " + message + "!");
    }
}
