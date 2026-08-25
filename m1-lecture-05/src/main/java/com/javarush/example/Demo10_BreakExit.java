package com.javarush.example;

import java.util.Scanner;

public class Demo10_BreakExit {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Вводите строки. Для выхода наберите exit");

        while (true) {
            String s = console.nextLine();

            if ("exit".equals(s)) {
                break;
            }

            System.out.println("Обработана строка: " + s);
        }

        System.out.println("Вышли из цикла, программа продолжается");
    }
}
