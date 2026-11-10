package com.javarush.live.figure.impl;

public class ImplicitImplementationDemo {

    public static void main(String[] args) {
        Pet pet = new Puppy();
        System.out.println("Щенок говорит: " + pet.sound());
    }

}
