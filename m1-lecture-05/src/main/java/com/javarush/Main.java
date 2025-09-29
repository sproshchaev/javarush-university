package com.javarush;

/**
 * JavaRush-University
 */
public class Main {

    public static void main(String[] args) {

        int n = 0;
        while (n < 4) {
            int m = 0;
            while (m < 5) {
                System.out.print("A");
                m++;
            }
            System.out.println();
            n++;
        }

        System.out.println("");

        // (1,1), (1,2), (1,3), (1,4), (1,5)
        // (2,1), (2,2), (2,3), (2,4), (2,5)
        // ...

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print("(" + i + "," + j + ")");
            }
            System.out.println();
        }
// *
// **
// ***
// ****
// *****

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) { // 1, 1-2, 1-3, 1-4, 1-5
                System.out.print("*");
            }
            System.out.println();
        }

    }


}
