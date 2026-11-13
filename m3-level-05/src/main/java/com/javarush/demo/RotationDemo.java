package com.javarush.demo;

import java.nio.file.*;
import org.apache.log4j.*;

public class RotationDemo {
    public static void main(String[] args) throws Exception {
        Path directory = Files.createTempDirectory("logging-rotation-");
        Path current = directory.resolve("application.log");
        LogManager.resetConfiguration();
        RollingFileAppender file = new RollingFileAppender(
                new PatternLayout("%m%n"), current.toString(), false);
        file.setMaximumFileSize(60);
        file.setMaxBackupIndex(2);
        Logger log = Logger.getLogger("rotation");
        log.setLevel(Level.INFO);
        log.setAdditivity(false);
        log.addAppender(file);
        for (int i = 1; i <= 5; i++) {
            log.info("Событие " + i + " записано");
        }
        file.close();
        System.out.println("Текущий файл существует: " + Files.exists(current));
        System.out.println("Архив существует: " + Files.exists(directory.resolve("application.log.1")));
        System.out.println("Последнее событие в текущем файле: "
                + Files.readString(current).contains("Событие 5"));
        LogManager.shutdown();
    }
}
