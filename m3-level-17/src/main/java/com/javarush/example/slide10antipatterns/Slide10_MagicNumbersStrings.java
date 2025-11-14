package com.javarush.example.slide10antipatterns;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.regex.Pattern;

// Детальный разбор антипаттерна "Магические числа и строки"
public class Slide10_MagicNumbersStrings {

    // ========== ПЛОХИЕ ПРИМЕРЫ ==========

    // Пример 1: Магические числа в бизнес-логике
    static class BadBusinessLogic {
        public double calculateSalary(int hoursWorked, int employeeType) {
            double baseRate;
            if (employeeType == 1) { // Что такое 1?
                baseRate = 15.5; // Что такое 15.5?
            } else if (employeeType == 2) { // Что такое 2?
                baseRate = 20.0; // Что такое 20.0?
            } else {
                baseRate = 25.0; // Что такое 25.0?
            }

            double salary = hoursWorked * baseRate;

            // Магические числа в расчетах
            if (hoursWorked > 40) { // Почему 40?
                salary += (hoursWorked - 40) * baseRate * 1.5; // Что такое 1.5?
            }

            // Магические числа в вычетах
            if (salary > 1000) { // Почему 1000?
                salary -= salary * 0.2; // Что такое 0.2?
            }

            return salary;
        }
    }

    // Пример 2: Магические строки
    static class BadStringProcessor {
        public boolean isValidUser(String username, String role) {
            // Магические строки
            if (username == null || username.length() < 3) { // Почему 3?
                return false;
            }

            if (!role.equals("admin") && !role.equals("user") && !role.equals("moderator")) {
                return false; // Что означают эти строки?
            }

            // Магические регулярные выражения
            if (!username.matches("[a-zA-Z0-9_]+")) { // Что проверяет это regex?
                return false;
            }

            return true;
        }

        public String getErrorMessage(int errorCode) {
            // Магические числа в switch
            switch (errorCode) {
                case 1: return "File not found"; // Что такое 1?
                case 2: return "Permission denied"; // Что такое 2?
                case 3: return "Network error"; // Что такое 3?
                case 404: return "Not found"; // Что такое 404?
                case 500: return "Server error"; // Что такое 500?
                default: return "Unknown error";
            }
        }
    }

    // Пример 3: Магические числа в датах и времени
    static class BadDateCalculator {
        public boolean isWeekend(LocalDate date) {
            return date.getDayOfWeek().getValue() > 5; // Почему 5? Что означает?
        }

        public boolean isSummer(LocalDate date) {
            int month = date.getMonthValue();
            return month >= 6 && month <= 8; // Почему 6 и 8?
        }

        public boolean canRentCar(int age) {
            return age >= 21; // Почему 21?
        }
    }

    // ========== ХОРОШИЕ ПРИМЕРЫ ==========

    // Пример 1: Константы вместо магических чисел
    static class GoodBusinessLogic {
        // Константы для типов сотрудников
        private static final int EMPLOYEE_TYPE_INTERN = 1;
        private static final int EMPLOYEE_TYPE_REGULAR = 2;
        private static final int EMPLOYEE_TYPE_SENIOR = 3;

        // Константы для ставок
        private static final double INTERN_RATE = 15.5;
        private static final double REGULAR_RATE = 20.0;
        private static final double SENIOR_RATE = 25.0;

        // Константы для расчетов
        private static final int STANDARD_WORK_HOURS = 40;
        private static final double OVERTIME_MULTIPLIER = 1.5;
        private static final double TAX_THRESHOLD = 1000.0;
        private static final double TAX_RATE = 0.2;

        public double calculateSalary(int hoursWorked, int employeeType) {
            double baseRate = getBaseRate(employeeType);
            double salary = hoursWorked * baseRate;

            // Сверхурочные
            if (hoursWorked > STANDARD_WORK_HOURS) {
                salary += calculateOvertime(hoursWorked, baseRate);
            }

            // Налоги
            if (salary > TAX_THRESHOLD) {
                salary -= calculateTax(salary);
            }

            return salary;
        }

        private double getBaseRate(int employeeType) {
            switch (employeeType) {
                case EMPLOYEE_TYPE_INTERN: return INTERN_RATE;
                case EMPLOYEE_TYPE_REGULAR: return REGULAR_RATE;
                case EMPLOYEE_TYPE_SENIOR: return SENIOR_RATE;
                default: throw new IllegalArgumentException("Unknown employee type: " + employeeType);
            }
        }

        private double calculateOvertime(int hoursWorked, double baseRate) {
            int overtimeHours = hoursWorked - STANDARD_WORK_HOURS;
            return overtimeHours * baseRate * OVERTIME_MULTIPLIER;
        }

        private double calculateTax(double salary) {
            return salary * TAX_RATE;
        }
    }

    // Пример 2: Enum и константы для строк
    static class GoodStringProcessor {
        // Enum для ролей
        public enum UserRole {
            ADMIN("admin"),
            USER("user"),
            MODERATOR("moderator");

            private final String roleName;

            UserRole(String roleName) {
                this.roleName = roleName;
            }

            public String getRoleName() {
                return roleName;
            }
        }

        // Константы для валидации
        private static final int MIN_USERNAME_LENGTH = 3;
        private static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z0-9_]+");

        // Enum для кодов ошибок
        public enum ErrorCode {
            FILE_NOT_FOUND(1, "File not found"),
            PERMISSION_DENIED(2, "Permission denied"),
            NETWORK_ERROR(3, "Network error"),
            NOT_FOUND(404, "Not found"),
            SERVER_ERROR(500, "Server error");

            private final int code;
            private final String message;

            ErrorCode(int code, String message) {
                this.code = code;
                this.message = message;
            }

            public int getCode() { return code; }
            public String getMessage() { return message; }
        }

        public boolean isValidUser(String username, UserRole role) {
            if (username == null || username.length() < MIN_USERNAME_LENGTH) {
                return false;
            }

            if (role == null) {
                return false;
            }

            if (!USERNAME_PATTERN.matcher(username).matches()) {
                return false;
            }

            return true;
        }

        public String getErrorMessage(ErrorCode errorCode) {
            return errorCode.getMessage();
        }
    }

    // Пример 3: Константы для дат и бизнес-правил
    static class GoodDateCalculator {
        private static final int WEEKEND_START = DayOfWeek.SATURDAY.getValue();
        private static final int SUMMER_START_MONTH = 6;
        private static final int SUMMER_END_MONTH = 8;
        private static final int MIN_CAR_RENTAL_AGE = 21;

        public boolean isWeekend(LocalDate date) {
            return date.getDayOfWeek().getValue() >= WEEKEND_START;
        }

        public boolean isSummer(LocalDate date) {
            int month = date.getMonthValue();
            return month >= SUMMER_START_MONTH && month <= SUMMER_END_MONTH;
        }

        public boolean canRentCar(int age) {
            return age >= MIN_CAR_RENTAL_AGE;
        }
    }

    // ========== ДЕМОНСТРАЦИЯ ==========

    public static void main(String[] args) {
        System.out.println("=== Магические числа и строки ===\n");

        // Демонстрация бизнес-логики
        System.out.println("1. Бизнес-логика:");
        BadBusinessLogic badBiz = new BadBusinessLogic();
        GoodBusinessLogic goodBiz = new GoodBusinessLogic();

        System.out.println("Плохо: " + badBiz.calculateSalary(45, 2));
        System.out.println("Хорошо: " + goodBiz.calculateSalary(45, GoodBusinessLogic.EMPLOYEE_TYPE_REGULAR));

        // Демонстрация работы со строками
        System.out.println("\n2. Работа со строками:");
        BadStringProcessor badString = new BadStringProcessor();
        GoodStringProcessor goodString = new GoodStringProcessor();

        System.out.println("Плохая валидация: " + badString.isValidUser("john", "admin"));
        System.out.println("Хорошая валидация: " + goodString.isValidUser("john", GoodStringProcessor.UserRole.ADMIN));

        System.out.println("Плохие ошибки: " + badString.getErrorMessage(404));
        System.out.println("Хорошие ошибки: " + goodString.getErrorMessage(GoodStringProcessor.ErrorCode.NOT_FOUND));

        // Демонстрация работы с датами
        System.out.println("\n3. Работа с датами:");
        BadDateCalculator badDate = new BadDateCalculator();
        GoodDateCalculator goodDate = new GoodDateCalculator();

        LocalDate today = LocalDate.now();
        System.out.println("Плохая проверка выходных: " + badDate.isWeekend(today));
        System.out.println("Хорошая проверка выходных: " + goodDate.isWeekend(today));

        System.out.println("Плохая проверка аренды: " + badDate.canRentCar(20));
        System.out.println("Хорошая проверка аренды: " + goodDate.canRentCar(20));

        System.out.println("\n=== Преимущества хорошего подхода ===");
        System.out.println("• Код самодокументируется");
        System.out.println("• Легко изменять значения в одном месте");
        System.out.println("• Меньше ошибок при модификации");
        System.out.println("• Лучшая читаемость и поддерживаемость");
    }
}