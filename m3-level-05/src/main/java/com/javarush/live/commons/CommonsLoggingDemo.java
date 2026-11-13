package com.javarush.live.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Jdk14Logger;
import java.util.logging.*;

public class CommonsLoggingDemo {
    public static void main(String[] args) {
        Logger backend = Logger.getLogger("commons-demo");
        backend.setUseParentHandlers(false);
        Handler output = new StreamHandler(System.out, new Formatter() {
            public String format(LogRecord event) {
                return event.getLevel() + " - " + event.getMessage() + "\n";
            }
        });
        backend.addHandler(output);
        backend.setLevel(Level.INFO);
        Log log = new Jdk14Logger("commons-demo");
        log.info("Сообщение через общий интерфейс");
        output.flush();
    }
}
