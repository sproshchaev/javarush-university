package com.javarush;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class NewInputStreamDemo {

    public static void main(String[] args) throws IOException {

        Path file = Path.of("test.txt");

        // Старый способ (не рекомендуется)
        // FileInputStream fis = new FileInputStream(file.toFile());

        // Новый способ
        InputStream inputStream = Files.newInputStream(file);

        System.out.println("Поток создан через Files.newInputStream()");
        System.out.println("Файл: " + file.getFileName());
        inputStream.close(); // закрываем поток

    }

}
