package com.javarush.demo;

import com.javarush.demo.exception.DemoResource;

public class TryWithResourcesDemo {

    public static void main(String[] args) throws Exception {

        DemoResource file = new DemoResource("report.txt");
        try {
            file.work(false);
        } catch (IllegalStateException e) {
            System.out.println("catch: " + e.getMessage());
        } finally {
            file.close();
        }

        System.out.println("С try with res ---");

        try (DemoResource file2 = new DemoResource("report.txt")) {
            file2.work(false);
        }


    }

}
