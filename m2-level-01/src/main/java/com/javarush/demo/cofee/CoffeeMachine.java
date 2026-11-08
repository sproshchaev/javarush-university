package com.javarush.demo.cofee;

public class CoffeeMachine {

    // Кнопка наружу - менять нельзя!
    public void makeCoffee() {
        // Состав приватных методов менять можно!
        heatWater();
        grindBeans();
        brew();
        // + добавить молока

    }

    // Нагрев воды
    private void heatWater() {
        System.out.println("Нагреваем воду до 92 градусов");
    }

    // Сделать помол
    private void grindBeans() {
        System.out.println("Мелем 18 гр зерен");
    }

    // Пролить воду
    private void brew() {
        System.out.println("Проливаем воду через кофе");
    }

    //

}
