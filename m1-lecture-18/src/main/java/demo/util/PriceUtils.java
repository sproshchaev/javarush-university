package demo.util;

/**
 * Утилитарный класс
 */
public class PriceUtils {

    // Константы (psf)
    public static final int VAT_PERCENT = 20;
    private static final int FRE_DELIVERY_FROM = 1000;

    // Приватный конструктор
    private PriceUtils() {

    }

    public static int withVat(int price) {
        return price + price * VAT_PERCENT / 100;
    }

    public static boolean isFreeDelivery(int orderTotal) {
        return orderTotal >= FRE_DELIVERY_FROM;
    }

}
