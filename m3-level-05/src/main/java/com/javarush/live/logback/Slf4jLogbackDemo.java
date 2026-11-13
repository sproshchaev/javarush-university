package com.javarush.live.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLogbackDemo {
    public static void main(String[] args) {
        DemoLogging.configure("INFO");
        Logger log = LoggerFactory.getLogger(Slf4jLogbackDemo.class);
        log.info("Приложение запущено");
        System.out.println("Реализация: " + log.getClass().getName());
    }
}
