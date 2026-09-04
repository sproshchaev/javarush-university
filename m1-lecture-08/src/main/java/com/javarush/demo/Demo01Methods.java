package com.javarush.demo;

public class Demo01Methods {

    // Точка входа - public static void main
    public static void main(String[] args) {

        //print();
        //print();
        //print();

        for (int i = 0; i < 3; i++) {
            // Main.print();
            print(); // Hello, Java!
        }


    }

    // Метод который можно вызывать в любом месте программы!
    public static void print() {
        System.out.println("------------");
        System.out.println("Hello, Java!");
        System.out.println("------------");
    }

}
