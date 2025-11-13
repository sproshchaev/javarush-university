package com.javarush;

public class StackTraceElementDemo {

    // Цепочка вызовов: main -> methodA() -> methodB()
    public static void main(String[] args) {
        methodA();
    }

    static void methodA() {
        System.out.println("run methodA");
        methodB();
    }

    static void methodB() {
        System.out.println("run methodB");
        // Получение текущего stack trace
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        // Обход массива
        System.out.println("Stack Trace:");
             // Экземпляр класса                   // StackTraceElement[]
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            System.out.println(" " + stackTraceElement.getClassName() + "."
                    + stackTraceElement.getMethodName()
                    + "(строка " + stackTraceElement.getLineNumber() + ")");
        }


    }

}
