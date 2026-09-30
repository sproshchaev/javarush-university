package demo;

import demo.util.PriceUtils;

public class ProductDemo {

    public static void main(String[] args) {

        Product coffee = new Product("Кофе", 250);
        Product tea = new Product("Чай", 0);

        System.out.println(coffee);
        System.out.println(tea);

        coffee.setPrice(-100);
        System.out.println(coffee);

        System.out.println(tea.getPriceWithVat());

        System.out.println("Создано товаров: " + Product.getCount());

        // ---
        // PriceUtils priceUtils = new PriceUtils();
        System.out.println("НДС: " + PriceUtils.VAT_PERCENT + "%" );
        System.out.println("250 c НДС: " + PriceUtils.withVat(250));

    }

}
