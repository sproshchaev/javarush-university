package com.javarush.example;

/**
 * Слайд 5. Пример if-else: выполняется ровно одна ветка.
 */
public class Slide05_IfElseExample {
    public static void main(String[] args) {

        int age = 17;

        if (age < 18) {
            System.out.println("Ты еще ребенок");
            System.out.println("Не спорь со взрослыми");
        } else {
            System.out.println("Вы уже взрослый");
            System.out.println("Ну и молодежь пошла");
        }
    }
}
