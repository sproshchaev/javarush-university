package com.javarush.example;

/**
 * Демонстрация перегрузки (overloading) и переопределения (overriding) методов.
 * Слайды 8-11: Перегрузка методов, Переопределение методов, Ограничения, Преимущества.
 */
public class Slide08_OverloadOverrideDemo {
    public static void main(String[] args) {
        System.out.println("=== Перегрузка и Переопределение методов ===\n");

        // 1. Демонстрация перегрузки
        System.out.println("--- 1. ПЕРЕГРУЗКА МЕТОДОВ (Overloading) ---");
        Calculator calc = new Calculator();
        System.out.println("Сумма int: " + calc.add(5, 3));
        System.out.println("Сумма double: " + calc.add(5.5, 3.3));
        System.out.println("Сумма трёх int: " + calc.add(5, 3, 2));
        System.out.println("Конкатенация: " + calc.add("Hello", "World"));

        // 2. Демонстрация автоматического расширения типов
        System.out.println("\n--- Автоматическое расширение типов ---");
        calc.processNumber(10);      // int -> int
        calc.processNumber((byte)5); // byte -> int (расширение)
        calc.processNumber(100L);    // long -> long

        // 3. Демонстрация переопределения и полиморфизма
        System.out.println("\n--- 2. ПЕРЕОПРЕДЕЛЕНИЕ МЕТОДОВ (Overriding) ---");

        PaymentSystem[] systems = {
                new CreditCardPayment(),
                new PayPalPayment(),
                new CryptoPayment()  // Новый тип - легко добавили
        };

        // ПОЛИМОРФИЗМ: один интерфейс - разные реализации
        double amount = 100.0;
        for (PaymentSystem system : systems) {
            system.processPayment(amount);
            System.out.println("Комиссия: " + system.calculateFee(amount));
            System.out.println("Статус: " + system.getStatus() + "\n");
        }

        // 4. Демонстрация ограничений переопределения
        System.out.println("--- 3. ОГРАНИЧЕНИЯ ПЕРЕОПРЕДЕЛЕНИЯ ---");
        BaseProcessor base = new AdvancedProcessor();

        // Проверяем, что правила соблюдены:
        System.out.println("Доступ: " + base.getAccessLevel()); // Расширенная видимость
        System.out.println("Данные: " + base.getData());        // Суженный тип возврата
        base.execute("тест");                                   // Та же сигнатура

        // 5. Сравнение перегрузки и переопределения
        System.out.println("\n--- 4. СРАВНЕНИЕ ПЕРЕГРУЗКИ И ПЕРЕОПРЕДЕЛЕНИЯ ---");
        System.out.println("ПЕРЕГРУЗКА:");
        System.out.println("  - В одном классе");
        System.out.println("  - Разные параметры (сигнатура)");
        System.out.println("  - Решается на этапе компиляции");
        System.out.println("  - Возвращаемый тип может быть любым");

        System.out.println("\nПЕРЕОПРЕДЕЛЕНИЕ:");
        System.out.println("  - В родителе и наследнике");
        System.out.println("  - Одинаковые параметры");
        System.out.println("  - Решается во время выполнения (полиморфизм)");
        System.out.println("  - Ограничения: совместимый возвращаемый тип, не сужать доступ");

        // 6. Преимущества полиморфизма на практике
        System.out.println("\n--- 5. ПРЕИМУЩЕСТВА ПОЛИМОРФИЗМА ---");
        OrderProcessor orderProcessor = new OrderProcessor();
        orderProcessor.processOrder(systems[0], 150.0);  // Кредитная карта
        orderProcessor.processOrder(systems[1], 200.0);  // PayPal
    }
}

/**
 * Класс для демонстрации ПЕРЕГРУЗКИ методов.
 * Перегрузка - несколько методов с одним именем, но разными параметрами.
 */
class Calculator {
    // 1. Перегрузка по ТИПАМ параметров
    public int add(int a, int b) {
        System.out.print("add(int, int): ");
        return a + b;
    }

    public double add(double a, double b) {
        System.out.print("add(double, double): ");
        return a + b;
    }

    // 2. Перегрузка по КОЛИЧЕСТВУ параметров
    public int add(int a, int b, int c) {
        System.out.print("add(int, int, int): ");
        return a + b + c;
    }

    // 3. Перегрузка по ТИПУ параметров (String вместо чисел)
    public String add(String a, String b) {
        System.out.print("add(String, String): ");
        return a + " " + b;
    }

    // 4. Перегрузка с автоматическим расширением типов
    public void processNumber(int x) {
        System.out.println("processNumber(int): " + x);
    }

    public void processNumber(long x) {
        System.out.println("processNumber(long): " + x);
    }

    // 5. НЕ перегрузка - возвращаемый тип не входит в сигнатуру!
    // public double add(int a, int b) { // ОШИБКА КОМПИЛЯЦИИ!
    //     return a + b;
    // }
}

/**
 * Базовый класс для демонстрации ПЕРЕОПРЕДЕЛЕНИЯ.
 * Переопределение - замена реализации метода в классе-наследнике.
 */
abstract class PaymentSystem {
    // Абстрактный метод - должен быть переопределен
    public abstract void processPayment(double amount);

    // Неабстрактный метод - может быть переопределен
    public double calculateFee(double amount) {
        return amount * 0.02; // 2% комиссия по умолчанию
    }

    // Метод с реализацией по умолчанию
    public String getStatus() {
        return "Ожидание";
    }

    // final-метод - нельзя переопределить
    public final String getSystemName() {
        return "Платежная система";
    }
}

/**
 * Конкретная реализация - кредитная карта.
 */
class CreditCardPayment extends PaymentSystem {
    // ПЕРЕОПРЕДЕЛЕНИЕ абстрактного метода
    @Override
    public void processPayment(double amount) {
        System.out.println("Обработка платежа по кредитной карте: $" + amount);
        // Логика обработки кредитной карты
    }

    // ПЕРЕОПРЕДЕЛЕНИЕ с изменением поведения
    @Override
    public double calculateFee(double amount) {
        return amount * 0.03; // 3% для кредитных карт
    }

    @Override
    public String getStatus() {
        return "Одобрено банком";
    }

    // Специфичный метод только для кредитных карт
    public boolean validateCard(String cardNumber) {
        return cardNumber != null && cardNumber.length() == 16;
    }
}

/**
 * Другая реализация - PayPal.
 */
class PayPalPayment extends PaymentSystem {
    @Override
    public void processPayment(double amount) {
        System.out.println("Обработка PayPal платежа: $" + amount);
        // Логика PayPal
    }

    @Override
    public double calculateFee(double amount) {
        return amount * 0.01 + 0.3; // $0.30 + 1%
    }

    @Override
    public String getStatus() {
        return "Завершено в PayPal";
    }
}

/**
 * Новая реализация - легко добавляется благодаря полиморфизму.
 */
class CryptoPayment extends PaymentSystem {
    @Override
    public void processPayment(double amount) {
        System.out.println("Обработка криптоплатежа: $" + amount + " (в BTC)");
    }

    @Override
    public String getStatus() {
        return "Подтверждено в блокчейне";
    }

    // Не переопределяем calculateFee - используем реализацию по умолчанию
}

/**
 * Класс для демонстрации ОГРАНИЧЕНИЙ переопределения.
 */
class BaseProcessor {
    // Метод с protected доступом
    protected String getAccessLevel() {
        return "protected";
    }

    // Метод, возвращающий Object
    public Object getData() {
        return "Данные";
    }

    // Метод с параметрами
    public void execute(String command) {
        System.out.println("Выполняем: " + command);
    }
}

class AdvancedProcessor extends BaseProcessor {
    // ✅ ПРАВИЛО: Расширение видимости (protected -> public)
    @Override
    public String getAccessLevel() {
        return "public (расширенный доступ)";
    }

    // ✅ ПРАВИЛО: Сужение типа возврата (Object -> String)
    @Override
    public String getData() {
        return "Строковые данные";
    }

    // ✅ ПРАВИЛО: Та же сигнатура
    @Override
    public void execute(String command) {
        System.out.println("Улучшенное выполнение: " + command.toUpperCase());
    }

    // ❌ НЕЛЬЗЯ (закомментировано):
    // @Override
    // private String getAccessLevel() { return "private"; } // Сужение доступа

    // @Override
    // public Object getData() { return 123; } // Изменение типа возврата
}

/**
 * Класс, демонстрирующий ПРЕИМУЩЕСТВА полиморфизма.
 * Работает с любыми PaymentSystem, не зная конкретных типов.
 */
class OrderProcessor {
    // Метод работает с ЛЮБОЙ платежной системой
    public void processOrder(PaymentSystem paymentSystem, double amount) {
        System.out.println("\n--- Обработка заказа ---");
        System.out.println("Сумма: $" + amount);

        // Не нужно знать конкретный тип!
        paymentSystem.processPayment(amount);

        double fee = paymentSystem.calculateFee(amount);
        double total = amount + fee;

        System.out.println("Комиссия: $" + fee);
        System.out.println("Итого: $" + total);
        System.out.println("Статус: " + paymentSystem.getStatus());

        // Если нужна специфичная логика - проверка типа
        if (paymentSystem instanceof CreditCardPayment) {
            CreditCardPayment card = (CreditCardPayment) paymentSystem;
            System.out.println("Проверка карты: " + card.validateCard("1234567812345678"));
        }
    }
}