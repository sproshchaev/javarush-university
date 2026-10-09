package com.javarush.demo;

public class StackTraceElementDemo {

    public static void main(String[] args) {
        printCaller();
    }

    private static void printCaller() {
        StackTraceElement[] methods = Thread.currentThread().getStackTrace();

        for (int i = 0; i < methods.length; i++) {
            StackTraceElement element = methods[i];
            System.out.println("Элемент: ");
            System.out.println(" класс: " + element.getClassName());
            System.out.println(" метод: " + element.getMethodName());
            System.out.println(" файл: " + element.getFileName());
            System.out.println(" строка: " + element.getLineNumber());
            System.out.println(" модуль: " + element.getModuleName());
            System.out.println(" версия: " + element.getModuleVersion());
        }
    }

}
