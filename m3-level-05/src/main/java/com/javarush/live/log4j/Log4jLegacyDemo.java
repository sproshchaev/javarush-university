package com.javarush.live.log4j;

import org.apache.log4j.*;

public class Log4jLegacyDemo {

    public static void main(String[] args) {

        LogManager.resetConfiguration();
        ConsoleAppender console = new ConsoleAppender(new PatternLayout("%p - %m%n"));
        Logger.getRootLogger().addAppender(console);
        Logger.getRootLogger().setLevel(Level.TRACE);
        Logger log = Logger.getLogger(Log4jLegacyDemo.class);

        log.trace("Трассировка");
        log.debug("Отладка");
        log.info("Инфо");
        log.warn("Предупреждение");
        log.error("Ошибка");
        log.fatal("Критическая ошибка");

        System.out.println("После fatal приложение продолжает работу");
        LogManager.shutdown();
    }


}
