package com.javarush.demo;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.LoggerFactory;

public class PatternDemo {
    public static void main(String[] args) {
        LoggerContext context = DemoLogging.configure("INFO");
        ch.qos.logback.classic.Logger root = context.getLogger("ROOT");
        root.detachAndStopAllAppenders();
        ConsoleAppender<ILoggingEvent> console = new ConsoleAppender<>();
        console.setContext(context);
        console.setEncoder(DemoLogging.encoder(context, "[%level] %logger{0}: %msg%n"));
        console.start();
        root.addAppender(console);
        LoggerFactory.getLogger(PatternDemo.class).info("Сообщение принято");
    }
}
