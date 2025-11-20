package com.javarush.example;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Slide06_PathExample {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы с Path ===\n");

        // Создание объектов Path (Слайд 6)
        System.out.println("1. СОЗДАНИЕ PATH:");
        Path absolutePath = Path.of("/Users/ivan/projects/java/src/Main.java");
        Path relativePath = Path.of("documents/report.pdf");
        Path complexPath = Path.of("/Users/ivan/projects/../projects/java/./src/../src/Main.java");

        System.out.println("Абсолютный путь: " + absolutePath);
        System.out.println("Относительный путь: " + relativePath);
        System.out.println("Сложный путь: " + complexPath);

        // Методы Path (Слайд 7)
        System.out.println("\n2. МЕТОДЫ PATH:");
        System.out.println("getParent(): " + absolutePath.getParent());
        System.out.println("getFileName(): " + absolutePath.getFileName());
        System.out.println("getRoot(): " + absolutePath.getRoot());
        System.out.println("isAbsolute(): " + absolutePath.isAbsolute());
        System.out.println("isAbsolute(relative): " + relativePath.isAbsolute());

        // Абсолютные и относительные пути (Слайд 8)
        System.out.println("\n3. АБСОЛЮТНЫЕ И ОТНОСИТЕЛЬНЫЕ ПУТИ:");
        System.out.println("relative.toAbsolutePath(): " + relativePath.toAbsolutePath());
        System.out.println("normalize(): " + complexPath.normalize());

        // Декомпозиция пути
        System.out.println("\n4. ДЕКОМПОЗИЦИЯ ПУТИ:");
        System.out.println("getNameCount(): " + absolutePath.getNameCount());
        for (int i = 0; i < absolutePath.getNameCount(); i++) {
            System.out.println("  getName(" + i + "): " + absolutePath.getName(i));
        }
        System.out.println("subpath(1, 3): " + absolutePath.subpath(1, 3));

        // Paths vs Path.of (Слайд 9)
        System.out.println("\n5. PATHS VS PATH.OF:");
        Path pathsWay = Paths.get("/Users/ivan/documents");
        Path ofWay = Path.of("/Users/ivan/documents");
        System.out.println("Paths.get(): " + pathsWay);
        System.out.println("Path.of(): " + ofWay);
        System.out.println("Результат одинаковый: " + pathsWay.equals(ofWay));

        // resolve и relativize
        System.out.println("\n6. RESOLVE И RELATIVIZE:");
        Path base = Path.of("/Users/ivan/projects");
        Path file = Path.of("src/Main.java");
        Path another = Path.of("/Users/ivan/documents");

        System.out.println("base.resolve(file): " + base.resolve(file));
        System.out.println("base.relativize(another): " + base.relativize(another));
    }
}