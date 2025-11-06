package com.javarush.example;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Как избавится от мноэественного if
 */
public class IfDemo {

    // Определим методы для примера
    private static void doSomethingA() { System.out.println("Выполняется действие A"); }
    private static void doSomethingB() { System.out.println("Выполняется действие B"); }
    private static void doSomethingC() { System.out.println("Выполняется действие C"); }
    private static void doDefault() { System.out.println("Выполняется действие по умолчанию"); }

    public static void main(String[] args) {
        String type = "B";

        // 1. switch (Java 14+)
        System.out.println("--- 1. Switch ---");
        switch (type) {
            case "A" -> doSomethingA();
            case "B" -> doSomethingB();
            case "C" -> doSomethingC();
            default -> doDefault();
        }

        // 2. switch (Java 13 и ниже)
        System.out.println("--- 2. Switch (старый стиль) ---");
        switch (type) {
            case "A":
                doSomethingA();
                break;
            case "B":
                doSomethingB();
                break;
            case "C":
                doSomethingC();
                break;
            default:
                doDefault();
        }

        // 3. Полиморфизм (Strategy)
        System.out.println("--- 3. Полиморфизм ---");
        Handler handler = getHandler(type);
        handler.handle();
        // или через Map
        Map<String, Handler> handlerMap = Map.of(
                "A", new HandlerA(),
                "B", new HandlerB(),
                "C", new HandlerC()
        );
        Handler mapHandler = handlerMap.getOrDefault(type, new DefaultHandler());
        mapHandler.handle();

        // 4. Карта с Consumer
        System.out.println("--- 4. Карта с Consumer ---");
        Map<String, Runnable> actionMap = Map.of(
                "A", IfDemo::doSomethingA,
                "B", IfDemo::doSomethingB,
                "C", IfDemo::doSomethingC
        );
        actionMap.getOrDefault(type, IfDemo::doDefault).run();

        // 5. Карта с Function (если возвращаемое значение)
        System.out.println("--- 5. Карта с Function ---");
        // Пример с возвращаемым значением
        Map<String, Supplier<String>> stringActionMap = Map.of(
                "A", () -> "Результат A",
                "B", () -> "Результат B",
                "C", () -> "Результат C"
        );
        String result = stringActionMap.getOrDefault(type, () -> "Результат по умолчанию").get();
        System.out.println("Результат: " + result);
    }

    // Интерфейс и классы для полиморфизма
    interface Handler {
        void handle();
    }

    static class HandlerA implements Handler {
        public void handle() { doSomethingA(); }
    }

    static class HandlerB implements Handler {
        public void handle() { doSomethingB(); }
    }

    static class HandlerC implements Handler {
        public void handle() { doSomethingC(); }
    }

    static class DefaultHandler implements Handler {
        public void handle() { doDefault(); }
    }

    private static Handler getHandler(String type) {
        switch (type) {
            case "A": return new HandlerA();
            case "B": return new HandlerB();
            case "C": return new HandlerC();
            default: return new DefaultHandler();
        }
    }
}