package com.javarush.example;

/**
 * Пример, демонстрирующий, как enum на самом деле реализуется компилятором.
 * Это не настоящий enum, а эмуляция — показывает внутреннюю структуру,
 * которую компилятор генерирует для enum-типа.
 * Использует if-else для определения значения в toString() — корректный синтаксис.
 */
class Planet {
    // Статические константы — каждая представляет одну планету
    public static final Planet MERCURY = new Planet(0);
    public static final Planet VENUS = new Planet(1);
    public static final Planet EARTH = new Planet(2);
    public static final Planet MARS = new Planet(3);
    public static final Planet JUPITER = new Planet(4);
    public static final Planet SATURN = new Planet(5);
    public static final Planet URANUS = new Planet(6);
    public static final Planet NEPTUNE = new Planet(7);

    // Массив всех значений — аналог values()
    private static final Planet[] values = {
            MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, URANUS, NEPTUNE
    };

    // Поле, хранящее порядковый номер
    private final int value;

    // Закрытый конструктор — объекты можно создавать только внутри класса
    private Planet(int value) {
        this.value = value;
    }

    // Метод ordinal() — возвращает порядковый номер
    public int ordinal() {
        return this.value;
    }

    // Статический метод values() — возвращает массив всех значений
    public static Planet[] values() {
        return values.clone(); // Защита от изменения внешним кодом
    }

    // Переопределяем toString() для читаемого вывода
    @Override
    public String toString() {
        if (this == MERCURY) return "Меркурий";
        if (this == VENUS) return "Венера";
        if (this == EARTH) return "Земля";
        if (this == MARS) return "Марс";
        if (this == JUPITER) return "Юпитер";
        if (this == SATURN) return "Сатурн";
        if (this == URANUS) return "Уран";
        if (this == NEPTUNE) return "Нептун";
        return "Неизвестная планета";
    }

    // Запрещаем клонирование — объекты должны быть уникальными
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Planet objects cannot be cloned.");
    }
}

public class Slide07_EnumAsClassExample {

    public static void main(String[] args) {
        System.out.println("=== Эмуляция enum как обычного класса (на примере Planet) ===");

        Planet current = Planet.EARTH;
        System.out.println("Сейчас на: " + current); // Земля
        System.out.println("Порядковый номер: " + current.ordinal()); // 2

        System.out.println("\n=== Все планеты (через values()) ===");
        for (Planet planet : Planet.values()) {
            System.out.println(planet.ordinal() + ": " + planet);
        }

        System.out.println("\n=== Проверка уникальности объектов ===");
        Planet planet1 = Planet.MARS;
        Planet planet2 = Planet.values()[3];
        Planet planet3 = findPlanetByName("МАРС"); // Имитация valueOf

        System.out.println("Найдена по имени: " + planet3);

        System.out.println("planet1 == planet2? " + (planet1 == planet2)); // true — один и тот же объект!
        System.out.println("planet1 == planet3? " + (planet1 == planet3)); // true — тоже один объект!

        System.out.println("\n=== Попытка создать новый объект (не получится!) ===");
        try {
            // Planet newPlanet = new Planet(99); // Не скомпилируется — конструктор private
            System.out.println("Не удалось создать объект — конструктор закрыт.");
        } catch (Exception e) {
            System.out.println("Ошибка при создании: " + e.getMessage());
        }
    }

    // Имитация valueOf — поиск по имени планеты
    private static Planet findPlanetByName(String name) {
        for (Planet planet : Planet.values()) {
            if (planet.toString().toUpperCase().equals(name)) {
                return planet;
            }
        }
        throw new IllegalArgumentException("No constant with name " + name);
    }
}