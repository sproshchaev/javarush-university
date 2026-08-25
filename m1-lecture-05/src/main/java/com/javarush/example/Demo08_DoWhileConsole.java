package com.javarush.example;

import java.util.Scanner;

public class Demo08_DoWhileConsole {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Вводите строки. Для выхода наберите exit");

        String s;
        do {
            s = console.nextLine();
            System.out.println("Вы ввели: " + s);
        } while (!"exit".equals(s));

        System.out.println("Работа завершена");
    }
}
