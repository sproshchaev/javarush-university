package com.javarush.demo;

public class WiderAccessDemo {
    public static void main(String[] args) {
        SalesReport report = new SalesReport();
        System.out.println(report.title());
    }
}

abstract class Report {
    protected abstract String title();
}

class SalesReport extends Report {
    // protected в родителе, public в наследнике: видимость расширили
    @Override
    public String title() {
        return "Отчет о продажах за ноябрь";
    }
}

// class SecretReport extends Report {
//     @Override
//     private String title() {
//         return "Секрет";
//     }
// }
