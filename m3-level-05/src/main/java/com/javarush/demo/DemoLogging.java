package com.javarush.demo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.LoggerFactory;

public class DemoLogging {
    public static LoggerContext configure(String level) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.reset();
        PatternLayoutEncoder encoder = encoder(context, "%level %logger{0} - %msg%n");
        ConsoleAppender<ILoggingEvent> console = new ConsoleAppender<>();
        console.setContext(context);
        console.setEncoder(encoder);
        console.start();
        ch.qos.logback.classic.Logger root = context.getLogger("ROOT");
        root.setLevel(Level.toLevel(level));
        root.addAppender(console);
        return context;
    }

    public static PatternLayoutEncoder encoder(LoggerContext context, String pattern) {
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern(pattern);
        encoder.start();
        return encoder;
    }
}
