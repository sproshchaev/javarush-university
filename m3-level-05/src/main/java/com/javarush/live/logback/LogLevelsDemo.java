package com.javarush.live.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogLevelsDemo {
    private static final Logger log = LoggerFactory.getLogger(LogLevelsDemo.class);

    public static void main(String[] args) {
        DemoLogging.configure("DEBUG");
        log.trace("Трассировка");
        log.debug("Отладка");
        log.info("Информация");
        log.warn("Предупреждение");
        log.error("Ошибка");
        processOrder("1", 10_000);
    }

    public static void processOrder(String orderId, double amount) {
        log.debug("Обработка заказа {} на сумму {}", orderId, amount);
        if (amount > 10_000) {
            log.info("Крупный заказ {} на сумму {}. Требуется дополнительная проверка",
                    orderId, amount);
        }

        try {
            int a = 10;
            int b = 0;
            int result = a / b;
            log.debug("Результат расчета для заказа {}: {}", orderId, result);
        } catch (ArithmeticException failure) {
            log.error("Расчет для заказа " + orderId + " завершился ошибкой", failure);
            return;
        }

        log.info("Заказ {} успешно обработан", orderId);
    }
}
