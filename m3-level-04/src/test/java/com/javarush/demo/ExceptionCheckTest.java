package com.javarush.demo;

import com.javarush.service.ExternalAuditService;
import com.javarush.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class ExceptionCheckTest {

    @Mock
    ExternalAuditService auditService;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("Неверный email: исключение с сообщением, к аудиту не обращались")
    void createUser_InvalidEmail_ThrowsAndSkipsAudit() {
        // Вызываем
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> userService.createUser("anna", "anna-shop.ru"));

        // Проверяем
        assertEquals("Некорректный email", error.getMessage());
        verifyNoInteractions(auditService);
    }

    @Test
    @DisplayName("Сбой аудита: исключение из мока доходит до вызывающего кода")
    void createUser_AuditFails_ExceptionPropagates() {
        // Создаем
        doThrow(new IllegalStateException("Сервис аудита недоступен"))
                .when(auditService).logUserCreation(eq("anna"), anyString());

        // Вызываем
        IllegalStateException error = assertThrows(IllegalStateException.class,
                () -> userService.createUser("anna", "anna@shop.ru"));

        // Проверяем
        assertEquals("Сервис аудита недоступен", error.getMessage());
    }
}
