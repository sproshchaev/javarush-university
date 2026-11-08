package com.javarush.example;

public class TraditionalSwitchExample {

    public static void main(String[] args) {
        String[] products = {"Apple", "Peach", "Raspberry", "Banana", "Carrot"};

        for (String product : products) {
            String productType;

            // Традиционный switch statement (до Java 12) c обязательным break
            switch (product) {
                case "Apple":
                    productType = "Fruit";
                    break;
                case "Peach":
                    productType = "Fruit";
                    break;
                case "Raspberry":
                    productType = "Berry";
                    break;
                // case null:   // так можно с Java 21, на Java 17 null проверяем до switch
                //     productType = "null product";
                //     break;
                default:
                    productType = "Other";
                    break;
            }
            System.out.println(product + " -> " + productType);
        }
    }
}
