package com.javarush;

import java.nio.file.Path;

public class PathSimpleDemo {

    public static void main(String[] args) {

        // Создание объекта Path
        Path filePath = Path.of("documents/report.txt");
        Path hemePath = Path.of("/Users/ivan/projects");

        System.out.println("Файл: " + filePath);                                   // Файл: documents/report.txt
        System.out.println("Домашняя директория: " + hemePath);                    // Домашняя директория: /Users/ivan/projects
        System.out.println("Тип объекта: " + filePath.getClass().getSimpleName()); // Тип объекта: UnixPath

        // todo сделать для пути Win! :)

    }

}
