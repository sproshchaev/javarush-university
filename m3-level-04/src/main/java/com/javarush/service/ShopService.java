package com.javarush.service;

import com.javarush.example.NotificationService;
import com.javarush.model.Order;
import com.javarush.util.IdGenerator;

/**
 * Магазин: собирает вместе все сервисы модуля.
 * Регистрация покупателя, расчет цены в рублях, оформление заказа с оплатой и уведомление.
 */
public class ShopService {
    private final UserService userService;
    private final CachedUserService customers;
    private final PriceCalculator priceCalculator;
    private final OrderService orderService;
    private final NotificationService notificationService;

    public ShopService(UserService userService,
                       CachedUserService customers,
                       PriceCalculator priceCalculator,
                       OrderService orderService,
                       NotificationService notificationService) {
        this.userService = userService;
        this.customers = customers;
        this.priceCalculator = priceCalculator;
        this.orderService = orderService;
        this.notificationService = notificationService;
    }

    public String registerCustomer(String username, String email) {
        return userService.createUser(username, email);
    }

    public boolean placeOrder(int customerId, String email, double priceUsd, String cardNumber) {
        String name = customers.getName(customerId);
        if (name == null) {
            throw new IllegalArgumentException("Покупатель не найден: " + customerId);
        }

        double amountRub = priceCalculator.toRub(priceUsd);
        Order order = new Order(IdGenerator.nextId(), amountRub, cardNumber);
        boolean paid = orderService.processOrder(order);

        String message = paid
                ? name + ", заказ " + order.getId() + " оплачен"
                : name + ", оплата заказа не прошла";
        notificationService.notifyUser(email, message);
        return paid;
    }
}
