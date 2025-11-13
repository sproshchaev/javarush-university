package com.javarush;

public class AutoCloseableDemo {

    public void mentod() throws Exception {
        // Класс помещается в try-with-resources если имплементирует интерфейс Autocloseable
        try (MyResource myResource = new MyResource()) {
            System.out.println("Работа с ресурсом");
        }
    }

}
