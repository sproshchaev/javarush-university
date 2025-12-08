package com.javarush.notification;

public class EmailService implements NotificationService {

    @Override
    public void send(String message, String recipient) {
        System.out.println("Sending email to " + recipient);
        // Какая-то логика отправки по e-mail
    }
}
