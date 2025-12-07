package com.javarush.example;

/**
 * Демонстрация инкапсуляции:
 * 1. Сокрытие данных (private поля)
 * 2. Контроль доступа через геттеры/сеттеры
 * 3. Валидация внутреннего состояния
 * 4. Сокрытие реализации
 */
public class Slide05_EncapsulationDemo {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация инкапсуляции ===\n");

        // 1. Создание банковского счета с контролем начального баланса
        BankAccount account = new BankAccount("Иван Иванов", "ACC001", 1000.0);
        System.out.println("Создан счет: " + account.getAccountInfo());

        // 2. Попытка некорректных операций (будут отклонены)
        System.out.println("\n--- Попытка некорректных операций ---");
        account.deposit(-500);      // Отрицательное пополнение
        account.withdraw(2000);     // Снятие больше баланса
        account.withdraw(-100);     // Отрицательное снятие

        // 3. Корректные операции
        System.out.println("\n--- Корректные операции ---");
        account.deposit(500);
        account.withdraw(200);
        account.applyInterest();    // Внутренняя логика скрыта

        // 4. Попытка прямого доступа к полям (невозможна)
        // account.balance = 1000000; // Ошибка компиляции!
        // account.accountNumber = "HACKED"; // Ошибка компиляции!

        System.out.println("\n--- Итоговое состояние ---");
        System.out.println(account.getAccountInfo());
        System.out.println("Баланс через геттер: " + account.getBalance());

        // 5. Демонстрация иммутабельности части данных
        System.out.println("\n--- Иммутабельные данные ---");
        System.out.println("Номер счета (неизменяемый): " + account.getAccountNumber());
        // account.setAccountNumber("NEW123"); // Метода не существует!
    }
}

/**
 * Класс Банковский счет - пример полной инкапсуляции
 */
class BankAccount {
    // 1. Приватные поля - данные полностью скрыты
    private final String accountNumber; // final - неизменяемый после создания
    private String ownerName;
    private double balance;
    private double interestRate = 0.05; // Внутренняя реализация скрыта

    // 2. Конструктор с валидацией
    public BankAccount(String ownerName, String accountNumber, double initialBalance) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя владельца не может быть пустым");
        }
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Номер счета не может быть пустым");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }

        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // 3. Геттеры - контролируемый доступ на чтение
    public String getOwnerName() {
        return ownerName;
    }

    public String getAccountNumber() {
        return accountNumber; // Только чтение - нет сеттера
    }

    public double getBalance() {
        return balance;
    }

    // 4. Сеттер с валидацией
    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            System.out.println("Ошибка: имя владельца не может быть пустым");
            return;
        }
        this.ownerName = ownerName;
    }

    // 5. Бизнес-методы с полным контролем логики
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: сумма пополнения должна быть положительной");
            return;
        }
        balance += amount;
        System.out.printf("Успешно пополнено: +%.2f. Новый баланс: %.2f%n", amount, balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: сумма снятия должна быть положительной");
            return;
        }
        if (amount > balance) {
            System.out.printf("Ошибка: недостаточно средств. Запрошено: %.2f, доступно: %.2f%n",
                    amount, balance);
            return;
        }
        balance -= amount;
        System.out.printf("Успешно снято: -%.2f. Новый баланс: %.2f%n", amount, balance);
    }

    // 6. Приватный метод - внутренняя реализация скрыта
    private double calculateInterest() {
        return balance * interestRate;
    }

    // 7. Публичный метод, использующий приватную реализацию
    public void applyInterest() {
        double interest = calculateInterest(); // Скрытая логика расчета
        balance += interest;
        System.out.printf("Начислены проценты: +%.2f. Новый баланс: %.2f%n",
                interest, balance);
    }

    // 8. Можно менять внутреннюю реализацию без влияния на внешний код
    public void setInterestRate(double rate) {
        if (rate < 0 || rate > 1) {
            System.out.println("Ошибка: процентная ставка должна быть от 0 до 1");
            return;
        }
        this.interestRate = rate;
        System.out.println("Процентная ставка изменена на: " + (rate * 100) + "%");
    }

    // 9. Метод для получения полной информации
    public String getAccountInfo() {
        return String.format("Счет: %s, Владелец: %s, Баланс: %.2f",
                accountNumber, ownerName, balance);
    }
}