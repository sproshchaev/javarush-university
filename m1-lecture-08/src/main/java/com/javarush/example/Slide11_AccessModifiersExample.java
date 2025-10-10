package com.javarush.example;


/**
 * Пример из слайда 11: "Модификаторы доступа"
 *
 * Модификаторы доступа определяют, кто может обращаться к методам и полям.
 * Есть 4 типа доступа: public, protected, package-private (без модификатора), private.
 */
public class Slide11_AccessModifiersExample {

    // 👇 Поля с разными модификаторами
    public String publicField = "Доступно всем";
    protected String protectedField = "Доступно в пакете и наследникам";
    String packagePrivateField = "Доступно только в пакете"; // без модификатора
    private String privateField = "Доступно только в этом классе";

    // 👇 Методы с разными модификаторами
    public void publicMethod() {
        System.out.println("Этот метод доступен везде.");
        privateMethod(); // ✅ можно вызвать изнутри класса
    }

    protected void protectedMethod() {
        System.out.println("Этот метод доступен в пакете и наследникам.");
    }

    void packagePrivateMethod() {
        System.out.println("Этот метод доступен только в пакете.");
    }

    private void privateMethod() {
        System.out.println("Этот метод доступен только в этом классе.");
    }

    public static void main(String[] args) {
        System.out.println("=== Модификаторы доступа ===");

        Slide11_AccessModifiersExample obj = new Slide11_AccessModifiersExample();

        // ✅ Все методы и поля доступны изнутри класса
        obj.publicMethod();
        obj.protectedMethod();
        obj.packagePrivateMethod();
        obj.privateMethod();

        System.out.println("\n--- Доступ к полям ---");
        System.out.println("Public: " + obj.publicField);
        System.out.println("Protected: " + obj.protectedField);
        System.out.println("Package-private: " + obj.packagePrivateField);
        System.out.println("Private: " + obj.privateField);

        System.out.println("\n✅ В этом классе все доступно — мы внутри него.");
    }
}
