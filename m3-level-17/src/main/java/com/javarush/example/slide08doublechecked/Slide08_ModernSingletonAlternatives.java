package com.javarush.example.slide08doublechecked;

// Современные альтернативы Double Checked Locking
class HolderSingleton {
    // Инициализация через static holder class (thread-safe и ленивая)
    private static class SingletonHolder {
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    private HolderSingleton() {
        System.out.println("Создан HolderSingleton");
    }

    public static HolderSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

enum EnumSingleton {
    INSTANCE;

    EnumSingleton() {
        System.out.println("Создан EnumSingleton");
    }

    public void doWork() {
        System.out.println("EnumSingleton работает");
    }
}

// Демонстрация современных подходов
public class Slide08_ModernSingletonAlternatives {
    public static void main(String[] args) {
        System.out.println("=== Современные альтернативы Double Checked Locking ===\n");

        System.out.println("1. Static Holder Class:");
        HolderSingleton holder1 = HolderSingleton.getInstance();
        HolderSingleton holder2 = HolderSingleton.getInstance();
        System.out.println("Один и тот же экземпляр: " + (holder1 == holder2));

        System.out.println("\n2. Enum Singleton:");
        EnumSingleton enum1 = EnumSingleton.INSTANCE;
        EnumSingleton enum2 = EnumSingleton.INSTANCE;
        System.out.println("Один и тот же экземпляр: " + (enum1 == enum2));
        enum1.doWork();
    }
}