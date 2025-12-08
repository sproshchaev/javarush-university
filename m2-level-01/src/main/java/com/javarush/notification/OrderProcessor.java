package com.javarush.notification;

// Модуль потребитель сервисов
public class OrderProcessor {

    // Внедрение через интерфейс (вариант 1 = правильный)
    private NotificationService notification;

    // Внедрение через реализацию (вариант 2 - не рекомендуемый)
    // public EmailService emailService;
    // public OrderProcessor(EmailService emailService) {
    //     this.emailService = emailService;
    // }


    // Зависимость передается из вне (Dependency Injection)
    public OrderProcessor(NotificationService notification) {
        this.notification = notification; // Работаем с интерфейсом, а не с реализацей
    }

    public void processOrder(String orderId, String customer) {
        System.out.println("Обрабатываем заказ ");
        notification.send("Ваш заказ " + orderId + " готов!", customer);
    }

}
