package com.javarush.live.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionDemo {
    public static void main(String[] args) {
        DemoLogging.configure("INFO");
        Logger log = LoggerFactory.getLogger(ExceptionDemo.class);
        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException failure) {
            log.error("Не удалось прочитать количество", failure);
        }
    }
}
