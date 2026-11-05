package com.javarush.demo;

import com.javarush.model.Order;
import com.javarush.repository.DatabaseRepository;
import com.javarush.service.OrderService;
import com.javarush.service.PaymentGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class InOrderTest {

    @Mock
    DatabaseRepository repository;

    @Mock
    PaymentGateway gateway;

    @InjectMocks
    OrderService orderService;

    private final Order order = new Order("A-2", 300.0, "3333");

    @Test
    @DisplayName("Отказ в оплате: сохранить, провести оплату, удалить — именно в таком порядке")
    void processOrder_PaymentDeclined_SavesChargesDeletesInOrder() {
        // Вызываем: правила для шлюза нет, мок вернет false
        boolean result = orderService.processOrder(order);

        // Проверяем
        assertFalse(result);
        InOrder inOrder = inOrder(repository, gateway);
        inOrder.verify(repository).save(order);
        inOrder.verify(gateway).processPayment(300.0, "3333");
        inOrder.verify(repository).delete("A-2");
    }

    @Test
    @DisplayName("Обратный порядок падает с VerificationInOrderFailure")
    void processOrder_PaymentDeclined_WrongOrderFails() {
        orderService.processOrder(order);

        InOrder inOrder = inOrder(repository, gateway);
        assertThrows(VerificationInOrderFailure.class, () -> {
            inOrder.verify(repository).delete("A-2");
            inOrder.verify(repository).save(order);
        });
    }
}
