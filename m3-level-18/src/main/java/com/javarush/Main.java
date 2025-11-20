package com.javarush;

import java.util.ArrayList;
import java.util.List;

/**
 *  JavaRush-University
 */
public class Main {
    public static void main(String[] args) {

        // Примивы храняться в стеке
        int number = 10; // стек
        String name = "Jav"; // куча

        List<String> list = new ArrayList<>(); // куча

        // массив примитивов - в стеке? или в куче?
        int[] arrayInt = new int[number]; // куча

        // Три фундаментальных правила JMM (Java Memory Model):
        // 1) Правило 1: Однопоточная последовательность - внутри одного окна операции выполняются в логическом порядке
        int a = 1;
        int b = 2;
        int result = a + b; // всегда 3 независимо от оптимизаций процессора

        // 2) Нет "воздушных значений" - переменная не может получить случайное значение
        int x = 0; // Всегда 0, 1, 2 ... / "не мусорное значение", то что записали - то и получили

    }

    // 3) Happens-before порядок
    class Exaple {
        volatile boolean flag = false;
        int data = 0;

        void writer() {
            data = 42;       // (1)
            flag = true;     // (2) happens-before любого чтения flag
        }

        void reader() {
            if (flag) {      // (3) если видит true, то гарантированно видит и data = 42
                System.out.println(data);
            }
        }
    }

    // Правило happens-before гарантии
    class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++; // захват монитора создает happends-before
                     // Освобождение монитора - возможность след захвата
        }            // соблюдение атомарности (чтение / увеличение / запись)

        public synchronized int getCount() {
            return count; // Гарантия видимости последнего значения count
        }
    }

}
