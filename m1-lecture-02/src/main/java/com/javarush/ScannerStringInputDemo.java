package com.javarush;

import java.util.Scanner;

public class ScannerStringInputDemo {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        System.out.println("Как вас зовут?");
        String name = console.nextLine();
        // console.nextInt();

        System.out.println("Привет, " + name + "!");

    }

}
