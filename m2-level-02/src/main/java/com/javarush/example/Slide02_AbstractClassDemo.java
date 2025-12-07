package com.javarush.example;

/**
 * Демонстрация абстрактных классов.
 * Слайд 2-3: Абстрактные классы и методы.
 */
public class Slide02_AbstractClassDemo {

    public static void main(String[] args) {
        System.out.println("=== Демонстрация абстрактных классов ===\n");

        // 1. Попытка создать абстрактный класс (раскомментировать для ошибки)
        // GeometricShape shape = new GeometricShape("Фигура"); // Ошибка компиляции!
        System.out.println("1. Создать объект абстрактного класса GeometricShape - НЕВОЗМОЖНО.\n");

        // 2. Создание объектов конкретных классов-наследников
        Circle circle = new Circle(5.0, "Круг");
        Rectangle rectangle = new Rectangle(4.0, 6.0, "Прямоугольник");
        Triangle triangle = new Triangle(3.0, 4.0, 5.0, "Треугольник");

        // 3. Работа через полиморфизм (переменная абстрактного типа)
        GeometricShape[] shapes = {circle, rectangle, triangle};

        for (GeometricShape shape : shapes) {
            shape.displayInfo(); // Вызов переопределенного метода
            System.out.println("Площадь: " + shape.calculateArea());
            System.out.println("Периметр: " + shape.calculatePerimeter());
            System.out.println("---");
        }

        // 4. Вызов метода по умолчанию из абстрактного класса
        System.out.println("\n--- Методы абстрактного класса ---");
        circle.printDescription();
        rectangle.printDescription();

        // 5. Демонстрация абстрактного класса без абстрактных методов (редкий случай)
        System.out.println("\n--- Абстрактный класс без абстрактных методов ---");
        // BaseRepository repo = new BaseRepository(); // Нельзя создать!
        MySqlRepository mysqlRepo = new MySqlRepository();
        mysqlRepo.connect();
        mysqlRepo.executeQuery("SELECT * FROM users");
    }
}

/**
 * Абстрактный класс "Геометрическая фигура".
 * Содержит как абстрактные, так и конкретные методы.
 */
abstract class GeometricShape {
    private String name;

    public GeometricShape(String name) {
        this.name = name;
    }

    // АБСТРАКТНЫЕ МЕТОДЫ (должны быть реализованы наследниками)
    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    // Абстрактный метод для отображения информации (каждая фигура делает по-своему)
    public abstract void displayInfo();

    // КОНКРЕТНЫЕ МЕТОДЫ (уже имеют реализацию)
    public String getName() {
        return name;
    }

    public void printDescription() {
        System.out.println("Это геометрическая фигура: " + name);
    }

    // Статический метод в абстрактном классе (разрешен)
    public static void printShapeType() {
        System.out.println("Тип: Геометрическая фигура");
    }
}

/**
 * Конкретный класс-наследник "Круг".
 * Реализует ВСЕ абстрактные методы родителя.
 */
class Circle extends GeometricShape {
    private double radius;

    public Circle(double radius, String name) {
        super(name);
        if (radius <= 0) {
            throw new IllegalArgumentException("Радиус должен быть положительным");
        }
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void displayInfo() {
        System.out.println("○ " + getName() + " с радиусом " + radius);
    }

    // Специфичный метод только для Circle
    public double getDiameter() {
        return 2 * radius;
    }
}

/**
 * Конкретный класс-наследник "Прямоугольник".
 */
class Rectangle extends GeometricShape {
    private double width;
    private double height;

    public Rectangle(double width, double height, String name) {
        super(name);
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Стороны должны быть положительными");
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void displayInfo() {
        System.out.println("▭ " + getName() + " " + width + "x" + height);
    }
}

/**
 * Конкретный класс-наследник "Треугольник".
 */
class Triangle extends GeometricShape {
    private double sideA, sideB, sideC;

    public Triangle(double a, double b, double c, String name) {
        super(name);
        // Проверка на существование треугольника
        if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Некорректные стороны треугольника");
        }
        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
    }

    @Override
    public double calculateArea() {
        // Формула Герона
        double p = calculatePerimeter() / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public void displayInfo() {
        System.out.println("△ " + getName() + " со сторонами " +
                sideA + ", " + sideB + ", " + sideC);
    }
}

/**
 * Пример абстрактного класса БЕЗ абстрактных методов.
 * Такой класс всё равно нельзя инстанцировать, но он может служить
 * базовым классом с общей логикой.
 */
abstract class BaseRepository {
    protected String connectionString;

    public BaseRepository() {
        this.connectionString = "default_connection";
    }

    // Нет абстрактных методов, но класс всё равно abstract
    public void connect() {
        System.out.println("Подключаемся к: " + connectionString);
    }

    public void disconnect() {
        System.out.println("Отключаемся от базы данных");
    }

    // Этот метод МОЖЕТ быть переопределен наследниками (но не обязан)
    public void executeQuery(String query) {
        System.out.println("Выполняем запрос: " + query);
    }
}

/**
 * Конкретный наследник абстрактного класса без абстрактных методов.
 */
class MySqlRepository extends BaseRepository {
    public MySqlRepository() {
        super();
        this.connectionString = "jdbc:mysql://localhost:3306/mydb";
    }

    @Override
    public void executeQuery(String query) {
        System.out.println("MySQL выполняет: " + query);
        // Специфичная логика для MySQL
    }
}