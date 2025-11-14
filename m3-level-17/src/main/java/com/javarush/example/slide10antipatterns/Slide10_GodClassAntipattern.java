package com.javarush.example.slide10antipatterns;

import java.util.*;
import java.time.LocalDateTime;

// Детальный разбор антипаттерна "Класс бога"
public class Slide10_GodClassAntipattern {

    // ========== ПЛОХОЙ ПРИМЕР: КЛАСС-БОГ ==========

    /**
     * АНТИПАТТЕРН: Класс, который делает всё
     * Нарушает принцип единственной ответственности (SRP)
     * Содержит 500+ строк кода, делает всё: от валидации до отправки email
     */
    static class OrderManagerGodClass {
        // Слишком много полей с разной ответственностью
        private List<Order> orders = new ArrayList<>();
        private List<Customer> customers = new ArrayList<>();
        private Map<Integer, Double> taxRates = new HashMap<>();
        private EmailConfig emailConfig = new EmailConfig();
        private DatabaseConfig dbConfig = new DatabaseConfig();
        private ReportConfig reportConfig = new ReportConfig();

        // Сотни строк кода...

        // Методы валидации
        public boolean validateOrder(Order order) {
            // Сложная логика валидации (50+ строк)
            if (order == null) return false;
            if (order.getItems() == null || order.getItems().isEmpty()) return false;
            if (order.getCustomerId() <= 0) return false;
            if (order.getTotalAmount() < 0) return false;
            // ... еще 20 проверок
            return true;
        }

        public boolean validateCustomer(Customer customer) {
            // Еще 30+ строк валидации
            if (customer == null) return false;
            if (customer.getEmail() == null || !customer.getEmail().contains("@")) return false;
            if (customer.getAge() < 18) return false;
            // ... еще 15 проверок
            return true;
        }

        // Методы расчета
        public double calculateTax(Order order) {
            // Сложные налоговые расчеты (40+ строк)
            double tax = 0;
            String country = getCustomerCountry(order.getCustomerId());
            double taxRate = taxRates.getOrDefault(country, 0.2);
            // ... сложные вычисления
            return order.getTotalAmount() * taxRate;
        }

        public double calculateShipping(Order order) {
            // Логика расчета доставки (30+ строк)
            double weight = calculateTotalWeight(order);
            String country = getCustomerCountry(order.getCustomerId());
            // ... сложные правила доставки
            return weight * 0.5; // упрощенный расчет
        }

        // Методы работы с базой данных
        public void saveOrder(Order order) {
            // Логика сохранения в базу (40+ строк)
            System.out.println("Сохранение заказа в базу...");
            // ... сложная логика работы с БД
        }

        public void updateOrder(Order order) {
            // Логика обновления (30+ строк)
            System.out.println("Обновление заказа...");
            // ... сложная логика
        }

        public void deleteOrder(int orderId) {
            // Логика удаления (25+ строк)
            System.out.println("Удаление заказа...");
            // ... сложная логика
        }

        // Методы отправки уведомлений
        public void sendOrderConfirmation(Order order) {
            // Логика отправки email (35+ строк)
            String email = getCustomerEmail(order.getCustomerId());
            String subject = "Подтверждение заказа #" + order.getId();
            String body = createEmailBody(order);
            // ... сложная логика отправки
            System.out.println("Отправка email: " + subject);
        }

        public void sendShippingNotification(Order order) {
            // Логика отправки уведомления о доставке (30+ строк)
            String email = getCustomerEmail(order.getCustomerId());
            String subject = "Ваш заказ отправлен";
            String body = createShippingEmailBody(order);
            // ... сложная логика
            System.out.println("Отправка уведомления о доставке");
        }

        // Методы генерации отчетов
        public void generateDailyReport() {
            // Логика генерации ежедневного отчета (60+ строк)
            System.out.println("Генерация ежедневного отчета...");
            // ... сложная логика отчетности
        }

        public void generateMonthlyReport() {
            // Логика генерации месячного отчета (70+ строк)
            System.out.println("Генерация месячного отчета...");
            // ... очень сложная логика
        }

        // Вспомогательные методы (еще 20+ методов)
        private String getCustomerCountry(int customerId) {
            // Логика получения страны
            return "US";
        }

        private String getCustomerEmail(int customerId) {
            // Логика получения email
            return "customer@example.com";
        }

        private double calculateTotalWeight(Order order) {
            // Логика расчета веса
            return 2.5;
        }

        private String createEmailBody(Order order) {
            // Логика создания тела email
            return "Thank you for your order!";
        }

        private String createShippingEmailBody(Order order) {
            // Логика создания тела email о доставке
            return "Your order has been shipped!";
        }

        // ... и еще много других методов
    }

    // ========== ХОРОШИЙ ПРИМЕР: РАЗДЕЛЕНИЕ ОТВЕТСТВЕННОСТИ ==========

    // Каждый класс отвечает за одну конкретную задачу

    // Валидация
    static class OrderValidator {
        public boolean validate(Order order) {
            if (order == null) return false;
            if (order.getItems() == null || order.getItems().isEmpty()) return false;
            if (order.getCustomerId() <= 0) return false;
            if (order.getTotalAmount() < 0) return false;
            return true;
        }
    }

    static class CustomerValidator {
        public boolean validate(Customer customer) {
            if (customer == null) return false;
            if (customer.getEmail() == null || !customer.getEmail().contains("@")) return false;
            if (customer.getAge() < 18) return false;
            return true;
        }
    }

    // Расчеты
    static class TaxCalculator {
        private Map<String, Double> taxRates = new HashMap<>();

        public TaxCalculator() {
            taxRates.put("US", 0.1);
            taxRates.put("EU", 0.2);
            taxRates.put("UK", 0.15);
        }

        public double calculate(Order order, String country) {
            double taxRate = taxRates.getOrDefault(country, 0.2);
            return order.getTotalAmount() * taxRate;
        }
    }

    static class ShippingCalculator {
        public double calculate(Order order, String country) {
            double weight = calculateTotalWeight(order);
            // Простые правила доставки
            if (country.equals("US")) return weight * 0.5;
            else if (country.equals("EU")) return weight * 0.8;
            else return weight * 1.0;
        }

        private double calculateTotalWeight(Order order) {
            return 2.5; // упрощенный расчет
        }
    }

    // Работа с базой данных
    static class OrderRepository {
        public void save(Order order) {
            System.out.println("Сохранение заказа в базу: " + order.getId());
        }

        public void update(Order order) {
            System.out.println("Обновление заказа: " + order.getId());
        }

        public void delete(int orderId) {
            System.out.println("Удаление заказа: " + orderId);
        }
    }

    static class CustomerRepository {
        public Customer findById(int customerId) {
            return new Customer(customerId, "customer@example.com", 25, "US");
        }
    }

    // Отправка уведомлений
    static class EmailService {
        private EmailConfig config;

        public void sendOrderConfirmation(Order order, Customer customer) {
            String subject = "Подтверждение заказа #" + order.getId();
            String body = createConfirmationEmailBody(order);
            sendEmail(customer.getEmail(), subject, body);
        }

        public void sendShippingNotification(Order order, Customer customer) {
            String subject = "Ваш заказ отправлен";
            String body = createShippingEmailBody(order);
            sendEmail(customer.getEmail(), subject, body);
        }

        private String createConfirmationEmailBody(Order order) {
            return "Thank you for your order #" + order.getId();
        }

        private String createShippingEmailBody(Order order) {
            return "Your order #" + order.getId() + " has been shipped!";
        }

        private void sendEmail(String to, String subject, String body) {
            System.out.println("Отправка email to: " + to + ", subject: " + subject);
        }
    }

    // Генерация отчетов
    static class ReportGenerator {
        public void generateDailyReport(List<Order> orders) {
            System.out.println("Генерация ежедневного отчета для " + orders.size() + " заказов");
        }

        public void generateMonthlyReport(List<Order> orders) {
            System.out.println("Генерация месячного отчета для " + orders.size() + " заказов");
        }
    }

    // Основной класс - координатор
    static class OrderManager {
        private OrderValidator orderValidator = new OrderValidator();
        private CustomerValidator customerValidator = new CustomerValidator();
        private TaxCalculator taxCalculator = new TaxCalculator();
        private ShippingCalculator shippingCalculator = new ShippingCalculator();
        private OrderRepository orderRepository = new OrderRepository();
        private CustomerRepository customerRepository = new CustomerRepository();
        private EmailService emailService = new EmailService();
        private ReportGenerator reportGenerator = new ReportGenerator();

        public void processOrder(Order order) {
            // Валидация
            if (!orderValidator.validate(order)) {
                throw new IllegalArgumentException("Invalid order");
            }

            Customer customer = customerRepository.findById(order.getCustomerId());
            if (!customerValidator.validate(customer)) {
                throw new IllegalArgumentException("Invalid customer");
            }

            // Расчеты
            double tax = taxCalculator.calculate(order, customer.getCountry());
            double shipping = shippingCalculator.calculate(order, customer.getCountry());
            double total = order.getTotalAmount() + tax + shipping;

            // Сохранение
            orderRepository.save(order);

            // Уведомление
            emailService.sendOrderConfirmation(order, customer);
        }

        public void generateReports(List<Order> orders) {
            reportGenerator.generateDailyReport(orders);
            reportGenerator.generateMonthlyReport(orders);
        }
    }

    // ========== ВСПОМОГАТЕЛЬНЫЕ КЛАССЫ ==========

    static class Order {
        private int id;
        private int customerId;
        private double totalAmount;
        private List<String> items;

        public Order(int id, int customerId, double totalAmount, List<String> items) {
            this.id = id;
            this.customerId = customerId;
            this.totalAmount = totalAmount;
            this.items = items;
        }

        // геттеры
        public int getId() { return id; }
        public int getCustomerId() { return customerId; }
        public double getTotalAmount() { return totalAmount; }
        public List<String> getItems() { return items; }
    }

    static class Customer {
        private int id;
        private String email;
        private int age;
        private String country;

        public Customer(int id, String email, int age, String country) {
            this.id = id;
            this.email = email;
            this.age = age;
            this.country = country;
        }

        // геттеры
        public int getId() { return id; }
        public String getEmail() { return email; }
        public int getAge() { return age; }
        public String getCountry() { return country; }
    }

    static class EmailConfig {}
    static class DatabaseConfig {}
    static class ReportConfig {}

    // ========== ДЕМОНСТРАЦИЯ ==========

    public static void main(String[] args) {
        System.out.println("=== Класс бога vs Разделение ответственности ===\n");

        // Создаем тестовые данные
        Order order = new Order(1, 123, 100.0, Arrays.asList("Item1", "Item2"));

        System.out.println("1. Плохой подход - один класс делает всё:");
        OrderManagerGodClass godClass = new OrderManagerGodClass();
        // godClass.processOrder(order); // Слишком много ответственности в одном месте

        System.out.println("\n2. Хороший подход - разделение ответственности:");
        OrderManager goodManager = new OrderManager();
        goodManager.processOrder(order);

        System.out.println("\n=== Преимущества разделения ответственности ===");
        System.out.println("• Легче тестировать (можно тестировать каждый компонент отдельно)");
        System.out.println("• Легче поддерживать (изменения в одной области не затрагивают другие)");
        System.out.println("• Лучшая читаемость (каждый класс имеет четкую цель)");
        System.out.println("• Возможность повторного использования компонентов");
        System.out.println("• Упрощенная командная работа (разные разработчики могут работать над разными компонентами)");
    }
}