package com.javarush.example;

public class Demo02_WhileSum {
    public static void main(String[] args) {
        System.out.println("=== Сумма чисел от 1 до 10 ===");

        int i = 1;
        int sum = 0;

        while (i <= 10) {
            sum = sum + i;
            System.out.println("Прибавили " + i + ", текущая сумма: " + sum);
            i++;
        }

        System.out.println("Итоговая сумма: " + sum);
    }
}
