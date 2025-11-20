package com.javarush;

import java.util.Scanner;

public class SimpleConsoleReading {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя:");
        String text = scanner.nextLine();
        System.out.println("Привет," + text + "!");
        scanner.close();
    }

}
