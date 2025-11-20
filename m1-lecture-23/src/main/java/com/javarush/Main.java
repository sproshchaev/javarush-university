package com.javarush;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  JavaRush-University
 */
public class Main {
    public static void main(String[] args) {

        // Демо базовых потоков System.in, System.out
        System.out.println("Введите символ:"); // Исходящий `OutputStream` (вывод).

        // Входящий поток для чтения байт
        try {                                     // это `InputStream` (ввод)
            int userInputByte = System.in.read(); // A -> 65, B -> 66 (см табл ASCII)
                                                  // `read()` читает один байт и возвращает его как `int`
            char userInputChar = (char) userInputByte;
            System.out.println("Вы ввели символ: " + userInputChar + " его код: " + userInputByte);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
