package com.javarush.demo;

public class QuickInitMonthsDemo {

    public static void main(String[] args) {

        int[][] months = { {31, 28, 31}, {30, 31, 30}, {31, 31, 30}, {31, 30, 31}  };

        System.out.println("Кварталы: " + months.length);
        System.out.println("Месяцев в квартале: " + months[0].length);
        System.out.println("Февраль: " + months[0][1] + " дней");

        int year = 0;
        for (int i = 0; i < months.length; i++) {
            int quarter = 0;
            for (int j = 0; j < months[i].length; j++) {
                quarter = quarter + months[i][j];
                // quarter += months[i][j]; (сокращенный вариант)
            }
            System.out.println("Квартал " + (i + 1) + ": " + quarter + " дней");
            year = year + quarter;
            // year += quarter;(сокращенный вариант)
        }
        System.out.println("Всего в году: " + year + " дней");

    }

}
