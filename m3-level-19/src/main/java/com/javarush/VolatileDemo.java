package com.javarush;

public class VolatileDemo {

    static volatile boolean flag = true;
    // static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

        // Поток-слушатель  - работает когда flag = true
        Thread listener = new Thread(() -> {
            int count = 0;
            while (flag) {
                count++;
            }
            System.out.println("Слушатель завершил работу, count=" + count);

        });

        listener.start();
        Thread.sleep(1000);

        // Главный поток меняет флаг
        System.out.println("Главный поток: flag -> flase");
        flag = false;

        listener.join();
        System.out.println("Программа завершена");

    }


}
