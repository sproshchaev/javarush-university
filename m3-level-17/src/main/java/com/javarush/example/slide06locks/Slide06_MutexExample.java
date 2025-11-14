package com.javarush.example.slide06locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Пример использования мьютекса (ReentrantLock) для защиты общего ресурса
class Printer {
    private Lock printerLock = new ReentrantLock();

    public void printDocument(String employee, String document) {
        printerLock.lock(); // Захватываем мьютекс (как будто берем ключ от принтера)
        try {
            System.out.println(employee + " начал печать: " + document);
            // Имитация времени печати
            Thread.sleep(2000);
            System.out.println(employee + " завершил печать: " + document);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            printerLock.unlock(); // Всегда освобождаем мьютекс
        }
    }
}

// Демонстрация работы мьютекса
public class Slide06_MutexExample {
    public static void main(String[] args) {
        Printer sharedPrinter = new Printer();

        // Создаем несколько сотрудников, которые хотят печатать одновременно
        Thread employee1 = new Thread(() -> {
            sharedPrinter.printDocument("Иван", "Отчет за квартал");
        });

        Thread employee2 = new Thread(() -> {
            sharedPrinter.printDocument("Мария", "Презентация для клиента");
        });

        Thread employee3 = new Thread(() -> {
            sharedPrinter.printDocument("Петр", "Договор аренды");
        });

        System.out.println("=== Демонстрация Мьютекса ===");
        System.out.println("Принтер один, но мьютекс гарантирует, что печатает только один сотрудник за раз\n");

        employee1.start();
        employee2.start();
        employee3.start();

        try {
            employee1.join();
            employee2.join();
            employee3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nВсе документы напечатаны!");
    }
}