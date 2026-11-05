package com.javarush.demo;

import com.javarush.service.CurrencyService;
import com.javarush.service.PriceCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WhenThenReturnTest {

    private static final double DELTA = 0.0001;

    @Mock
    CurrencyService currencyService;

    @InjectMocks
    PriceCalculator calculator;

    @Test
    @DisplayName("when().thenReturn() задает результат так же, как doReturn")
    void toRub_RateIsStubbedWithWhen_MultipliesByRate() {
        // Создаем
        when(currencyService.getUsdRate()).thenReturn(92.0);

        // Вызываем и проверяем
        assertEquals(920.0, calculator.toRub(10), DELTA);

        // void-метод внутрь when не передать, компилятор не пропустит:
        // when(repository.delete("A-1")).thenReturn(null);  // 'void' type not allowed here
    }

    @Test
    @DisplayName("Spy: when() вызывает настоящий get(0), у пустого списка это исключение")
    void spy_WhenOnEmptyList_CallsRealMethodAndThrows() {
        List<String> spyList = spy(new ArrayList<>());

        assertThrows(IndexOutOfBoundsException.class,
                () -> when(spyList.get(0)).thenReturn("Java"));
    }

    @Test
    @DisplayName("Spy: doReturn задает правило без вызова настоящего метода")
    void spy_DoReturnOnEmptyList_ReturnsStub() {
        // Создаем
        List<String> spyList = spy(new ArrayList<>());
        doReturn("Java").when(spyList).get(0);

        // Проверяем
        assertEquals("Java", spyList.get(0));
    }
}
