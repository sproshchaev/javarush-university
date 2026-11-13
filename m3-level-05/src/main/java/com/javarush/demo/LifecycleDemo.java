package com.javarush.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LifecycleDemo {
    public static void main(String[] args) {
        DemoLogging.configure("INFO");
        Logger log = LoggerFactory.getLogger(LifecycleDemo.class);
        log.info("Приложение запущено");
        boolean mailAvailable = false;
        if (!mailAvailable) {
            log.warn("Сервис рассылки недоступен, отправка отложена");
        }
        log.info("Задача {} завершена со статусом {}", 17, "отложена");
        log.info("Приложение завершено");
    }
}
