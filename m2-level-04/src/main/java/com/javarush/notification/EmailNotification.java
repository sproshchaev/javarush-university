package com.javarush.notification;

public class EmailNotification implements Notification{

    @Override
    public void send(String message) {
        System.out.println("Отправка email: " + message);
    }

}
