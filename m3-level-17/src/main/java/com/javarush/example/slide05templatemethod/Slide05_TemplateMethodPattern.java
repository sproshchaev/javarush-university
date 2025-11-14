package com.javarush.example.slide05templatemethod;

// Абстрактный класс, определяющий шаблонный метод
abstract class DataProcessor {
    // Шаблонный метод - финальный, чтобы нельзя было изменить структуру алгоритма
    public final void processData() {
        readData();
        transformData();
        validateData();
        saveData();
        logResult();
    }

    // Общий шаг для всех процессоров
    private void readData() {
        System.out.println("Чтение данных из источника...");
    }

    // Абстрактный метод - должен быть реализован в подклассах
    protected abstract void transformData();

    // Hook-метод - может быть переопределен в подклассах, но имеет реализацию по умолчанию
    protected void validateData() {
        System.out.println("Базовая валидация данных...");
    }

    // Общий шаг для всех процессоров
    private void saveData() {
        System.out.println("Сохранение данных в базу...");
    }

    // Hook-метод с реализацией по умолчанию
    protected void logResult() {
        System.out.println("Обработка завершена успешно");
    }
}

// Конкретная реализация для обработки CSV данных
class CsvDataProcessor extends DataProcessor {
    @Override
    protected void transformData() {
        System.out.println("Преобразование CSV: разбор строк, обработка заголовков...");
    }

    @Override
    protected void validateData() {
        super.validateData(); // Используем базовую валидацию
        System.out.println("Дополнительная валидация: проверка разделителей CSV...");
    }
}

// Конкретная реализация для обработки JSON данных
class JsonDataProcessor extends DataProcessor {
    @Override
    protected void transformData() {
        System.out.println("Преобразование JSON: парсинг объектов, валидация структуры...");
    }

    @Override
    protected void logResult() {
        System.out.println("JSON обработка завершена. Создан отчет по обработанным объектам.");
    }
    // validateData не переопределен - используется базовая реализация
}

// Конкретная реализация для обработки XML данных
class XmlDataProcessor extends DataProcessor {
    @Override
    protected void transformData() {
        System.out.println("Преобразование XML: парсинг DOM, обработка тегов...");
    }

    @Override
    protected void validateData() {
        System.out.println("Специфическая валидация XML: проверка схемы XSD...");
        // Не вызываем super.validateData() - полностью заменяем валидацию
    }

    @Override
    protected void logResult() {
        System.out.println("XML обработка завершена. Сгенерирована статистика по тегам.");
    }
}

// Демонстрация работы паттерна Шаблонный метод
public class Slide05_TemplateMethodPattern {
    public static void main(String[] args) {
        System.out.println("=== Обработка CSV данных ===");
        DataProcessor csvProcessor = new CsvDataProcessor();
        csvProcessor.processData();

        System.out.println("\n=== Обработка JSON данных ===");
        DataProcessor jsonProcessor = new JsonDataProcessor();
        jsonProcessor.processData();

        System.out.println("\n=== Обработка XML данных ===");
        DataProcessor xmlProcessor = new XmlDataProcessor();
        xmlProcessor.processData();

        // Демонстрация того, что структура алгоритма неизменна
        System.out.println("\n=== Все процессоры следуют одному алгоритму ===");
        DataProcessor[] processors = {
                new CsvDataProcessor(),
                new JsonDataProcessor(),
                new XmlDataProcessor()
        };

        for (DataProcessor processor : processors) {
            System.out.println("\n--- Запуск следующего процессора ---");
            processor.processData();
        }
    }
}