package com.javarush.morse;

import com.javarush.morse.core.Alphabet;
import com.javarush.morse.core.MorseCoder;
import com.javarush.morse.exception.MorseException;
import com.javarush.morse.model.ProcessingResult;
import com.javarush.morse.service.FileService;

import java.util.Map;
import java.util.Scanner;

/**
 * Проект Модуля 1 - Точка входа
 */
public class MorseApp {

    private final MorseCoder morseCoder;
    private final FileService fileService;
    private final Scanner scanner;

    public MorseApp() {
        this.morseCoder = new MorseCoder();
        this.fileService = new FileService();
        this.scanner = new Scanner(System.in);
    }


    public static void main(String[] args) {
        MorseApp app = new MorseApp();
        app.run();
    }

    public void run() {
        // todo: главный цикл приложения
        // 1) вывести приветствие
        printWelcomeMessage();

        // 2) Меню (бесконечный цикл)*
        while (true) {
            showMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    processEncodeFile();
                    break;
                case "2":
                    processDecodeFile();
                    break;
                case "3":
                    showAlphabetInfo();
                    break;
                case "0":
                    System.out.println("\nДо свидания!");
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }

        // 3) Обработка выбора пользователя
        // 4) * выход по команде
    }

    private void printWelcomeMessage() {
        System.out.println("АЗБУКА МОРЗЕ v.1.0");
        System.out.println("Профессиональный кодер-декодер");
        System.out.println("=".repeat(50));

    }

    private void showMainMenu() {
        System.out.println("ГЛАВНОЕ МЕНЮ: ");
        System.out.println("1. Закодировать файл (текст -> Морзе)");
        System.out.println("2. Декодировать файл (Морзе -> текст)");
        System.out.println("3. Справка по алфавиту Морзе");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void processEncodeFile() {
        // 1) получить пути файлов 2) прочитать исходный файл 3) закодировать текст 4) записать результат 5) показать успешный результат
        System.out.println("Кодирование файла:");
        try {
            String inputFile = getInputFilePath();
            String outputFile = getOutputFilePath();

            String context = fileService.readFile(inputFile);

            ProcessingResult result = morseCoder.encodeText(context);

            fileService.writeFile(result.getOutputPreview(), outputFile);

            displaySuccessResult(result, inputFile, outputFile);
        } catch (MorseException e) {
            displayError(e.getMessage());
        }
    }

    private void processDecodeFile() {
        // 1) получить пут 2) прочитать файл 3) декодировать код Морзе 4) показать успешный результат
        System.out.println("\n ДЕКОДИРОВАНИЕ ФАЙЛА");

        try {
            String inputFile = getInputFilePath();
            String outputFile = getOutputFilePath();

            String content = fileService.readFile(inputFile);
            ProcessingResult result = morseCoder.decodeText(content);
            fileService.writeFile(result.getOutputPreview(), outputFile);

            displaySuccessResult(result, inputFile, outputFile);

        } catch (MorseException e) {
            displayError(e.getMessage());
        }
    }

    private String getInputFilePath() {
        System.out.print("Введите путь к исходному файлу (с кодом Морзе) и его имя: ");
        return scanner.nextLine().trim();
    }

    private String getOutputFilePath() {
        System.out.print("Введите путь для результата и имя файла в который запишем результат: ");
        return scanner.nextLine().trim();
    }

    private void displaySuccessResult(ProcessingResult result, String inputFile, String outputFile) {
        System.out.println("\n " + result.getMessage());
        System.out.println("Исходный файл: " + inputFile);
        System.out.println("Результат: " + outputFile);

        System.out.println("\nПревью:");
        System.out.println("Вход: " + result.getInputPreview());
        System.out.println("Выход: " + result.getOutputPreview());
    }

    private void displayError(String message) {
        System.out.println("\n Ошибка: " + message + "\n");
    }

    private void showAlphabetInfo() {
        System.out.println("\n АЛФАВИТ МОРЗЕ");
        System.out.println("────────────────");

        System.out.println("\nРУССКИЕ БУКВЫ:");
        int count = 0;
        for (char c = 'А'; c <= 'Я'; c++) {
            String morse = Alphabet.TEXT_TO_MORSE.get(c);
            if (morse != null) {
                System.out.printf("  %c → %s%n", c, morse);
                count++;
            }
        }
        System.out.println("В словаре всего " + count + " РУССКИХ БУКВ");

        System.out.println("\nЦИФРЫ:");
        count = 0;
        for (char c = '0'; c <= '9'; c++) {
            String morse = Alphabet.TEXT_TO_MORSE.get(c);
            if (morse != null) {
                System.out.printf("  %c → %s%n", c, morse);
                count++;
            }
        }
        System.out.println("В словаре всего " + count + " ЦИФР");


        System.out.println("\nЗНАКИ ПРЕПИНАНИЯ И СИМВОЛЫ:");
        // Выводим остальные символы (не буквы и не цифры)
        count = 0;
        for (Map.Entry<Character, String> entry : Alphabet.TEXT_TO_MORSE.entrySet()) {
            char c = entry.getKey();
            if (!(c >= 'А' && c <= 'Я') && !(c >= '0' && c <= '9')) {
                String displayChar = c == ' ' ? "[ПРОБЕЛ]" : String.valueOf(c);
                System.out.printf("  %s → %s%n", displayChar, entry.getValue());
                count++;
            }
        }
        System.out.println("В словаре всего " + count + " ЗНАКОВ ПРЕПИНАНИЯ И СИМВОЛОВ");

        System.out.println("\nПримеры кодирования:");
        System.out.println("  'SOS' → ... --- ...");
        System.out.println("  'ПРИВЕТ' → .--. .-. .. .-- . -");
        System.out.println("\nПравила:");
        System.out.println("• Символы разделяются пробелами");
        System.out.println("• Слова разделяются знаком '/'");
        System.out.println("• Поддерживаются русские буквы, цифры, базовые знаки препинания");
    }


}
