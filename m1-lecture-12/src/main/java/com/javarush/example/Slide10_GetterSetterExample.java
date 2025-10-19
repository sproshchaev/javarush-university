package com.javarush.example;

/**
 * Пример, демонстрирующий использование геттеров и сеттеров для доступа к private полям.
 * Это реализация принципа инкапсуляции.
 */
public class Slide10_GetterSetterExample {

    // --- Поля класса ---
    // Все поля объявлены как private, согласно правилам инкапсуляции
    private String productName;
    private int productPrice;
    private boolean isAvailable;

    // --- Конструкторы ---

    /**
     * Конструктор с параметрами.
     *
     * @param name Название продукта
     * @param price Цена продукта
     * @param available Признак доступности
     */
    public Slide10_GetterSetterExample(String name, int price, boolean available) {
        this.productName = name;
        this.productPrice = price;
        this.isAvailable = available;
    }

    // --- Геттеры (get-методы) ---

    /**
     * Возвращает название продукта.
     *
     * @return Название продукта
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Возвращает цену продукта.
     *
     * @return Цена продукта
     */
    public int getProductPrice() {
        return productPrice;
    }

    /**
     * Возвращает признак доступности продукта.
     *
     * @return true, если продукт доступен, false в противном случае
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    // --- Сеттеры (set-методы) ---

    /**
     * Устанавливает новое название продукта.
     *
     * @param productName Новое название продукта
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Устанавливает новую цену продукта.
     * Здесь можно добавить проверку на корректность цены (например, не отрицательную).
     *
     * @param productPrice Новая цена продукта
     */
    public void setProductPrice(int productPrice) {
        if (productPrice >= 0) {
            this.productPrice = productPrice;
        } else {
            System.out.println("Ошибка: Цена не может быть отрицательной.");
        }
    }

    /**
     * Устанавливает признак доступности продукта.
     *
     * @param available Новое значение признака доступности
     */
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    // --- Публичные методы ---
    /**
     * Выводит информацию о продукте в консоль.
     */
    public void displayInfo() {
        System.out.println("Название: " + productName);
        System.out.println("Цена: " + productPrice + " руб.");
        System.out.println("Доступен: " + (isAvailable ? "Да" : "Нет"));
        System.out.println("---");
    }

    /**
     * Метод main - точка входа в программу.
     * Демонстрирует создание объекта и использование геттеров и сеттеров.
     */
    public static void main(String[] args) {
        // Создаем объект
        Slide10_GetterSetterExample product = new Slide10_GetterSetterExample("Смартфон", 50000, true);
        System.out.println("Информация о продукте после создания:");
        product.displayInfo();

        // Используем геттеры для получения значений
        System.out.println("Получаем название продукта через геттер: " + product.getProductName());
        System.out.println("Получаем цену продукта через геттер: " + product.getProductPrice());

        // Используем сеттеры для изменения значений
        System.out.println("\nМеняем название продукта:");
        product.setProductName("Ноутбук");
        System.out.println("Получаем новое название: " + product.getProductName());

        System.out.println("\nМеняем цену продукта:");
        product.setProductPrice(75000);
        System.out.println("Получаем новую цену: " + product.getProductPrice());

        System.out.println("\nПопытка установить отрицательную цену:");
        product.setProductPrice(-100); // Выведет сообщение об ошибке
        System.out.println("Цена осталась прежней: " + product.getProductPrice());

        // Обновляем информацию
        System.out.println("\nОбновлённая информация о продукте:");
        product.displayInfo();
    }
}