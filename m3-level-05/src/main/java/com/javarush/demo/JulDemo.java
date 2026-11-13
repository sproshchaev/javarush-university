package com.javarush.demo;

import java.util.logging.*;

public class JulDemo {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(JulDemo.class.getName());
        log.setUseParentHandlers(false);
        Handler output = new StreamHandler(System.out, new Formatter() {
            public String format(LogRecord event) {
                return event.getLevel() + " - " + event.getMessage() + "\n";
            }
        });
        output.setLevel(Level.ALL);
        log.addHandler(output);
        log.setLevel(Level.INFO);
        log.fine("Подробности диагностики");
        log.info("Приложение запущено");
        log.warning("Внешний сервис отвечает медленно");
        output.flush();
    }
}
