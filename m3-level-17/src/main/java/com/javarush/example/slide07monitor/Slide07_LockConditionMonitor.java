package com.javarush.example.slide07monitor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

// Монитор с использованием Lock и Condition (более гибкая альтернатива synchronized)
class BankAccount {
    private int balance = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition sufficientFunds = lock.newCondition();
    private final Condition depositMade = lock.newCondition();

    public void withdraw(int amount, String client) throws InterruptedException {
        lock.lock();
        try {
            // Ждем, пока не будет достаточно средств
            while (balance < amount) {
                System.out.println(client + " ждет - недостаточно средств. Нужно: " +
                        amount + ", есть: " + balance);
                sufficientFunds.await(); // Аналог wait()
            }

            balance -= amount;
            System.out.println(client + " снял: " + amount + ". Остаток: " + balance);

            // Уведомляем другие потоки, что баланс изменился
            depositMade.signalAll();

        } finally {
            lock.unlock();
        }
    }

    public void deposit(int amount, String client) throws InterruptedException {
        lock.lock();
        try {
            balance += amount;
            System.out.println(client + " положил: " + amount + ". Баланс: " + balance);

            // Уведомляем ожидающих снятия, что появились средства
            sufficientFunds.signalAll();

        } finally {
            lock.unlock();
        }
    }

    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}

// Демонстрация монитора с Lock/Condition
public class Slide07_LockConditionMonitor {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        System.out.println("=== Демонстрация Монитора (Lock + Condition) ===");
        System.out.println("Банковский счет с условными переменными\n");

        // Клиенты, которые хотят снять деньги
        Thread client1 = new Thread(() -> {
            try {
                account.withdraw(500, "Клиент-1");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread client2 = new Thread(() -> {
            try {
                Thread.sleep(200); // Немного ждем
                account.withdraw(300, "Клиент-2");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Банкомат, который пополняет счет
        Thread atm = new Thread(() -> {
            try {
                Thread.sleep(1000); // Имитация задержки
                account.deposit(400, "Банкомат");

                Thread.sleep(500);
                account.deposit(600, "Банкомат");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        client1.start();
        client2.start();
        atm.start();

        try {
            client1.join();
            client2.join();
            atm.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nФинальный баланс: " + account.getBalance());
    }
}