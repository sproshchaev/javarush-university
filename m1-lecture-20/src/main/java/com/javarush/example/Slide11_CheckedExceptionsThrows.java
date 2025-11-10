package com.javarush.example;

import java.util.Scanner;
import java.io.*;

/**
 * Slide11_CheckedExceptionsThrows - демонстрация использования ключевого слова throws
 * для объявления checked-исключений в сигнатуре метода.
 * Пример: работа с файлами и пользовательским вводом.
 */
public class Slide11_CheckedExceptionsThrows {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Демонстрация работы с checked-исключениями и throws ===");

        while (true) {
            try {
                System.out.print("Введите имя файла для чтения: ");
                String fileName = scanner.nextLine();

                // Вызов метода, который declares throws IOException
                String content = readAndValidateFile(fileName);

                System.out.println("📄 Содержимое файла:");
                System.out.println(content);

            } catch (FileNotFoundException e) {
                System.out.println("❌ ОШИБКА (checked): Файл не найден — " + e.getMessage());
            } catch (IOException e) {
                System.out.println("💥 ОШИБКА (checked): Ошибка при чтении файла — " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("🚨 ОШИБКА (unchecked): " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❓ Неизвестная ошибка: " + e.getMessage());
            } finally {
                System.out.println("---");
            }

            System.out.print("Продолжить? (да/нет): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("да") && !answer.equalsIgnoreCase("yes")) {
                break;
            }
        }

        scanner.close();
        System.out.println("✅ Программа завершена. Все правила работы с throws продемонстрированы!");
    }

    /**
     * Метод читает файл и возвращает его содержимое.
     * Может выбросить checked-исключения: FileNotFoundException, IOException.
     * @param fileName имя файла
     * @return содержимое файла как строка
     * @throws FileNotFoundException если файл не существует
     * @throws IOException если произошла ошибка ввода-вывода
     */
    static String readAndValidateFile(String fileName) throws FileNotFoundException, IOException {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым!"); // unchecked
        }

        // Имитация чтения файла
        if (!fileName.equals("data.txt")) {
            throw new FileNotFoundException("Файл '" + fileName + "' не найден.");
        }

        // В реальном коде здесь было бы чтение из файла...
        return "Это содержимое файла data.txt.\nСтрока 1.\nСтрока 2.\nСтрока 3.";
    }
}