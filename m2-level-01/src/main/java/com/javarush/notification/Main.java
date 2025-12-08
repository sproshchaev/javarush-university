package com.javarush.notification;

public class Main {

    public static void main(String[] args) {

        // Интерфейс                             // Реализация
        NotificationService emailServiceNotif = new EmailService();
        NotificationService smsServiceNotif = new SmsService();

        // OrderProcessor не знает о деталях отправки!
        OrderProcessor processorWithEmail = new OrderProcessor(emailServiceNotif);
        OrderProcessor processorWithSms = new OrderProcessor(smsServiceNotif);

        // Работа с заказами
        processorWithEmail.processOrder("ORD-123", "ivan@mail.com");
        processorWithSms.processOrder("ORD-123", "+79221234567");

        // Завтра мы можем добавить отправку уведомления через Телеграм

    }

}
