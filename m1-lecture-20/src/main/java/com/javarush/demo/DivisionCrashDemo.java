package com.javarush.demo;

public class DivisionCrashDemo {

    public static void main(String[] args) {

        System.out.println("Начало программы");
        try {

            int a = 10;
            int b = 0; // м ввести вользователь из консоли, или из вх параметра

            int result = a / b; // ArithmeticException: '/ by zero' <- .getMessage()
            System.out.println("Результат: " + result); // <- не выполняется

        } catch (ArithmeticException e) {
            System.out.println("Перехватили исключение " + e.getMessage()); // <- переходим сюда
        }
        System.out.println("Конец программы"); // <- после вып catch мы переходим сюда

    }

}
