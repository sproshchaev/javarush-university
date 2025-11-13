package com.javarush;

/**
 *  JavaRush-University
 */
public class Main {
    public static void main(String[] args) throws MyException {

        MyException myException = new MyException();

        try {
            int a = 5 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Перехватили стандартное исключение " + e.getMessage());
            throw myException;
        }

    }
}
