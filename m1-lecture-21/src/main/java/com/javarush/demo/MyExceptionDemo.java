package com.javarush.demo;

import com.javarush.demo.exception.MyException;

public class MyExceptionDemo {

    public static void main(String[] args) {

        MyException myException = new MyException();

        try {

            int a = 5 / 0; // throw ArithmeticException

            boolean throwMException = false;

            if (throwMException) {
                throw myException; // генерация MyException
            }

        } catch (MyException e) {
            System.out.println("Возникло MyException");
        } catch (ArithmeticException e) {
            System.out.println("Возникло ArithmeticException");
        }

    }

}
