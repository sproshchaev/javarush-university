package com.javarush.example;

/**
 * Пример использования констант (final переменных)
 */
public class Slide03_Constants {

    // Объявление локальной константы
    public static void main(String[] args) {
        final int MAX_ATTEMPTS = 3;
        final String APP_NAME = "JavaRush Webinar";

        System.out.println("Название приложения: " + APP_NAME);
        System.out.println("Максимум попыток: " + MAX_ATTEMPTS);

        // ❌ ОШИБКА КОМПИЛЯЦИИ — нельзя изменять final-переменную
        // MAX_ATTEMPTS = 5; // Раскомментируй — и увидишь ошибку!

        // ✅ Глобальные константы — объявляем в классе, делаем public static final
        System.out.println("\n--- Глобальные константы ---");
        System.out.println("Корневая папка: " + Config.SOURCE_ROOT);
        System.out.println("Ширина экрана: " + Config.DISPLAY_WIDTH);
        System.out.println("Высота экрана: " + Config.DISPLAY_HEIGHT);
    }
}

// Отдельный класс для глобальных констант
class Config {
    public static final String SOURCE_ROOT = "c:\\projects\\my\\";
    public static final int DISPLAY_WIDTH = 1024;
    public static final int DISPLAY_HEIGHT = 768;
}