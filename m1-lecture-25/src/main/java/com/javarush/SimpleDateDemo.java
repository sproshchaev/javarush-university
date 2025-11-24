package com.javarush;

import java.util.Date;

public class SimpleDateDemo {

    public static void main(String[] args) {
        // Объект Date - сохранит текущие дату и время
        Date date = new Date(); // временной срез

        System.out.println("Текущее время и дата: " + date);

        // Внутри даты - миллисекунды
        System.out.println("Миллисекунды с 01.01.1970: " + date.getTime());

    }

}
