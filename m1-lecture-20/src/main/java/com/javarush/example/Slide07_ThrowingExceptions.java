package com.javarush.example;

import java.util.Scanner;

/**
 * Slide07_ThrowingExceptions - демонстрация явного выбрасывания исключений.
 * Пример: проверка данных пользователя с использованием throw.
 */
public class Slide07_ThrowingExceptions {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Регистрация пользователя с проверкой данных ===");

        while (true) {
            try {
                System.out.print("Введите ваш возраст: ");
                int age = Integer.parseInt(scanner.nextLine());

                System.out.print("Введите ваш email: ");
                String email = scanner.nextLine();

                validateUser(age, email);

                System.out.println("✅ Пользователь успешно зарегистрирован!");
                break; // Выход из цикла при успешной регистрации

            } catch (NumberFormatException e) {
                System.out.println("❌ ОШИБКА: Возраст должен быть целым числом!");
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ ОШИБКА: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("🚨 НЕИЗВЕСТНАЯ ОШИБКА: " + e.getMessage());
            } finally {
                System.out.println("---");
            }
        }

        scanner.close();
        System.out.println("✅ Регистрация завершена. Добро пожаловать!");
    }

    /**
     * Метод проверяет корректность данных пользователя.
     * @param age возраст пользователя
     * @param email email пользователя
     * @throws IllegalArgumentException если данные некорректны
     */
    private static void validateUser(int age, String email) {
        if (age < 18) {
            throw new IllegalArgumentException("Возраст должен быть не менее 18 лет.");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email не может быть пустым.");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email должен содержать символ '@'.");
        }

        System.out.println("✔️ Данные пользователя прошли проверку.");
    }
}