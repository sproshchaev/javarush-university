package com.javarush.demo;

/**
 * Это javadoc и здесь можно кратко писать зачем этот класс
 * был создан
 */
public class StaticContextErrorDemo {

    String greeting = "Привет"; // обычное поле
    static int visits = 0; // статическое поле

    /**
     * Обычный метод видит статику
     */
    void sayHello() {
        System.out.println("Привет!");
        visits++;
    }

    public static void main(String[] args) {

        // System.out.println(greeting); // ошибка компиляции
        // sayHello(); // ошибка компиляции

        StaticContextErrorDemo demo = new StaticContextErrorDemo();
        System.out.println(demo.greeting); // через объект
        demo.sayHello();
        demo.sayHello();
        demo.sayHello();
        demo.sayHello();
        demo.sayHello();

        System.out.println("visits: " + visits);

    }

}
