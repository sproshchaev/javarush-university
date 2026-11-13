package com.javarush.demo;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import java.nio.file.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleFileDemo {
    public static void main(String[] args) throws Exception {
        LoggerContext context = DemoLogging.configure("INFO");
        Path file = Path.of("target/logging-demo/application.log");
        Files.createDirectories(file.getParent());
        FileAppender<ILoggingEvent> appender = new FileAppender<>();
        appender.setContext(context);
        appender.setFile(file.toString());
        appender.setAppend(false);
        appender.setEncoder(DemoLogging.encoder(context, "%level %msg%n"));
        appender.start();
        context.getLogger("ROOT").addAppender(appender);
        Logger log = LoggerFactory.getLogger(ConsoleFileDemo.class);
        log.info("Событие записано двум получателям");
        context.stop();
        System.out.println("Файл: " + Files.readString(file).strip());
    }
}
