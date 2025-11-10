package com.javarush.example;

import java.util.Scanner;

/**
 * Slide09_ExceptionTypesHierarchy - демонстрация иерархии исключений:
 * Throwable → Error и Exception → RuntimeException.
 * Пример: работа с пользовательским вводом и демонстрация разных типов исключений.
 */
public class Slide09_ExceptionTypesHierarchy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Демонстрация видов исключений ===");

        while (true) {
            try {
                System.out.print("Введите число (или 'exit' для выхода): ");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                int number = Integer.parseInt(input); // Может вызвать NumberFormatException → RuntimeException

                if (number < 0) {
                    throw new IllegalArgumentException("Число не может быть отрицательным!"); // RuntimeException
                }

                if (number > 100) {
                    throw new java.io.IOException("Число слишком большое — это checked exception!"); // Exception
                }

                System.out.println("✅ Введено корректное число: " + number);

            } catch (NumberFormatException e) {
                System.out.println("⚠️ ОШИБКА: Введите целое число!");
            } catch (IllegalArgumentException e) {
                System.out.println("🚨 ОШИБКА (Runtime): " + e.getMessage());
            } catch (java.io.IOException e) {
                System.out.println("💥 ОШИБКА (Checked): " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❓ Неизвестная ошибка: " + e.getMessage());
            } finally {
                System.out.println("---");
            }
        }

        scanner.close();
        System.out.println("✅ Программа завершена. Все типы исключений продемонстрированы!");
    }
}