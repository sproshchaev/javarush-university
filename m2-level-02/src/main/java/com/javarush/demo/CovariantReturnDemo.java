package com.javarush.demo;

public class CovariantReturnDemo {
    public static void main(String[] args) {
        AppleBox box = new AppleBox();
        Apple apple = box.take();   // приведение типа не нужно
        System.out.println(apple.name() + ", сорт " + apple.variety());

        FruitBox anyBox = box;
        Fruit fruit = anyBox.take();
        System.out.println("Через родителя: " + fruit.name());
    }
}

class Fruit {
    String name() {
        return "Фрукт";
    }
}

class Apple extends Fruit {
    @Override
    String name() {
        return "Яблоко";
    }

    String variety() {
        return "Антоновка";
    }
}

abstract class FruitBox {
    abstract Fruit take();
}

class AppleBox extends FruitBox {
    // В родителе тип результата Fruit, здесь Apple: тип сузили
    @Override
    Apple take() {
        return new Apple();
    }
}
