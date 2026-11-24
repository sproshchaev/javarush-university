package com.javarush.live;

public class ProductSwitchDemo {

    public static void main(String[] args) {

        for (String product : new String[]{"Apple", "Peach", "Raspberry", "Carrot"}) {
            String statementResult = categoryWithStatement(product);
            String expressionResult = categoryWithExpression(product);
            String blockResult = categoryWithBlock(product);

            // Carrot: other / other / other
            System.out.printf("%s: %s / %s / %s%n", product, statementResult, expressionResult, blockResult);
        }

    }

    private static String categoryWithStatement(String product) {
        String productType;
        switch (product) {
            case "Apple":
            case "Peach":
                productType = "Fruit";
                break;
            case "Raspberry":
                productType = "Berry";
                break;
            default:
                productType = "other";
        }
        return productType;
    }

    private static String categoryWithExpression(String product) {
        return switch (product) {
            case "Apple", "Peach" -> "Fruit";
            case "Raspberry" -> "Berry";
            default -> "other";
        };
    }

    public static String categoryWithBlock(String product) {
        String productType = switch (product) {
            case "Apple", "Peach" -> {
                System.out.println("This is fruit");
                yield "Fruit";
            }
            case "Raspberry" -> "Berry";
            default -> "other";
        };
        return productType;
    }

}
