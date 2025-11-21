package com.javarush;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesReadWriteDemo {

    public static void main(String[] args) throws IOException {

        Path testFile = Path.of("test.txt");

        // запись строки в файл
        Files.writeString(testFile, "Hello World!"); // ASCII 1 символ = 1 байт

        // читаем разными способами
        System.out.println(Files.readString(testFile));
        System.out.println("Размер файла: " + Files.size(testFile));      // Размер файла: 12
        System.out.println("Это файл? " + Files.isRegularFile(testFile)); // true
        System.out.println("Это файл? " + Files.isRegularFile(Path.of("test_dir"))); // false


        // todo дописать примеры с остальными методами из слайда №12 :)


    }

}
