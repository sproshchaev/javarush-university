package com.javarush.demo;

import com.javarush.demo.exception.InvalidAgeException;

public class UncheckedCustomExceptionDemo {

    public static void main(String[] args) {
        int[] ages = {25, -3, 140, 40};
        for (int age : ages) {
            try {
                register(age);
            } catch (InvalidAgeException e) {
                System.out.println("Не зарегистрировали: " + e.getMessage());
            }
        }
    }

    private static void register(int age) {
        if (age < 0 || age > 120) {
            throw new InvalidAgeException("возраст вне диапазона 0..120");
        }
        System.out.println("Зарегистрировали, возраст: " + age);
    }

}
