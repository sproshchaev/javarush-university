package com.javarush.live.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParametersDemo {
    private static int calculations;

    private static String details() {
        calculations++;
        return "подробное описание";
    }

    public static void main(String[] args) {
        DemoLogging.configure("INFO");
        Logger log = LoggerFactory.getLogger(ParametersDemo.class);
        log.info("Заказ {} обработан, позиций {}", 42, 3);
        log.debug("Подробности: {}", details());
        System.out.println("Вычислений после параметра: " + calculations);
        if (log.isDebugEnabled()) {
            log.debug("Подробности: {}", details());
        }
        System.out.println("Вычислений после проверки уровня: " + calculations);
    }
}
