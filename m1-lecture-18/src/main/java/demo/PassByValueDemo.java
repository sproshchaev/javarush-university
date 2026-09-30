package demo;

import java.util.Arrays;

public class PassByValueDemo {

    // Меняем объект по полученной ссылке - изменение видно снаружи
    static void raisePrice(Product product) {
        product.setPrice(product.getPrice() + 50);
    }

    // Присвоить параметру новый объект - снаружи ничего не меняется
    static void replace(Product product) {
        product = new Product("Подмена", 1);
    }

    // Массив
    static void fillWithZero(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
    }

    public static void main(String[] args) {

        Product coffee = new Product("Кофе", 250);
        raisePrice(coffee);
        System.out.println("После raisePrice: " + coffee.getPrice());

        replace(coffee);
        System.out.println("После replace: " + coffee.getName());

        int[] scores = {5, 4, 3};
        fillWithZero(scores);
        System.out.println("После fillWithZero: " + Arrays.toString(scores));

    }


}
