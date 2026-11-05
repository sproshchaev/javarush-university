package com.javarush.demo;

import com.javarush.example.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;

@ExtendWith(MockitoExtension.class)
class MockCreationTest {

    @Mock
    EmailService annotatedMock;

    @Test
    @DisplayName("mock() создает мок без правил: методы возвращают значения по умолчанию")
    void mock_WithoutRules_ReturnsDefaultValues() {
        // Создаем
        EmailService manualMock = mock(EmailService.class);

        // Вызываем
        boolean sent = manualMock.sendEmail("anna@shop.ru", "Тема", "Текст");

        // Проверяем
        assertFalse(sent);
        assertTrue(mockingDetails(manualMock).isMock());
    }

    @Test
    @DisplayName("@Mock: поле заполняет MockitoExtension перед каждым тестом")
    void mockAnnotation_FieldInitializedByExtension_IsMock() {
        assertNotNull(annotatedMock);
        assertTrue(mockingDetails(annotatedMock).isMock());
    }

    @Test
    @DisplayName("Мок списка ничего не хранит: после add размер 0, get возвращает null")
    void mockList_AddElement_StoresNothing() {
        // Создаем
        @SuppressWarnings("unchecked")
        List<String> mockList = mock(List.class);

        // Вызываем
        mockList.add("Java");

        // Проверяем
        assertEquals(0, mockList.size());
        assertNull(mockList.get(0));
    }
}
