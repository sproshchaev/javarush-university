package com.javarush;

public class CoffeeShop {

    public static void main(String[] args) {

        String drink = "coffee";
        double priceItem = 100.00 + 100.00;

        // -2 млрд...2 млрд. 32 бита
        int count = 2;

        // count = count + 1;
        count++;

        // count = count - 1;
        count--;


        String strInBill1 = "Кофейня ".toUpperCase(); // (2) КОФЕЙНЯ

        int lenStr = strInBill1.length(); // (1) получить длину строки
        System.out.println("Длина строки " + strInBill1 + "=" + lenStr); // Длина строки Кофейня =8

        String strInBill2 = "Амиго".toLowerCase(); // (3) амиго
        String strInBill3 = "Чек №1";
        String strInBill4 = "-------";
        String strInBill5 = "Напиток: " + drink;
        String totalStr = "Цена: " + priceItem * count + " руб.";

         System.out.print(strInBill1);
         System.out.println(strInBill2);
         System.out.println(strInBill3);
         System.out.println(strInBill4);
         System.out.println(strInBill5);
         System.out.println(totalStr);


        String priceFromTerminal = "1850"; // строка
        int priceAsNumber = Integer.parseInt(priceFromTerminal); // "1850" -> 1850
        System.out.println(priceAsNumber); // как тип int

        String string = String.valueOf(100); // 100 -> "100"
        System.out.println(string); // как тип String

        // Однострочный коммент

        /*
        * Многострочный комментарий
        * Многострочный комментарий
        * Многострочный комментарий
        * */

    }

}


