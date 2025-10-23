package com.javarush;

/**
 * Статические методы
 */
public class StaticMethod {

    // Статический метод - привязан к классу (не к объекту)
    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        // Вызов статического метода через Имя класса
        int result = add(1, 2);
        System.out.println("Результат = " + result);
    }

}
