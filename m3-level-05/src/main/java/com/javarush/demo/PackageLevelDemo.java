package com.javarush.demo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;

public class PackageLevelDemo {
    public static void main(String[] args) {
        LoggerContext context = DemoLogging.configure("INFO");
        context.getLogger("com.javarush.payment").setLevel(Level.DEBUG);
        Logger payment = context.getLogger("com.javarush.payment.Service");
        Logger catalog = context.getLogger("com.javarush.catalog.Service");
        payment.debug("Диагностика платежа");
        catalog.debug("Диагностика каталога");
        catalog.info("Каталог готов");
    }
}
