package com.javarush.service;

import com.javarush.example.NotificationService;
import com.javarush.model.Order;
import com.javarush.util.IdGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Финал демо: ShopService проверяется отдельно от всех пяти зависимостей.
 */
@ExtendWith(MockitoExtension.class)
class ShopServiceTest {

    @Mock
    UserService userService;

    @Mock
    CachedUserService customers;

    @Mock
    PriceCalculator priceCalculator;

    @Mock
    OrderService orderService;

    @Mock
    NotificationService notificationService;

    @InjectMocks
    ShopService shop;

    private MockedStatic<IdGenerator> idGenerator;

    @BeforeEach
    void fixOrderId() {
        idGenerator = mockStatic(IdGenerator.class);
        idGenerator.when(IdGenerator::nextId).thenReturn("ID-42");
    }

    @AfterEach
    void releaseOrderId() {
        idGenerator.close();
    }

    @Test
    @DisplayName("Оплата прошла: заказ в рублях ушел в OrderService, покупатель получил письмо")
    void placeOrder_PaymentSucceeds_CreatesOrderAndNotifies() {
        // Создаем
        when(customers.getName(7)).thenReturn("Анна");
        when(priceCalculator.toRub(50.0)).thenReturn(4775.0);
        when(orderService.processOrder(any(Order.class))).thenReturn(true);

        // Вызываем
        boolean paid = shop.placeOrder(7, "anna@shop.ru", 50.0, "1111");

        // Проверяем результат
        assertTrue(paid);

        // Проверяем, какой заказ ушел в OrderService
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderService).processOrder(orderCaptor.capture());
        Order order = orderCaptor.getValue();
        assertEquals("ID-42", order.getId());
        assertEquals(4775.0, order.getAmount(), 0.0001);
        assertEquals("1111", order.getCardNumber());

        // Проверяем письмо
        verify(notificationService).notifyUser("anna@shop.ru", "Анна, заказ ID-42 оплачен");
    }

    @Test
    @DisplayName("Оплата не прошла: метод вернул false, покупатель получил письмо об отказе")
    void placeOrder_PaymentDeclined_NotifiesAboutFailure() {
        when(customers.getName(8)).thenReturn("Борис");
        when(priceCalculator.toRub(500.0)).thenReturn(47750.0);
        when(orderService.processOrder(any(Order.class))).thenReturn(false);

        assertFalse(shop.placeOrder(8, "boris@shop.ru", 500.0, "2222"));

        verify(notificationService).notifyUser("boris@shop.ru", "Борис, оплата заказа не прошла");
    }

    @Test
    @DisplayName("Неизвестный покупатель: исключение, заказ и письмо не создаются")
    void placeOrder_UnknownCustomer_ThrowsAndTouchesNothing() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> shop.placeOrder(99, "nobody@shop.ru", 10.0, "0000"));

        assertEquals("Покупатель не найден: 99", error.getMessage());
        verifyNoInteractions(priceCalculator, orderService, notificationService);
    }

    @Test
    @DisplayName("Регистрация передается в UserService")
    void registerCustomer_DelegatesToUserService() {
        when(userService.createUser("anna", "anna@shop.ru")).thenReturn("USER-1");

        assertEquals("USER-1", shop.registerCustomer("anna", "anna@shop.ru"));
    }
}
