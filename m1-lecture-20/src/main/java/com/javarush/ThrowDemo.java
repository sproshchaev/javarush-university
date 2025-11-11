package com.javarush;

public class ThrowDemo {

    public static void main(String[] args) {
        try {
            checkAge(150);
        } catch (IllegalArgumentException e) {
            System.out.println("Произошла передача недопустимого аргумента!");
        } finally {
            // Закрытие ресурсов
            System.out.println("Выполнение блока finally...");
        }


    }

    // Проверка возраста
    static void checkAge(int age) {
        if (age > 100) {
            // throw позволяет выбрасывать исключения в приложении
            throw new IllegalArgumentException("Возраст не может быть более 100 лет!"); // Аргумент принимает не разрешенное значение
        }
        System.out.println("Возраст прошел проверку!");
    }


}


