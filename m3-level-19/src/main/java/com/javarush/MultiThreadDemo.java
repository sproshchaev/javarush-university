package com.javarush;

public class MultiThreadDemo {

    static int count = 0; // Общий ресурс

    public static void main(String[] args) throws InterruptedException {

        // Поток 1: +1000
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        });

        // Поток 2: -1000
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count--;
            }
        });

        t1.start();
        t2.start();

        //
        t1.join();
        t2.join();

        System.out.println("Итого: " + count);

    }

}
