package com.javarush.example;


/**
 * Слайд 14: Абстракция.
 * Демонстрация принципа абстракции на примере класса Employee.
 */
public class Slide14_Abstraction {

    public static void main(String[] args) {
        System.out.println("=== Слайд 14: Абстракция ===\n");

        // 1. Создание объекта Employee (как в примере слайда)
        System.out.println("1. Создание объекта Employee:");
        Employee employee = new Employee();
        employee.name = "Иван Петров";
        employee.age = 35;
        employee.socialInsuranceNumber = 123456789L;
        employee.taxNumber = 987654321L;

        System.out.printf("Employee employee = new Employee(); -> employee = %s\n", employee);
        System.out.println();

        // 2. Вывод информации об объекте
        System.out.println("2. Вывод основной информации (абстрагированные данные):");
        System.out.printf("Сотрудник: %s, Возраст: %d лет.\n", employee.name, employee.age);
        System.out.printf("Номер соцстрахования: %d\n", employee.socialInsuranceNumber);
        System.out.printf("ИНН: %d\n", employee.taxNumber);
        System.out.println();

        // 3. Демонстрация того, что второстепенные детали отброшены
        System.out.println("3. Второстепенные детали отброшены (абстрагируемся):");
        System.out.println("Класс Employee НЕ содержит полей для:");
        System.out.println(" - Цвета глаз (например, 'голубой')");
        System.out.println(" - Роста (например, 180 см)");
        System.out.println(" - Веса (например, 75 кг)");
        System.out.println(" - Любимого цвета (например, 'зелёный')");
        System.out.println("Эти данные не важны для основной логики работы с карточкой сотрудника.");
        System.out.println();

        // 4. Создание другого объекта Employee
        System.out.println("4. Создание другого объекта Employee:");
        Employee anotherEmployee = new Employee();
        anotherEmployee.name = "Мария Сидорова";
        anotherEmployee.age = 28;
        anotherEmployee.socialInsuranceNumber = 987654321L;
        anotherEmployee.taxNumber = 123456789L;

        System.out.printf("Employee anotherEmployee = new Employee(); -> %s\n", anotherEmployee);
        System.out.println();

        // 5. Понятие абстракции в действии
        System.out.println("5. Абстракция в действии:");
        System.out.println("Мы создали модель сотрудника, содержащую ТОЛЬКО те данные, которые:");
        System.out.println(" - Необходимы для выполнения задачи (управление персоналом).");
        System.out.println(" - Являются значимыми с точки зрения программы.");
        System.out.println("Все остальные детали, хоть и существующие в реальности, были скрыты.");
        System.out.println();

        System.out.println("\n--- Запомни ---");
        System.out.println("Абстракция — это выделение главного и скрытие второстепенного.");
        System.out.println("Класс должен содержать только те поля и методы, которые важны для его основной функции.");
        System.out.println("Это упрощает код, делает его более понятным и устойчивым к изменениям.");
    }

    // Определение класса Employee (как в примере слайда)
    static class Employee {
        String name;                 // Важно: ФИО
        int age;                     // Важно: Дата рождения (возраст)
        long socialInsuranceNumber;  // Важно: Номер соцстрахования
        long taxNumber;              // Важно: ИНН

        // Переопределение toString() для красивого вывода
        @Override
        public String toString() {
            return "Employee{name='" + name + "', age=" + age +
                    ", socialInsuranceNumber=" + socialInsuranceNumber +
                    ", taxNumber=" + taxNumber + "}";
        }
    }
}