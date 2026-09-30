package demo;

import java.util.Objects;

/**
 * Простой класс POJO
 *  1) поля
 *  2) конструктор (-ы)
 *  3) гт/сет eq/hc toString
 */
public class Product {

    /** Константы */
    public static final int INT = 120;
    public static final int INT1 = 100;

    // Общий счетчик - переменная / класс
    private static int count = 0;

    // Поля
    private final int id; // !
    private String name;
    private int price;

    // Конструкторы
    public Product(String name, int price) {
        count++;
        this.id = count;
        this.name = name;
        this.price = price;
    }

    // Блок логики
    public static int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    // Сеттер с проверкой
    public void setPrice(int price) {
        // Валидация входных параметров: -32 ... 32 тыс
        if (price < 0) {
            System.out.println("Отклонено: цена " + price + " меньше нуля!");
            return;
        }
        this.price = price;
    }

    // Вычисляемый геттер
    public int getPriceWithVat() {
        return price * INT / INT1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
