package com.javarush;

public class Solution {

    // Константы (public/private)
    // Поля класса (public (!) / private)
    // Статические методы - в тч main
    // Конструктор
    // Методы экземпляра класса (public/private)

    /**
     * Где объявлена переменная?    Область видимости:
     * В классе (int count)         - Все тело класса
     * В методе (int data)          - Только внутри метода
     * В блоке (int temp)           - Только внутри этого блока { }
     * До объявления                - Использовать нельзя - ошибка!
     */

    public int count = 0; // Поле (состояние) - видно везде
    public static int count2 = 1;

    // С учетом того что метод статический - можно обращаться к статическим полям класса
    public static void main(String[] args) {
        System.out.println("count2=" + count2);
        // System.out.println("data=" + date); // cannot find symbol
        // System.out.println("sum=" + sum); // cannot find symbol
    }

    // параметр метода виден только внутри - и не существует вне метода
    public void add(int data) {

        int sum = data * 2; // локальная переменная - видна только в этом методе
        // int sum = data * 3; // variable sum is already defined in method add(int)

        if (sum > 10) {
            int temp = sum + 5; // еще одна локальная переменная - видна только в if {...}
        }

        count = count + data;
        System.out.println("count=" + count);
    }




}
