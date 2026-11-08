package com.javarush.demo;

public class FilterTask {
    public static void main(String[] args) {
        int[] numbers = {-3, 8, 0, 15, -6, 4, 7};
        System.out.println("Четных: " + count(numbers, new EvenCondition()));
        System.out.println("Положительных: " + count(numbers, new PositiveCondition()));
        System.out.println("Больше 5: " + count(numbers, new GreaterThan(5)));
    }

    // Метод подсчета не знает, какое условие ему передали
    static int count(int[] numbers, Condition condition) {
        int result = 0;
        for (int number : numbers) {
            if (condition.test(number)) {
                result++;
            }
        }
        return result;
    }
}

interface Condition {
    boolean test(int number);
}

class EvenCondition implements Condition {
    @Override
    public boolean test(int number) {
        return number % 2 == 0;
    }
}

class PositiveCondition implements Condition {
    @Override
    public boolean test(int number) {
        return number > 0;
    }
}

class GreaterThan implements Condition {
    private int limit;

    GreaterThan(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean test(int number) {
        return number > limit;
    }
}
