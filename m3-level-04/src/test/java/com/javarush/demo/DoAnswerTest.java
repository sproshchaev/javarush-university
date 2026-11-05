package com.javarush.demo;

import com.javarush.util.TextFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
class DoAnswerTest {

    @Mock
    TextFormatter formatter;

    @Test
    @DisplayName("doAnswer: ответ мока зависит от аргумента")
    void format_AnswerUsesArgument_ReturnsUpperCase() {
        // Создаем
        doAnswer(invocation -> {
            String text = invocation.getArgument(0);
            return text.toUpperCase();
        }).when(formatter).format(anyString());

        // Вызываем и проверяем
        assertEquals("MOCKITO", formatter.format("mockito"));
        assertEquals("JAVA RUSH", formatter.format("java rush"));
    }
}
