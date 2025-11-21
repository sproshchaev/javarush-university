package com.javarush;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirStreamDemo {

    public static void main(String[] args) {

        Path currentDir = Path.of("."); // текущая директория

        try(DirectoryStream<Path> files = Files.newDirectoryStream(currentDir)) {

            System.out.println("Содержимое текущей директории:");
            for (Path path : files) {
                if (Files.isDirectory(path)) {
                    System.out.println("[DIR] " + path.getFileName()); // каталог
                } else {
                    System.out.println("[FILE] " + path.getFileName()); // файл
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}
