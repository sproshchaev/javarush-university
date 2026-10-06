package com.javarush.demo;

public class MultipleCatchDemo {

    public static void main(String[] args) {

        for (int choice = 1; choice <= 3 ; choice++) {

            try {
                runCase(choice);
            } catch (ArithmeticException e) {  // 1
                System.out.println("Деление на ноль: " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) { // 2
                System.out.println("Выход за границы массива: " + e.getMessage());
            } catch (NumberFormatException e) { // 3
                System.out.println("Строка не число: " + e.getMessage());
            } catch (Exception ex) { // 4
                System.out.println("Какое-то общее исключение: " + ex.getMessage());
            }

            System.out.println("Программа завершилась штатно");

        }


    }

    private static void runCase(int choice) {

        switch (choice) {
            case 1:
                int x = 10 / 0;
                break;
            case 2:
                int[] numbers = new int[3];
                numbers[5] = 1;
                break;
            case 3:
                int n = Integer.parseInt("100abc");
                break;
        }

    }

}
