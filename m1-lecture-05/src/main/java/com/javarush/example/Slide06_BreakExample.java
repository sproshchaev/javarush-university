package com.javarush.example;

import java.util.Scanner;

public class Slide06_BreakExample {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("=== Пример команды break ===\n");
        System.out.println("Программа будет читать строки с клавиатуры, пока не введёте 'exit'.\n");

        while (true) { // бесконечный цикл
            System.out.print("Введите строку: ");
            String s = console.nextLine();

            if ("exit".equals(s)) {
                System.out.println("Вы ввели 'exit' — программа завершается.");
                break; // прерываем цикл
            }

            System.out.println("Вы ввели: " + s);
        }

        System.out.println("\nЦикл завершён. Программа продолжает работу после break.");

        console.close();
    }
}