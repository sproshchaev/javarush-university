package com.javarush;

/**
 * Затенение
 */
public class Solution2 {

    public int sum = 0; // Поле класса

    // Доступ к затененному полю - через this
    // this - только внутри методов класса
    public void add(int data) {
        int sum = data * 2; // Локальная переменная - затеняет поле sum

        System.out.println(sum); // Локальная переменная
        System.out.println(this.sum); // Поле класса

    }

    // this - нельзя использовать вне метода класса

}
