package com.javarush.live.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LevelsDemo {
    public static void main(String[] args) {
        DemoLogging.configure("WARN");
        Logger log = LoggerFactory.getLogger(LevelsDemo.class);
        log.trace("Трассировка");
        log.debug("Диагностика");
        log.info("Обычная работа");
        log.warn("Нужна проверка состояния");
        log.error("Операция завершилась ошибкой");
    }
}
