package com.javarush.example;


/**
 * Слайд 15: Инкапсуляция.
 * Демонстрация принципа инкапсуляции на примере класса Employee с приватными полями и публичными геттерами/сеттерами.
 */
public class Slide15_Encapsulation {

    public static void main(String[] args) {
        System.out.println("=== Слайд 15: Инкапсуляция ===\n");

        // 1. Создание объекта Employee (с инкапсуляцией)
        System.out.println("1. Создание объекта Employee:");
        Employee employee = new Employee();

        // 2. Использование сеттеров для установки значений (безопасный доступ)
        System.out.println("2. Установка значений через сеттеры:");
        employee.setName("Иван Петров");
        employee.setAge(35);
        employee.setSocialInsuranceNumber(123456789L);
        employee.setTaxNumber(987654321L);

        System.out.printf("Установлено имя: %s\n", employee.getName());
        System.out.printf("Установлен возраст: %d\n", employee.getAge());
        System.out.printf("Установлен номер соцстрахования: %d\n", employee.getSocialInsuranceNumber());
        System.out.printf("Установлен ИНН: %d\n", employee.getTaxNumber());
        System.out.println();

        // 3. Попытка прямого доступа к приватным полям (не скомпилируется!)
        System.out.println("3. Попытка прямого доступа к приватным полям:");
        System.out.println("employee.name = \"...\"; // ОШИБКА КОМПИЛЯЦИИ! Поле name приватное.");
        System.out.println("employee.age = 30; // ОШИБКА КОМПИЛЯЦИИ! Поле age приватное.");
        System.out.println("Доступ возможен только через публичные методы (геттеры/сеттеры).");
        System.out.println();

        // 4. Демонстрация контроля в сеттерах
        System.out.println("4. Контроль в сеттерах (защита данных):");
        System.out.println("Попытка установить неверный возраст (-5):");
        employee.setAge(-5); // Внутри setAge() будет проверка
        System.out.printf("Возраст после попытки установить -5: %d\n", employee.getAge()); // Останется прежним (35)

        System.out.println("Попытка установить корректный возраст (40):");
        employee.setAge(40);
        System.out.printf("Возраст после установки 40: %d\n", employee.getAge());
        System.out.println();

        // 5. Вывод информации об объекте
        System.out.println("5. Вывод информации через геттеры:");
        System.out.printf("Сотрудник: %s, Возраст: %d лет.\n", employee.getName(), employee.getAge());
        System.out.printf("Номер соцстрахования: %d\n", employee.getSocialInsuranceNumber());
        System.out.printf("ИНН: %d\n", employee.getTaxNumber());
        System.out.println();

        System.out.println("\n--- Запомни ---");
        System.out.println("Инкапсуляция — это \"обёртывание\" данных и методов в класс и скрытие деталей реализации.");
        System.out.println("Поля делаются приватными (private), а доступ к ним предоставляется через публичные методы (геттеры/сеттеры).");
        System.out.println("Это защищает данные от некорректного использования и обеспечивает целостность объекта.");
    }

    // Определение класса Employee с инкапсуляцией
    static class Employee {
        private String name;                 // Приватное поле - скрыто от внешнего мира
        private int age;                     // Приватное поле
        private long socialInsuranceNumber;  // Приватное поле
        private long taxNumber;              // Приватное поле

        // Геттеры (для чтения)
        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public long getSocialInsuranceNumber() {
            return socialInsuranceNumber;
        }

        public long getTaxNumber() {
            return taxNumber;
        }

        // Сеттеры (для записи с контролем)
        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            if (age > 0 && age < 150) { // Простая проверка на корректность
                this.age = age;
            } else {
                System.out.println("Ошибка: Некорректный возраст. Оставлено предыдущее значение.");
                // Можно выбросить исключение или сделать другое действие
            }
        }

        public void setSocialInsuranceNumber(long socialInsuranceNumber) {
            this.socialInsuranceNumber = socialInsuranceNumber;
        }

        public void setTaxNumber(long taxNumber) {
            this.taxNumber = taxNumber;
        }

        // Переопределение toString() для красивого вывода
        @Override
        public String toString() {
            return "Employee{name='" + name + "', age=" + age +
                    ", socialInsuranceNumber=" + socialInsuranceNumber +
                    ", taxNumber=" + taxNumber + "}";
        }
    }
}
