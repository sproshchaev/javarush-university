package com.javarush.example;

import java.util.Scanner;
import java.io.*;

/**
 * Slide08_FinallyBlock - демонстрация использования блока finally для гарантированного закрытия ресурсов.
 * Пример: работа с файлом и пользовательским вводом.
 */
public class Slide08_FinallyBlock {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileWriter writer = null;

        System.out.println("=== Запись данных в файл с использованием finally ===");

        try {
            System.out.print("Введите имя файла (например, data.txt): ");
            String fileName = scanner.nextLine();

            writer = new FileWriter(fileName);

            System.out.print("Введите текст для записи: ");
            String text = scanner.nextLine();

            writer.write(text);
            System.out.println("✅ Текст успешно записан в файл: " + fileName);

        } catch (IOException e) {
            System.out.println("❌ ОШИБКА при работе с файлом: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("🚨 НЕИЗВЕСТНАЯ ОШИБКА: " + e.getMessage());
        } finally {
            // Гарантированное закрытие файла — даже если была ошибка!
            if (writer != null) {
                try {
                    writer.close();
                    System.out.println("🔒 Файл успешно закрыт.");
                } catch (IOException e) {
                    System.out.println("⚠️ Не удалось закрыть файл: " + e.getMessage());
                }
            }
            System.out.println("---");
        }

        scanner.close();
        System.out.println("✅ Программа завершена. Все ресурсы освобождены!");
    }
}