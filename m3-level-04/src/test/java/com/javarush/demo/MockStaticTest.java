package com.javarush.demo;

import com.javarush.util.IdGenerator;
import com.javarush.util.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mockStatic;

class MockStaticTest {

    @Test
    @DisplayName("Метод без параметров: ссылка на метод, подмена только внутри try")
    void nextId_MockedInsideTry_ReturnsStubThenRealValue() {
        try (MockedStatic<IdGenerator> mocked = mockStatic(IdGenerator.class)) {
            mocked.when(IdGenerator::nextId).thenReturn("ID-42");

            assertEquals("ID-42", IdGenerator.nextId());
            mocked.verify(IdGenerator::nextId);
        }

        assertNotEquals("ID-42", IdGenerator.nextId());
    }

    @Test
    @DisplayName("Метод с параметром: лямбда, остальные вызовы класса возвращают null")
    void toUpperCase_MockedWithLambda_ReturnsStubForGivenArgument() {
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {
            mocked.when(() -> StringUtils.toUpperCase("java")).thenReturn("МОК");

            assertEquals("МОК", StringUtils.toUpperCase("java"));
            assertNull(StringUtils.toUpperCase("kotlin"));
        }

        assertEquals("JAVA", StringUtils.toUpperCase("java"));
    }
}
