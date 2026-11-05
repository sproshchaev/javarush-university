package com.javarush.demo;

import com.javarush.example.EmailService;
import com.javarush.example.NotificationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.verification.TooManyActualInvocations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VerifyTimesTest {

    @Mock
    EmailService emailService;

    @InjectMocks
    NotificationService notificationService;

    private void notifyAboutOrderThreeTimes() {
        notificationService.notifyUser("anna@shop.ru", "Заказ принят");
        notificationService.notifyUser("anna@shop.ru", "Заказ собран");
        notificationService.notifyUser("anna@shop.ru", "Заказ доставлен");
    }

    @Test
    @DisplayName("times, atLeast, atMost, atLeastOnce, never: три письма покупателю")
    void notifyUser_ThreeNotifications_SendsThreeEmails() {
        // Вызываем
        notifyAboutOrderThreeTimes();

        // Проверяем
        verify(emailService, times(3)).sendEmail(eq("anna@shop.ru"), eq("Уведомление"), anyString());
        verify(emailService, atLeast(2)).sendEmail(anyString(), anyString(), anyString());
        verify(emailService, atMost(3)).sendEmail(anyString(), anyString(), anyString());
        verify(emailService, atLeastOnce()).sendEmail("anna@shop.ru", "Уведомление", "Заказ доставлен");
        verify(emailService, never()).sendEmail(eq("boris@shop.ru"), anyString(), anyString());
        verifyNoMoreInteractions(emailService);
    }

    @Test
    @DisplayName("only: единственный вызов единственного метода")
    void notifyUser_SingleNotification_OnlyOneCall() {
        notificationService.notifyUser("boris@shop.ru", "Привет");

        verify(emailService, only()).sendEmail("boris@shop.ru", "Уведомление", "Привет");
    }

    @Test
    @DisplayName("times(2) при трех вызовах падает с TooManyActualInvocations")
    void notifyUser_ThreeNotifications_TimesTwoFails() {
        notifyAboutOrderThreeTimes();

        assertThrows(TooManyActualInvocations.class,
                () -> verify(emailService, times(2)).sendEmail(anyString(), anyString(), anyString()));
    }
}
