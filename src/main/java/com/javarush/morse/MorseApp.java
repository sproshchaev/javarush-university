package com.javarush.morse;

import com.javarush.morse.core.Alphabet;
import com.javarush.morse.core.MorseCoder;
import com.javarush.morse.exception.MorseException;
import com.javarush.morse.service.FileManager;
import com.javarush.morse.service.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * JavaRush-University
 */
public class MorseApp {

    private final Scanner scanner = new Scanner(System.in);
    private final FileManager fileManager = new FileManager();
    private final Validator validator = new Validator();
    private final MorseCoder morseCoder = new MorseCoder();

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
                        printAlphabet();
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
        String input = ask("Исходный файл: ");
        String output = ask("Файл для результата: ");
        fileManager.checkDifferent(input, output);

        List<String> lines = fileManager.readLines(input);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            validator.validateText(lines.get(i), i + 1);
            result.add(morseCoder.encode(lines.get(i)));
        }
        fileManager.writeLines(output, result);
        printDone(output, result);
    }

    private void printDone(String output, List<String> result) {
        System.out.println("Готово, строк: " + result.size() + ", результат в файле " + output);
        if (!result.isEmpty()) {
            System.out.println("Первая строка: " + result.get(0));
        }
    }

    private void decodeFile() throws MorseException {
        String input = ask("Файл с кодом Морзе");
        String output = ask("Файл для результата: ");
        fileManager.checkDifferent(input, output);

        List<String> lines = fileManager.readLines(input);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            validator.validateMorse(lines.get(i), i + 1);
            result.add(morseCoder.decode(lines.get(i)));
        }
        fileManager.writeLines(output, result);
        printDone(output, result);
    }

    private void printAlphabet() {
        for (Map.Entry<Character, String> entry : Alphabet.table().entrySet()) {
            char symbol = entry.getKey();
            String name = symbol == ' ' ? "пробел" : String.valueOf(symbol);
            System.out.println(name + " " + entry.getValue());
        }

    }

    private String ask(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

}
