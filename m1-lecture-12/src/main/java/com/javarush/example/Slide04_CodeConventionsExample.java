package com.javarush.example;

/**
 * Пример, демонстрирующий основные принципы Java Code Conventions.
 * Цель — написать код, который легко понять другому программисту.
 * Этот класс моделирует простого пользователя системы.
 */
public class Slide04_CodeConventionsExample {

    // --- 1. Порядок элементов: public static final (константы) ---
    // 2. Именование: UPPER_SNAKE_CASE
    public static final int DEFAULT_AGE = 18;
    public static final String DEFAULT_STATUS = "Inactive";

    // --- 2. Порядок элементов: private static final (внутренние константы) ---
    // 2. Именование: UPPER_SNAKE_CASE
    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 150;

    // --- 3. Порядок элементов: private static (статические поля) ---
    // Пример статического поля (не константы)
    private static int totalUserCount = 0;

    // --- 4. Порядок элементов: Обычные поля (сначала private, потом protected, потом public) ---
    // 2. Именование: lowerCamelCase
    private String userName;
    private int userAge;
    private boolean isActive;
    protected String userRole;

    // --- 5. Порядок элементов: Конструкторы ---
    /**
     * Конструктор класса Slide04_CodeConventionsExample.
     * Имя конструктора совпадает с именем класса.
     * 8. Порядок модификаторов: public (модификатор доступа) - OK.
     *
     * @param name Имя пользователя (не должно быть null или пустым)
     * @param age  Возраст пользователя
     * @throws IllegalArgumentException если имя пустое или возраст некорректный
     */
    public Slide04_CodeConventionsExample(String name, int age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        }
        if (age < MIN_AGE || age > MAX_AGE) {
            System.out.println("Предупреждение: Указанный возраст " + age + " может быть некорректным.");
            age = DEFAULT_AGE;
        }

        this.userName = name;
        this.userAge = age;
        this.isActive = false;
        this.userRole = "USER"; // Роль по умолчанию
        totalUserCount++; // Увеличиваем счётчик при создании экземпляра
    }

    // --- 6. Порядок элементов: Публичные методы ---
    /**
     * Возвращает имя пользователя.
     * 8. Порядок модификаторов: public (модификатор доступа) - OK.
     *
     * @return Имя пользователя
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Устанавливает возраст пользователя.
     * 8. Порядок модификаторов: public (модификатор доступа) - OK.
     *
     * @param age Новый возраст пользователя
     */
    public void setUserAge(int age) {
        // 6. Объявление переменных: переменная объявлена близко к использованию
        int newAge = age;
        if (newAge >= MIN_AGE && newAge <= MAX_AGE) {
            this.userAge = newAge;
        } else {
            System.out.println("Ошибка: Возраст " + newAge + " вне допустимого диапазона [" + MIN_AGE + ", " + MAX_AGE + "]");
        }
    }

    /**
     * Проверяет, активен ли пользователь.
     * 8. Порядок модификаторов: public (модификатор доступа) - OK.
     *
     * @return true, если пользователь активен, false в противном случае
     */
    public boolean isUserActive() {
        return isActive;
    }

    /**
     * Активирует пользователя.
     * 8. Порядок модификаторов: public (модификатор доступа) - OK.
     */
    public void activateUser() {
        this.isActive = true;
    }

    /**
     * Деактивирует пользователя.
     * 8. Порядок модификаторов: public (модификатор доступа) - OK.
     */
    public void deactivateUser() {
        this.isActive = false;
    }

    /**
     * Получает текущий возраст пользователя.
     * 8. Порядок модификаторов: public (модификатор доступа) - OK.
     *
     * @return Текущий возраст
     */
    public int getUserAge() {
        return userAge;
    }

    /**
     * Выводит краткую информацию о пользователе в консоль.
     * 8. Порядок модификаторов: public (модификатор доступа) - OK.
     */
    public void printUserInfo() {
        // 7. Пустые строки и пробелы: пробелы после запятых и вокруг операторов
        System.out.println("Имя: " + userName);
        System.out.println("Возраст: " + userAge);
        System.out.println("Роль: " + userRole);
        System.out.println("Статус: " + (isActive ? "Активен" : "Не активен"));
        System.out.println("Диапазон допустимого возраста: от " + MIN_AGE + " до " + MAX_AGE);
        System.out.println("Значение по умолчанию для возраста: " + DEFAULT_AGE);
        System.out.println("Всего пользователей: " + totalUserCount);
    }

    // --- 7. Порядок элементов: Защищённые методы (protected) ---
    /**
     * Вспомогательный метод для проверки возраста.
     * 8. Порядок модификаторов: protected (модификатор доступа) - OK.
     *
     * @param age Возраст для проверки
     * @return true, если возраст в допустимом диапазоне
     */
    protected boolean isValidAge(int age) {
        return age >= MIN_AGE && age <= MAX_AGE;
    }

    // --- 8. Порядок элементов: Приватные методы (private) ---
    /**
     * Пример приватного метода.
     * 8. Порядок модификаторов: private (модификатор доступа) - OK.
     */
    private void internalProcessing() {
        System.out.println("Выполняется внутренняя обработка для " + userName);
    }

    // --- 9. Порядок элементов: Вложенные классы и интерфейсы ---
    /**
     * Пример вложенного статического класса.
     * Используется UpperCamelCase для имени.
     */
    public static class UserStats {
        private final String name;
        private final int age;

        public UserStats(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    /**
     * Метод main - точка входа в программу.
     * Содержит пример использования класса.
     */
    public static void main(String[] args) {
        // 4. Отступы и форматирование: 4 пробела, фигурные скобки, пробелы
        Slide04_CodeConventionsExample user = new Slide04_CodeConventionsExample("Алексей", 30);

        System.out.println("Информация о пользователе после создания:");
        user.printUserInfo();

        user.setUserAge(31);
        System.out.println("\nПосле изменения возраста:");
        user.printUserInfo();

        user.activateUser();
        System.out.println("\nПосле активации:");
        user.printUserInfo();

        // Пример использования вложенного класса
        System.out.println("\nСоздание статистики пользователя:");
        UserStats stats = new UserStats(user.getUserName(), user.getUserAge());
        System.out.println("Статистика для: " + stats.getName() + ", возраст: " + stats.getAge());
    }
}
