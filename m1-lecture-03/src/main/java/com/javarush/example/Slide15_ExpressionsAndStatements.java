package com.javarush.example;

/**
 * Слайд 15. Выражения и команды.
 */
public class Slide15_ExpressionsAndStatements {
    public static void main(String[] args) {

        int x;                       // команда: результата нет

        int a = 3;
        boolean result = (a < 10);   // выражение, тип boolean
        System.out.println("(a < 10) -> " + result);

        int i = 5;
        int old = i++;               // выражение, тип совпадает с типом i
        System.out.println("i++ вернуло " + old + ", теперь i = " + i);

        int y = (x = 5);             // выражение, тип совпадает с типом x
        System.out.println("(x = 5) вернуло " + y);
    }
}
