package com.javarush.overloading;

public class CalcDemo {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // 1) разное кол-во параметров
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.add(1, 2, 3));

        // 2) Разные типы параметров
        System.out.println(calculator.add(1.5, 2.5));

        // 3) Разный порядок типов
        System.out.println(calculator.add("Строка", 10));
        System.out.println(calculator.add(10, "Строка"));

        // Неявное расширение типов
        byte b = 5;
        calculator.process(b); // byte -> int (расширение)
        calculator.process(10); // int -> int (совпадение)
        // calculator.process(10.5); // double -> int сужение не допускается

        // Выбор наиболее конкретного метода
        calculator.print("Hello!"); // вызовет print(String) - точнее чем Object
        calculator.print(123);      // вызовет print(Object) - наиболее подходящий



    }

}
