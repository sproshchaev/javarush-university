package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Демонстрация наследования и полиморфизма:
 * 1. Наследование (extends)
 * 2. Переопределение методов (@Override)
 * 3. Приведение типов (upcast/downcast)
 * 4. Динамическая диспетчеризация
 * 5. Использование super
 */
public class Slide06_PolymorphismDemo {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация наследования и полиморфизма ===\n");

        // 1. Наследование - создание объектов разных классов
        Employee emp1 = new Developer("Анна", "Java");
        Employee emp2 = new Manager("Дмитрий", "Разработка");
        Employee emp3 = new Developer("Петр", "Python");

        // 2. Полиморфизм - работа с разными типами через общий интерфейс Employee
        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);

        System.out.println("--- Работа всех сотрудников (полиморфизм) ---");
        for (Employee employee : employees) {
            employee.work(); // Динамическая диспетчеризация!
            employee.takeBreak();
            System.out.println("Зарплата: " + employee.calculateSalary() + "\n");
        }

        // 3. Приведение типов (type casting)
        System.out.println("--- Приведение типов ---");

        // Upcast (автоматически)
        Employee employee = new Developer("Мария", "JavaScript");

        // Downcast с проверкой instanceof
        if (employee instanceof Developer) {
            Developer dev = (Developer) employee; // Явное приведение
            dev.writeCode();
            System.out.println("Специализация: " + dev.getSpecialization());
        }

        // 4. Вызов родительского метода через super (демонстрация)
        System.out.println("\n--- Демонстрация super ---");
        Developer dev = new Developer("Алексей", "C++");
        dev.showInfo(); // Вызовет метод, который использует super

        // 5. Работа с Object (вершина иерархии)
        System.out.println("\n--- Все объекты наследуются от Object ---");
        Object obj1 = emp1;
        Object obj2 = "Строка";
        Object obj3 = 42;

        System.out.println("Тип obj1: " + obj1.getClass().getName());
        System.out.println("Тип obj2: " + obj2.getClass().getName());
        System.out.println("Тип obj3: " + obj3.getClass().getName());

        // 6. Полиморфизм в действии - единая обработка разных типов
        System.out.println("\n--- Полиморфная обработка ---");
        processEmployee(emp1);
        processEmployee(emp2);

        // 7. Невозможность вызова специфичных методов без приведения
        // employee.writeCode(); // Ошибка компиляции!
    }

    // Метод, демонстрирующий полиморфизм
    static void processEmployee(Employee emp) {
        System.out.println("Обработка: " + emp.getName());
        emp.work();

        // Проверка типа и вызов специфичного метода
        if (emp instanceof Manager) {
            Manager manager = (Manager) emp;
            manager.conductMeeting();
        }
    }
}

/**
 * Базовый класс - Сотрудник
 */
class Employee {
    private String name;
    private int experienceYears;

    public Employee(String name) {
        this.name = name;
        this.experienceYears = 1;
    }

    public String getName() {
        return name;
    }

    public void work() {
        System.out.println(name + ": Выполняет общие задачи");
    }

    public void takeBreak() {
        System.out.println(name + ": На перерыве");
    }

    // Метод для переопределения в наследниках
    public double calculateSalary() {
        return 50000 + (experienceYears * 5000);
    }

    public void showInfo() {
        System.out.println("Сотрудник: " + name);
    }

    public void setExperienceYears(int years) {
        if (years > 0) {
            this.experienceYears = years;
        }
    }
}

/**
 * Класс-наследник 1: Разработчик
 */
class Developer extends Employee {
    private String specialization;

    public Developer(String name, String specialization) {
        super(name); // Вызов конструктора родителя
        this.specialization = specialization;
    }

    // 1. Переопределение метода (method overriding)
    @Override
    public void work() {
        System.out.println(getName() + ": Пишет код на " + specialization);
    }

    // 2. Переопределение с использованием super
    @Override
    public double calculateSalary() {
        double baseSalary = super.calculateSalary(); // Вызов родительского метода
        return baseSalary * 1.2; // Разработчики получают на 20% больше
    }

    // 3. Специфичный метод только для Developer
    public void writeCode() {
        System.out.println(getName() + ": Компилирует код... Готово!");
    }

    public String getSpecialization() {
        return specialization;
    }

    // 4. Дополнение родительского метода через super
    @Override
    public void showInfo() {
        super.showInfo(); // Вызов метода родителя
        System.out.println("Должность: Разработчик");
        System.out.println("Специализация: " + specialization);
    }
}

/**
 * Класс-наследник 2: Менеджер
 */
class Manager extends Employee {
    private String department;

    public Manager(String name, String department) {
        super(name);
        this.department = department;
    }

    @Override
    public void work() {
        System.out.println(getName() + ": Управляет отделом " + department);
    }

    @Override
    public double calculateSalary() {
        double baseSalary = super.calculateSalary();
        return baseSalary * 1.5; // Менеджеры получают на 50% больше
    }

    // Специфичный метод только для Manager
    public void conductMeeting() {
        System.out.println(getName() + ": Проводит совещание в отделе " + department);
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Должность: Менеджер");
        System.out.println("Отдел: " + department);
    }
}