package com.javarush.demo;

public class ShadowingDemo {

    // Поля класса
    public int count = 0;
    public int sum = 0;

    public void add(int data) {
        int sum = data * 2;
        this.sum = this.sum + data + sum;
        System.out.print("sum = " + sum + "\n"); // = println
        System.out.println("поле класса sum = " + this.sum);
    }

    public static void main(String[] args) {
        // Создание экземпляра класса shadowingDemo
        ShadowingDemo shadowingDemo = new ShadowingDemo();
        shadowingDemo.add(10);
    }

}
