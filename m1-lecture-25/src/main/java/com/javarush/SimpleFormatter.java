package com.javarush;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleFormatter {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        // Шаблон форматирования
        // y - год, M - месяц, d - день
        // H - часы (0-23)б m - минуты, s - секунды
        // S - мс, n - наносекунды
        System.out.println(now.format(formatter)); // 24.11.2025 21:42:17
    }

}
