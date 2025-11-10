package com.javarush.example;

import java.util.Scanner;

/**
 * Slide03_ExceptionsBasics - базовое понимание исключений в Java.
 * Пример: калькулятор с обработкой исключений при делении на ноль и некорректном вводе.
 */
public class Slide03_ExceptionsBasics {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculation = true;

        System.out.println("=== Калькулятор с обработкой исключений ===");

        while (continueCalculation) {
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
                            throw new ArithmeticException("Деление на ноль недопустимо!");
                        }
                        System.out.println("Результат: " + (firstNumber / secondNumber));
                        break;
                    default:
                        throw new IllegalArgumentException("Неизвестная операция: " + operation);
                }

                System.out.print("Продолжить? (да/нет): ");
                String answer = scanner.nextLine();
                continueCalculation = answer.equalsIgnoreCase("да") || answer.equalsIgnoreCase("yes");

            } catch (NumberFormatException e) {
                System.out.println("❌ ОШИБКА: Введено не целое число. Попробуйте снова.");
            } catch (ArithmeticException e) {
                System.out.println("⚠️ ОШИБКА: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ ОШИБКА: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("🚨 НЕИЗВЕСТНАЯ ОШИБКА: " + e.getMessage());
            } finally {
                System.out.println("---");
            }
        }

        scanner.close();
        System.out.println("✅ Работа калькулятора завершена. До встречи!");
    }
}