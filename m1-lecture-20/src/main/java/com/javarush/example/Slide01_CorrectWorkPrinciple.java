package com.javarush.example;

/**
 * Slide01_CorrectWorkPrinciple - пример демонстрации принципа:
 * "Правильно работающая программа" ≠ "Программа, которая просто работает".
 * Главное — читаемость, структура и понятность для других разработчиков.
 */
public class Slide01_CorrectWorkPrinciple {

    public static void main(String[] args) {
        // ❌ Пример "работает, но неправильно":
        // Непонятные имена переменных, отсутствие комментариев, логика скрыта.
        int x = 10;
        int y = 20;
        int z = x + y;
        System.out.println(z); // Выводит 30 — работает!

        // ✅ Пример "работает правильно":
        // Ясные имена, структурированная логика, комментарии.
        int firstNumber = 10;
        int secondNumber = 20;
        int sum = calculateSum(firstNumber, secondNumber);
        System.out.println("Сумма чисел: " + sum);

        // Дополнительный пример: расширяем функциональность без переписывания
        int thirdNumber = 5;
        int totalSum = calculateSum(sum, thirdNumber);
        System.out.println("Общая сумма: " + totalSum);
    }

    /**
     * Метод для вычисления суммы двух чисел.
     * @param a первое число
     * @param b второе число
     * @return сумма a и b
     */
    private static int calculateSum(int a, int b) {
        return a + b;
    }
}