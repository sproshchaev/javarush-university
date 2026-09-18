package com.javarush.demo;


public class Player {

    private static int level = 1; // одна ячейка на весь класс
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public void levelUp() {
        level++; // обычный метод может менять статическую переменную
    }

    public void printInfo() {
        System.out.println(name + ": очки = " + score + ", уровень = " + level);
    }

    public static int getLevel() {
        return level;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
