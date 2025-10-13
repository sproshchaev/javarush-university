package com.javarush;

/**
 * JavaRush-University
 */
public class Main {

    // Глобальная константа - исп. во всем проекте (psf)
    public static final int MAX_CONNECTION = 1000;


    public static void main(String[] args) {

        // Синтаксис:
        // final тип имя = значение;
        // (!) если константу переприсвоить - то ошибка компиляции!

        final int MAX_USERS = 100;
        System.out.println("MAX_USERS=" + MAX_USERS + " in " + MAX_CONNECTION);

        // MAX_USERS = 150;  // cannot assign a value to final variable MAX_USERS

        // Пример Math.max, Math.min
        int a = 1;
        int b = 2;
        int max, min = 0;

        max = Math.max(a, b);
        min = Math.min(a, b);

//        if (a < b) {
//            System.out.println("a < b");
//            max = b;
//        } else {
//            System.out.println("b > a");
//            max = a;
//        }

        // Создание экземпляра класса display Дисплей
        Display display = new Display(100, 100);
        System.out.println("Создан display " + display.toString());

        // Как обратиться к статической константе класса
        System.out.println("DISPLAY_NAME=" + Display.DISPLAY_NAME);

        // Как обратиться к константе объекта класса
        System.out.println("DISPLAY_SN=" + display.DISPLAY_SN);

    }

}
