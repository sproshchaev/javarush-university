package com.javarush.demo;

public class NoMultipleInheritanceDemo {
    public static void main(String[] args) {
        Copier copier = new Copier();
        copier.start();
        copier.startScanner();
    }
}

class PrinterUnit {
    void start() {
        System.out.println("Принтер: прогреваю барабан");
    }
}

class ScannerUnit {
    void start() {
        System.out.println("Сканер: включаю лампу");
    }
}

// class Copier extends PrinterUnit, ScannerUnit { }   // ошибка компиляции

// Родитель один, второй класс подключаем полем
class Copier extends PrinterUnit {
    private ScannerUnit scanner = new ScannerUnit();

    void startScanner() {
        scanner.start();
    }
}
