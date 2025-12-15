package com.javarush.notification;

public class NotifDemo {

    public static void main(String[] args) {
        Notification email = new EmailNotification();
        Notification sms = new SmsNotification();

        // Обычные уведомления
        email.send("Hello!"); // Отправка email: Hello!
        sms.send("Напоминание"); // Отправляем SMS: Напоминание

        // Срочные
        // email использует default-реализацию
        email.sendUrgent("Сервис не доступен!"); // СРОЧНО: СЕРВИС НЕ ДОСТУПЕН!!
        // смс использует свою реализацию
        sms.sendUrgent("Сервис не доступен!"); // СМС ТРЕВОГА: Сервис не доступен!!
    }

}
