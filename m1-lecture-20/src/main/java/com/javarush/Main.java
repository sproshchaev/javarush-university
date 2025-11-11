package com.javarush;

/**
 *  JavaRush-University
 */
public class Main {
    public static void main(String[] args) {

        int a = 10;
        int b = 0;

        // NumberFormatException
        String input = "150";
        String input2 = "abc";

        try {
            // Блок защищенных операторов

            System.out.println(a / b); // ArithmeticException: / by zero

            int number = Integer.parseInt(input2); // NumberFormatException
            number = Integer.parseInt(input2);
            System.out.println("number" + number);

        //} catch (ArithmeticException e ) {
        //    System.out.println("Произошла ошибка деления на ноль!");
        //} catch (NumberFormatException e) {
        //    System.out.println("Произошла ошибка преобразования формата!");
        //}

        // Или множественный перехват
        } catch (ArithmeticException | NumberFormatException e) {
           System.out.println("Произошла ошибка деления на ноль либо Произошла ошибка преобразования формата");
           System.out.println("!");
         }
    }

}
