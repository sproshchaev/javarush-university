package com.javarush.example;

/**
 * Слайд 9. Последовательность if-ов: вложенные условия.
 */
public class Slide09_NestedIf {
    public static void main(String[] args) {

        int temperature = 9;

        if (temperature > 20)
            System.out.println("надеть рубашку");
        else // тут температура меньше (или равна) 20
        {
            if (temperature > 10)
                System.out.println("надеть свитер");
            else // тут температура меньше (или равна) 10
            {
                if (temperature > 0)
                    System.out.println("надеть плащ");
                else // тут температура меньше 0
                    System.out.println("надеть пальто");
            }
        }
    }
}
