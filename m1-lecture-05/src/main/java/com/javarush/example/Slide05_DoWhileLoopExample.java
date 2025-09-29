package com.javarush.example;

import java.util.Scanner;

public class Slide05_DoWhileLoopExample {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("=== Пример цикла do-while ===\n");
        System.out.println("Введите 'exit', чтобы выйти. Любое другое слово — продолжим.\n");

        String s;
        do {
            System.out.print("Введите слово: ");
            s = console.nextLine();
            System.out.println("Вы ввели: " + s);
        } while (!"exit".equals(s));

        System.out.println("\nЦикл завершён. Вы ввели 'exit'.");

        console.close();
    }
}
