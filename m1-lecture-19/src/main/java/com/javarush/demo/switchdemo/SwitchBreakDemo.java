package com.javarush.demo.switchdemo;

public class SwitchBreakDemo {

    public static void main(String[] args) {

        int number = 1;

        switch (number) {
            case 1:
                System.out.println("код 1");
                break;
            case 2:
                System.out.println("код 2");
                break;
            case 3:
                System.out.println("код 3");
                break;
            default:
                System.out.println("код не выполняется");
        }

    }

}
