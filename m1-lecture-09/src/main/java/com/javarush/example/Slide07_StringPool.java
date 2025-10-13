package com.javarush.example;

/**
 * Пример работы с String Pool и методом intern()
 */
public class Slide07_StringPool {

    public static void main(String[] args) {
        System.out.println("=== String Pool ===\n");

        // 1. Строки-литералы — автоматически попадают в String Pool
        String a = "Привет";
        String b = "Привет"; // та же строка → ссылка на тот же объект!

        System.out.println("a == b: " + (a == b)); // ✅ true — ссылки совпадают!
        System.out.println("a.equals(b): " + a.equals(b)); // ✅ true — содержимое одинаково

        // 2. Создание строки через new — НЕ попадает в String Pool (по умолчанию)
        String c = new String("Привет");
        String d = new String("Привет");

        System.out.println("\nc == d: " + (c == d)); // ❌ false — разные объекты в Heap
        System.out.println("c.equals(d): " + c.equals(d)); // ✅ true — содержимое одинаково

        // 3. Используем intern() — чтобы поместить строку в String Pool
        String e = c.intern(); // добавляем c в String Pool (если ещё нет)
        String f = d.intern();

        System.out.println("\ne == f: " + (e == f)); // ✅ true — теперь обе ссылаются на String Pool
        System.out.println("e.equals(f): " + e.equals(f)); // ✅ true

        // 4. Пример с новой строкой
        String g = "Пока";
        String h = new String("Пока").intern(); // явно добавляем в пул

        System.out.println("\ng == h: " + (g == h)); // ✅ true — обе в String Pool
        System.out.println("g.equals(h): " + g.equals(h)); // ✅ true

        // 5. Что если строки разные?
        String i = "Hello";
        String j = "World";

        System.out.println("\ni == j: " + (i == j)); // ❌ false — разные строки → разные объекты
        System.out.println("i.equals(j): " + i.equals(j)); // ❌ false — разное содержимое

        // 6. Показываем, что intern() добавляет строку в пул, если её там нет
        String k = "Новая строка";
        String l = new String("Новая строка").intern();

        System.out.println("\nk == l: " + (k == l)); // ✅ true — intern() вернул ссылку на строку из пула
    }
}