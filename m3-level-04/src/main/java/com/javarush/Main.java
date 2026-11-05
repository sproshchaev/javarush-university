package com.javarush;

import com.javarush.example.ConsoleEmailService;
import com.javarush.example.NotificationService;
import com.javarush.repository.InMemoryOrderRepository;
import com.javarush.repository.InMemoryUserRepository;
import com.javarush.service.*;

import java.util.Map;

/**
 * Интернет-магазин без Spring: все зависимости собираем руками.
 * В тестах те же зависимости заменяются моками Mockito.
 */
public class Main {

    public static void main(String[] args) {
        InMemoryOrderRepository orders = new InMemoryOrderRepository();
        PaymentGateway payments = new RetryingPaymentService(new BankPaymentClient(10_000));
        OrderService orderService = new OrderService(orders, payments);

        ShopService shop = new ShopService(
                new UserService(new ConsoleAuditService()),
                new CachedUserService(new InMemoryUserRepository(Map.of(7, "Анна", 8, "Борис"))),
                new PriceCalculator(new FixedCurrencyService(95.5)),
                orderService,
                new NotificationService(new ConsoleEmailService()));

        shop.registerCustomer("anna", "anna@shop.ru");

        System.out.println("--- Заказ Анны на 50 долларов ---");
        shop.placeOrder(7, "anna@shop.ru", 50, "1111");

        System.out.println("--- Заказ Бориса на 500 долларов ---");
        shop.placeOrder(8, "boris@shop.ru", 500, "2222");

        System.out.println("--- Второй заказ Анны: имя уже в кэше ---");
        shop.placeOrder(7, "anna@shop.ru", 20, "1111");

        System.out.println("Заказов в базе: " + orders.count());
    }
}
