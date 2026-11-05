package com.javarush.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetryingPaymentServiceTest {

    @Mock
    PaymentClient client;

    @InjectMocks
    RetryingPaymentService service;

    @Test
    @DisplayName("Первая попытка удачная: клиент банка вызван один раз")
    void processPayment_FirstAttemptSucceeds_CallsClientOnce() {
        when(client.pay(100.0, "1111")).thenReturn(true);

        assertTrue(service.processPayment(100.0, "1111"));
        verify(client, times(1)).pay(100.0, "1111");
    }

    @Test
    @DisplayName("Отказ банка не повторяем: повтор нужен только при сбое")
    void processPayment_BankDeclines_NoRetry() {
        when(client.pay(100.0, "1111")).thenReturn(false);

        assertFalse(service.processPayment(100.0, "1111"));
        verify(client, times(1)).pay(100.0, "1111");
    }

    @Test
    @DisplayName("Две неудачи, затем успех: результат третьей попытки")
    void processPayment_TwoFailuresThenSuccess_ReturnsResultOfThirdAttempt() {
        // Создаем: цепочка ответов, каждый вызов берет следующий
        when(client.pay(100.0, "1111"))
                .thenThrow(new RuntimeException("таймаут"))
                .thenThrow(new RuntimeException("таймаут"))
                .thenReturn(true);

        // Вызываем и проверяем
        assertTrue(service.processPayment(100.0, "1111"));
        verify(client, times(3)).pay(100.0, "1111");
    }

    @Test
    @DisplayName("Все три попытки упали: бросается последнее исключение")
    void processPayment_AllAttemptsFail_ThrowsLastError() {
        when(client.pay(50.0, "2222")).thenThrow(new RuntimeException("сервис недоступен"));

        RuntimeException error = assertThrows(RuntimeException.class,
                () -> service.processPayment(50.0, "2222"));

        assertEquals("сервис недоступен", error.getMessage());
        verify(client, times(RetryingPaymentService.MAX_ATTEMPTS)).pay(50.0, "2222");
    }
}
