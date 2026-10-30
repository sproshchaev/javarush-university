package com.javarush.morse;

import com.javarush.morse.exception.MorseException;

import java.util.Scanner;

/**
 * JavaRush-University
 */
public class MorseApp {

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new MorseApp().run();
    }

    private void run() {

        // CLI - command line interface
        System.out.println("Азбука Морзе: кодирование и декодирование файлов");

        while (true) {
            printMenu();
            String choice = ask("Выберите действие: ");
            try {
                switch (choice) {
                    case "1":
                        encodeFile();
                        break;
                    case "2":
                        decodeFile();
                        break;
                    case "3":
                        printApphabet();
                        break;
                    case "0":
                        System.out.println("Пока!");
                        return;
                    default:
                        System.out.println("Нет такого пункта: " + choice);

                }
            } catch (MorseException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

        }

    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Закодировать файл");
        System.out.println("2. Декодировать файл");
        System.out.println("3. Показать алфавит");
        System.out.println("0. Выход");
    }

    private void encodeFile() throws MorseException {
        // TODO: спросить файлы, прочитать строки, проверить и закодировать каждую, записать результат
        System.out.println("Кодировка пока не реализована");
    }

    private void decodeFile() throws MorseException {
        // TODO: тоже для декодирования
        System.out.println("Декодирование пока не реализовано");
    }

    private void printApphabet() {
        // TODO: вывести азбуку
        System.out.println("Справка пока не работает");
    }

    private String ask(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

}
