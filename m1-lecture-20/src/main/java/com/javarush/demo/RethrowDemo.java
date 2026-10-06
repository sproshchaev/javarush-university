package com.javarush.demo;

public class RethrowDemo {

    public static void main(String[] args) {

        try {
            process();
        } catch (ArithmeticException ex) {
            System.out.println("main: поймали повторно " + ex.getMessage());
        } finally {
            System.out.println("Он обрабатывается всегда!");
        }
        System.out.println("main: работаем дальше");

    }


    private static void process() {
        try {
            int x = 10 / 0;
        } catch (ArithmeticException ex) {
            System.out.println("process: процесс перехватили, записали в журнал");
            throw ex; // new RuntimeException(); // new ArithmeticException(ex.getMessage());
        } catch (RuntimeException ex) {
            System.out.println("process: сюда не попадаем");
        }
        System.out.println("process: эта строка не выполнится");
    }
}
