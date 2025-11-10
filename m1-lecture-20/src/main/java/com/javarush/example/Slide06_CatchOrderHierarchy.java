package com.javarush.example;

import java.util.Scanner;

/**
 * Slide06_CatchOrderHierarchy - демонстрация правильного порядка catch-блоков
 * и использования иерархии исключений.
 * Пример: калькулятор с детальной обработкой разных типов ошибок.
 */
public class Slide06_CatchOrderHierarchy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] data = {5, 10, 15, 20};

        System.out.println("=== Калькулятор с учётом иерархии исключений ===");

        while (true) {
            try {
                System.out.print("Введите индекс массива (0-3): ");
                int index = Integer.parseInt(scanner.nextLine());

                System.out.print("Введите делитель: ");
                int divisor = Integer.parseInt(scanner.nextLine());

                int value = data[index]; // Может вызвать ArrayIndexOutOfBoundsException
                if (divisor == 0) {
                    throw new ArithmeticException("Деление на ноль запрещено!");
                }

                int result = value / divisor;
                System.out.println("Результат: " + result);

            } catch (NumberFormatException e) {
                System.out.println("❌ ОШИБКА: Введите целое число!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("⚠️ ОШИБКА: Индекс вне диапазона (доступны 0-3).");
            } catch (ArithmeticException e) {
                System.out.println("🚨 ОШИБКА: " + e.getMessage());
            } catch (Exception e) {
                // ⚠️ Этот блок — последний, только для "неожиданных" случаев
                System.out.println("💥 НЕИЗВЕСТНАЯ ОШИБКА: " + e.getMessage());
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
        System.out.println("✅ Программа завершена. Спасибо за использование!");
    }
}