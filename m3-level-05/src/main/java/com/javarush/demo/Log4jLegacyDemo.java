package com.javarush.demo;

import org.apache.log4j.*;

public class Log4jLegacyDemo {
    public static void main(String[] args) {
        LogManager.resetConfiguration();
        ConsoleAppender console = new ConsoleAppender(
                new PatternLayout("%p - %m%n"));
        Logger.getRootLogger().addAppender(console);
        Logger.getRootLogger().setLevel(Level.TRACE);
        Logger log = Logger.getLogger(Log4jLegacyDemo.class);
        log.trace("Трассировка");
        log.debug("Отладка");
        log.info("Информация");
        log.warn("Предупреждение");
        log.error("Ошибка операции");
        log.fatal("Критическая ситуация");
        System.out.println("После fatal программа продолжает работу");
        LogManager.shutdown();
    }
}
