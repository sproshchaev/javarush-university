package com.javarush.morse;

import com.javarush.morse.core.MorseCoder;
import com.javarush.morse.exception.MorseException;
import com.javarush.morse.model.ProcessingResult;
import com.javarush.morse.service.FileService;

import java.util.Scanner;

/**
 * Проект Модуля 1 - Точка входа
 */
public class MorseApp {

    private final MorseCoder morseCoder;
    private final FileService fileService;
    private final Scanner scanner;

    public MorseApp(MorseCoder morseCoder, FileService fileService, Scanner scanner) {
        this.morseCoder = morseCoder;
        this.fileService = fileService;
        this.scanner = scanner;
    }


    public static void main(String[] args) {
        // todo точка входа
        // MorseApp app = new MorseApp();
        // app.run();
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
        // todo: обработка кодирования файла
        // 1) получить пути файлов
        // 2) прочитать исходный файл
        // 3) закодировать текст
        // 4) записать результат
        // 5) показать успешный результат
        System.out.println("Кодирование файла:");
        try {
            String inputFile = getInputFilePath();
            String outputFile = getOutputFilePath();

            String context = fileService.readFile(inputFile);
            ProcessingResult result = morseCoder.encodeText(context);
            fileService.writeFile(getOutputFromResult(result), outputFile);

            displaySuccessResult(result, inputFile, outputFile);
        } catch (MorseException e) {
            displayError(e.getMessage());
        }
    }

    private void processDecodeFile() {
        // todo: обработка декодрования файла
        // 1) получить пути
        // 2) прочитать файл
        // 3) декодировать код Морзе
        // 4) показать успешный результат
    }

    private String getInputFilePath() {
        // todo: запрос пути к исходному файлу
        return null;
    }
    private String getOutputFilePath() {
        // todo: запрос пути для записи результата
        return null;
    }

    private void displaySuccessResult(ProcessingResult result, String inputFile, String outputFile) {
        // todo: красивый вывод успешного результата
    }

    private void displayError(String message) {
        // todo: вывод сообщения об ошибке
    }

    private void showAlphabetInfo() {
        // todo: вывод справки по алфавиту Морзе
    }

    // todo дописать
    private String getOutputFromResult(ProcessingResult result) {
        return null;
    }

}
