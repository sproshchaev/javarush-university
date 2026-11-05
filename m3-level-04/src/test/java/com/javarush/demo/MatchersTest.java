package com.javarush.demo;

import com.javarush.example.EmailService;
import com.javarush.service.PaymentGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class MatchersTest {

    @Mock
    PaymentGateway gateway;

    @Mock
    EmailService emailService;

    @Test
    @DisplayName("anyDouble и anyString пропускают любые значения своего типа")
    void processPayment_AnyMatchers_MatchAnyValues() {
        doReturn(true).when(gateway).processPayment(anyDouble(), anyString());

        assertTrue(gateway.processPayment(10.0, "1111"));
        assertTrue(gateway.processPayment(99999.0, "7777"));
    }

    @Test
    @DisplayName("anyString не пропускает null")
    void processPayment_NullCard_AnyStringDoesNotMatch() {
        lenient().doReturn(true).when(gateway).processPayment(anyDouble(), anyString());

        assertFalse(gateway.processPayment(10.0, null));
    }

    @Test
    @DisplayName("eq рядом с any: точный адрес, любая тема, включая null")
    void sendEmail_EqWithAny_MatchesOnlyGivenAddress() {
        lenient().doReturn(true).when(emailService)
                .sendEmail(eq("admin@shop.ru"), any(), any());

        assertTrue(emailService.sendEmail("admin@shop.ru", null, "Текст"));
        assertFalse(emailService.sendEmail("anna@shop.ru", "Тема", "Текст"));

        // Шаблон рядом с голым значением — InvalidUseOfMatchersException:
        // doReturn(true).when(gateway).processPayment(anyDouble(), "1111");
    }
}
