package com.javarush;

import java.net.URI;
import java.nio.file.Path;

public class PathMethodsDemo {

    public static void main(String[] args) {

        Path path = Path.of("/Users/ivan/projects/java/src/Main.java");

        System.out.println("Исходный путь: " + path);            // /Users/ivan/projects/java/src/Main.java
        System.out.println("Родитель: " + path.getParent());     // /Users/ivan/projects/java/src
        System.out.println("Имя файла: " + path.getFileName());  // Имя файла: Main.java
        System.out.println("Корень: " + path.getRoot());         // Корень: /
        System.out.println("Абсолютный? " + path.isAbsolute());  // true

        // todo Вывести примеры остальных методов из слайда №7

    }

}
