package com.javarush;

import java.util.Date;

public class SimpleCurrentDate {

    public static void main(String[] args) throws InterruptedException {
        Date rightNow = new Date();
        System.out.println("Прямо сейчас: " + rightNow);

        // 1,5 сек
        Thread.sleep(1500);

        Date aBitLater = new Date();
        System.out.println("Через 1,5 сек: " + aBitLater);

    }

}
