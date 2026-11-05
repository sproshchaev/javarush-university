package com.javarush.example;

public class ConsoleEmailService implements EmailService {

    @Override
    public boolean sendEmail(String to, String subject, String body) {
        System.out.println("[EMAIL] " + to + " | " + subject + " | " + body);
        return true;
    }
}
