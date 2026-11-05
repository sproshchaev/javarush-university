package com.javarush.demo;

import com.javarush.service.PaymentGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class ParamsTest {

    @Mock
    PaymentGateway gateway;

    @Test
    @DisplayName("Правило срабатывает на свои параметры")
    void processPayment_MatchingParams_ReturnsStubbedValue() {
        doReturn(true).when(gateway).processPayment(500.0, "1111");

        assertTrue(gateway.processPayment(500.0, "1111"));
    }

    @Test
    @DisplayName("С чужими параметрами мок возвращает значение по умолчанию")
    void processPayment_OtherParams_ReturnsDefault() {
        // lenient: строгий режим считает вызов с чужими параметрами ошибкой теста,
        // а здесь мы проверяем именно такой вызов
        lenient().doReturn(true).when(gateway).processPayment(500.0, "1111");

        assertFalse(gateway.processPayment(500.0, "2222"));
        assertFalse(gateway.processPayment(100.0, "1111"));
    }

    @Test
    @DisplayName("Два правила для одного метода: каждое срабатывает на свои параметры")
    void processPayment_TwoRules_EachMatchesOwnParams() {
        doReturn(true).when(gateway).processPayment(500.0, "1111");
        doReturn(true).when(gateway).processPayment(100.0, "1111");

        assertTrue(gateway.processPayment(500.0, "1111"));
        assertTrue(gateway.processPayment(100.0, "1111"));
    }
}
