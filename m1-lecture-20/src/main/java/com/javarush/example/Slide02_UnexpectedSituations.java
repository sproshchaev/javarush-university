package com.javarush.example;

import java.util.Scanner;

/**
 * Slide02_UnexpectedSituations - демонстрация работы с внештатными ситуациями.
 * Пример: калькулятор с защитой от деления на ноль и некорректного ввода.
 */
public class Slide02_UnexpectedSituations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Калькулятор с обработкой внештатных ситуаций ===");

        try {
            System.out.print("Введите первое число: ");
            int firstNumber = Integer.parseInt(scanner.nextLine());

            System.out.print("Введите второе число: ");
            int secondNumber = Integer.parseInt(scanner.nextLine());

            System.out.print("Выберите операцию (+, -, *, /): ");
            String operation = scanner.nextLine();

            switch (operation) {
                case "+":
                    System.out.println("Результат: " + (firstNumber + secondNumber));
                    break;
                case "-":
                    System.out.println("Результат: " + (firstNumber - secondNumber));
                    break;
                case "*":
                    System.out.println("Результат: " + (firstNumber * secondNumber));
                    break;
                case "/":
                    if (secondNumber == 0) {
                        System.out.println("⚠️ ОШИБКА: Деление на ноль невозможно! Пожалуйста, введите другое число.");
                    } else {
                        System.out.println("Результат: " + (firstNumber / secondNumber));
                    }
                    break;
                default:
                    System.out.println("❌ Неизвестная операция. Допустимые: +, -, *, /");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ ОШИБКА: Введено не число! Пожалуйста, введите корректные целые числа.");
        } finally {
            scanner.close();
            System.out.println("✅ Программа завершена. Спасибо за использование!");
        }
    }
}