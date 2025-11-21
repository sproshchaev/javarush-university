package com.javarush;

import java.nio.file.Path;

public class AbsoluteRelativeDemo {

    public static void main(String[] args) {

        // Users
        //      \ivan
        //           \projects
        //                    \app
        //           \documents

        // Абсолютные пути
        Path absPath1 = Path.of("/Users/ivan/projects/app");
        Path absPath2 = Path.of("/Users/ivan/documents");

        // Относительные пути
        Path relativePath = Path.of("src/Main.java");

        System.out.println("Absolute Path 1: " + absPath1); // Absolute Path 1: /Users/ivan/projects/app
        System.out.println("Absolute Path 2: " + absPath2); // Absolute Path 2: /Users/ivan/documents

        System.out.println("Относительный " + relativePath); // Относительный src/Main.java

        // /Users/sergeyproshchaev/Documents/Java/javarush-university/src/Main.java
        System.out.println("Относительный -> Абсолютный: " + relativePath.toAbsolutePath());

    }

}
