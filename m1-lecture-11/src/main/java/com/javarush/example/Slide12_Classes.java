package com.javarush.example;

/**
 * Слайд 12: Классы.
 * Демонстрация определения класса, его полей, конструктора и создания объектов.
 */
public class Slide12_Classes {

    public static void main(String[] args) {
        System.out.println("=== Слайд 12: Классы ===\n");

        // Создание объекта класса Cat (как в примере слайда)
        System.out.println("1. Создание объекта класса Cat:");
        Cat barsik = new Cat(); // Создаём объект (экземпляр) класса Cat
        barsik.name = "Барсик"; // Инициализируем поле name
        barsik.age = 3;          // Инициализируем поле age

        System.out.printf("Cat barsik = new Cat(); -> barsik = %s\n", barsik);
        System.out.println();

        // 2. Вывод информации об объекте (как в примере слайда)
        System.out.println("2. Вывод информации об объекте:");
        System.out.printf("Кот %s, возраст: %d лет.\n", barsik.name, barsik.age);
        System.out.println();

        // 3. Создание другого объекта того же класса
        System.out.println("3. Создание другого объекта класса Cat:");
        Cat murzik = new Cat();
        murzik.name = "Мурзик";
        murzik.age = 5;

        System.out.printf("Cat murzik = new Cat(); -> murzik = %s\n", murzik);
        System.out.printf("Кот %s, возраст: %d лет.\n", murzik.name, murzik.age);
        System.out.println();

        // 4. Понятие класса как шаблона
        System.out.println("4. Класс как шаблон:");
        System.out.println("Класс Cat определяет, что каждый кот (объект) должен иметь:");
        System.out.println(" - Поле 'name' (строка) для имени.");
        System.out.println(" - Поле 'age' (целое число) для возраста.");
        System.out.println("Все объекты класса Cat будут иметь эти поля, но их значения могут быть разными.");
        System.out.println();

        // 5. Улучшенная версия класса Cat с конструктором
        System.out.println("5. Улучшенная версия класса Cat (с конструктором):");
        Cat improvedBarsik = new Cat("Барсик", 3); // Передаём параметры в конструктор
        Cat improvedMurzik = new Cat("Мурзик", 5);

        System.out.printf("Cat improvedBarsik = new Cat(\"Барсик\", 3); -> %s\n", improvedBarsik);
        System.out.printf("Cat improvedMurzik = new Cat(\"Мурзик\", 5); -> %s\n", improvedMurzik);

        System.out.println("\n--- Запомни ---");
        System.out.println("Класс — это шаблон, определяющий структуру и поведение объектов.");
        System.out.println("Объект — это конкретный экземпляр класса, созданный с помощью new.");
        System.out.println("Поля класса (переменные) хранят состояние объекта.");
        System.out.println("Классы помогают организовать код, разбивая его на логические модули.");
    }

    // Определение класса Cat (как в примере слайда)
    static class Cat {
        String name; // Поле (переменная) класса - имя кота
        int age;     // Поле (переменная) класса - возраст кота

        // Конструктор по умолчанию (без параметров)
        public Cat() {
            // Можно добавить инициализацию по умолчанию, если нужно
        }

        // Конструктор с параметрами (улучшенная версия)
        public Cat(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Переопределение toString() для красивого вывода
        @Override
        public String toString() {
            return "Cat{name='" + name + "', age=" + age + "}";
        }
    }
}
