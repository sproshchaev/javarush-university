package com.javarush.demo;

import com.javarush.service.CurrencyService;
import com.javarush.service.PriceCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class DoReturnTest {

    private static final double DELTA = 0.0001;

    @Mock
    CurrencyService currencyService;

    @InjectMocks
    PriceCalculator calculator;

    @Test
    @DisplayName("Без правила мок возвращает курс 0, цена получается нулевой")
    void toRub_WithoutRule_ReturnsZero() {
        assertEquals(0.0, calculator.toRub(10), DELTA);
    }

    @Test
    @DisplayName("doReturn задает курс, калькулятор умножает цену на него")
    void toRub_RateIsStubbed_MultipliesByRate() {
        // Создаем
        doReturn(95.5).when(currencyService).getUsdRate();

        // Вызываем
        double rub = calculator.toRub(10);

        // Проверяем
        assertEquals(955.0, rub, DELTA);
    }
}
