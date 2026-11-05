package com.javarush.demo;

import com.javarush.model.Order;
import com.javarush.repository.DatabaseRepository;
import com.javarush.service.OrderService;
import com.javarush.service.PaymentGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VerifyTest {

    @Mock
    DatabaseRepository repository;

    @Mock
    PaymentGateway gateway;

    @InjectMocks
    OrderService orderService;

    private final Order order = new Order("A-1", 500.0, "1111");

    @Test
    @DisplayName("Оплата прошла: заказ сохранен, оплата запрошена с его суммой и картой")
    void processOrder_PaymentSucceeds_SavesOrderAndCharges() {
        // Создаем
        doReturn(true).when(gateway).processPayment(500.0, "1111");

        // Вызываем
        boolean result = orderService.processOrder(order);

        // Проверяем
        assertTrue(result);
        verify(repository).save(order);
        verify(gateway).processPayment(500.0, "1111");
    }

    @Test
    @DisplayName("verify вызова, которого не было, падает с WantedButNotInvoked")
    void processOrder_PaymentSucceeds_DeleteIsNotCalled() {
        doReturn(true).when(gateway).processPayment(500.0, "1111");

        orderService.processOrder(order);

        assertThrows(WantedButNotInvoked.class, () -> verify(repository).delete("A-1"));
    }
}
