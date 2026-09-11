package com.javarush.demo;

abstract class Man {

    String name;
    int age;

    void info() {
        System.out.println("name " + name + ", age " + age);
    }

    // abstract void run();

}
