package com.javarush.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SpyTest {

    @Spy
    List<String> spyList = new ArrayList<>();

    @Test
    @DisplayName("Spy вызывает настоящие методы: элементы действительно добавляются")
    void spy_AddElements_CallsRealMethods() {
        // Вызываем
        spyList.add("Java");
        spyList.add("Mockito");

        // Проверяем
        assertEquals(2, spyList.size());
        assertEquals("Mockito", spyList.get(1));
        verify(spyList).add("Java");
    }

    @Test
    @DisplayName("Spy: подмененный метод возвращает правило, остальные остаются настоящими")
    void spy_StubOneMethod_OtherMethodsStayReal() {
        // Создаем
        spyList.add("Java");
        doReturn(100).when(spyList).size();

        // Проверяем
        assertEquals(100, spyList.size());
        assertEquals("Java", spyList.get(0));
    }

    @Test
    @DisplayName("spy(ArrayList.class) создает настоящий объект через конструктор без параметров")
    void spyByClass_AddElement_WorksAsRealList() {
        // Создаем
        @SuppressWarnings("unchecked")
        List<String> spyByClass = spy(ArrayList.class);

        // Вызываем
        spyByClass.add("Spring");

        // Проверяем
        assertEquals(List.of("Spring"), spyByClass);
    }
}
