package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Слайды 3-6: Демонстрация памяти JVM - стек потока vs куча
 */
public class Slide03_06_StackVsHeap {

    // Поле класса - хранится в куче
    private static String classField = "Общее поле класса";

    public static void main(String[] args) {
        System.out.println("=== ПАМЯТЬ JVM: СТЕК VS КУЧА (Слайды 3-6) ===\n");

        demonstrateStackVsHeap();
        demonstrateMethodCalls();
        demonstrateSharedObject();
    }

    /**
     * Слайды 3,5: Демонстрация разницы между стеком и кучей
     */
    private static void demonstrateStackVsHeap() {
        System.out.println("1. РАЗМЕЩЕНИЕ ДАННЫХ В ПАМЯТИ:");

        // Локальные переменные - в стеке
        int primitiveInStack = 42;                    // Примитив в стеке
        String referenceInStack = "Hello";           // Ссылка в стеке

        // Объекты - в куче
        List<String> listInHeap = new ArrayList<>(); // Объект List в куче
        listInHeap.add("Элемент списка");            // Объект String в куче

        Integer wrapperInHeap = Integer.valueOf(100); // Обертка в куче

        System.out.println("   Примитив в стеке: " + primitiveInStack);
        System.out.println("   Ссылка в стеке: " + referenceInStack);
        System.out.println("   Объект List в куче: " + listInHeap);
        System.out.println("   Обертка Integer в куче: " + wrapperInHeap);
        System.out.println("   Поле класса в куче: " + classField);
    }

    /**
     * Слайд 4: Демонстрация стека вызовов методов
     */
    private static void demonstrateMethodCalls() {
        System.out.println("\n2. СТЕК ВЫЗОВОВ МЕТОДОВ:");

        System.out.println("   main() -> methodA()");
        methodA();
        System.out.println("   main() завершен");
    }

    private static void methodA() {
        int localVarA = 10;  // Локальная переменная в стеке methodA
        System.out.println("   methodA() -> methodB(), localVarA = " + localVarA);
        methodB();
    }

    private static void methodB() {
        String localVarB = "Локальная строка";  // Локальная переменная в стеке methodB
        System.out.println("   methodB(), localVarB = " + localVarB);
    }

    /**
     * Слайд 6: Демонстрация общего объекта для потоков
     */
    private static void demonstrateSharedObject() {
        System.out.println("\n3. ОБЩИЕ ОБЪЕКТЫ В КУЧЕ:");

        SharedData sharedData = new SharedData();  // Один объект в куче

        // Создаем несколько ссылок на один объект
        SharedData reference1 = sharedData;
        SharedData reference2 = sharedData;

        System.out.println("   Создан один объект SharedData в куче");
        System.out.println("   reference1 == reference2: " + (reference1 == reference2));
        System.out.println("   Обе ссылки указывают на один объект: " +
                reference1.getData() + " | " + reference2.getData());

        // Изменяем объект через одну ссылку
        reference1.setData("Новые данные");
        System.out.println("   После изменения через reference1:");
        System.out.println("   reference1: " + reference1.getData());
        System.out.println("   reference2: " + reference2.getData() + " (видит изменения!)");
    }

    /**
     * Класс для демонстрации общего объекта
     */
    static class SharedData {
        private String data = "Исходные данные";  // Поле объекта - в куче

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}