package com.javarush.demo;

public class SharedStaticFieldDemo {

    public static void main(String[] args) {

        Player anna = new Player("Анна");
        Player boris = new Player("Борис");

        anna.setScore(100); // обычное поле только у Анны
        anna.levelUp(); // меняем статическое поле через объект Анна
        anna.printInfo();
        boris.printInfo();

        System.out.println("Player.level: " + Player.getLevel());
    }

}
