package com.javarush.example;

/**
 * Примеры поиска подстрок в Java
 */
public class Slide10_SubstringSearch {

    public static void main(String[] args) {
        System.out.println("=== Поиск подстрок ===\n");

        String text = "Привет, мир! Привет, Java! Привет, друзья!";
        String searchWord = "Привет";
        String notFound = "Котик";

        // 1. indexOf(String str) — первое вхождение
        int firstIndex = text.indexOf(searchWord);
        System.out.println("Первое вхождение \"" + searchWord + "\": индекс " + firstIndex);

        // 2. indexOf(String str, int index) — поиск с указанной позиции
        int secondIndex = text.indexOf(searchWord, firstIndex + 1);
        System.out.println("Второе вхождение \"" + searchWord + "\": индекс " + secondIndex);

        int thirdIndex = text.indexOf(searchWord, secondIndex + 1);
        System.out.println("Третье вхождение \"" + searchWord + "\": индекс " + thirdIndex);

        // 3. lastIndexOf(String str) — последнее вхождение
        int lastIndex = text.lastIndexOf(searchWord);
        System.out.println("Последнее вхождение \"" + searchWord + "\": индекс " + lastIndex);

        // 4. lastIndexOf(String str, int index) — поиск с конца, но не дальше указанной позиции
        int lastBeforeThird = text.lastIndexOf(searchWord, thirdIndex - 1);
        System.out.println("Последнее вхождение до позиции " + thirdIndex + ": индекс " + lastBeforeThird);

        // 5. Что если подстрока не найдена?
        int notFoundIndex = text.indexOf(notFound);
        System.out.println("\nПоиск \"" + notFound + "\": индекс " + notFoundIndex); // -1

        if (notFoundIndex == -1) {
            System.out.println("Подстрока \"" + notFound + "\" не найдена.");
        }

        // 6. matches(String regex) — проверка по регулярному выражению
        String email = "user@example.com";
        String phone = "+79991234567";
        String invalid = "not-an-email";

        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
        String phoneRegex = "^\\+\\d{11}$";

        System.out.println("\n--- Проверка через matches() ---");
        System.out.println("Email \"" + email + "\" соответствует шаблону? " + email.matches(emailRegex));
        System.out.println("Phone \"" + phone + "\" соответствует шаблону? " + phone.matches(phoneRegex));
        System.out.println("Invalid \"" + invalid + "\" соответствует шаблону? " + invalid.matches(emailRegex));

        // 7. Пример использования в цикле — найти все вхождения
        System.out.println("\n--- Найти все вхождения \"" + searchWord + "\" ---");
        int startIndex = 0;
        int count = 0;
        while (true) {
            int pos = text.indexOf(searchWord, startIndex);
            if (pos == -1) break;
            count++;
            System.out.println("Вхождение #" + count + " на позиции " + pos);
            startIndex = pos + 1; // двигаемся дальше
        }
        System.out.println("Всего найдено: " + count + " вхождений");

        // 8. Важный вывод: всегда проверяйте результат на -1!
        int pos = text.indexOf("не существует");
        if (pos != -1) {
            System.out.println("Найдено на позиции: " + pos);
        } else {
            System.out.println("Не найдено!");
        }
    }
}