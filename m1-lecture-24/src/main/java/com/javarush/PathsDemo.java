package com.javarush;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsDemo {

    public static void main(String[] args) {

        // старый способ до Java 11
        Path oldWay = Paths.get("/Users/ivan/documents");

        // новый способ Java 11+ (предпочтительнее)
        Path newWay = Path.of("/Users/ivan/documents");

        System.out.println("Paths.get: " + oldWay); // Paths.get: /Users/ivan/documents
        System.out.println("Path.of: " + newWay);   // Path.of: /Users/ivan/documents

        System.out.println("Сравнение: " + oldWay.equals(newWay)); // true

    }

}
