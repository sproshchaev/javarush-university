package com.javarush.demo.exception;

public class DemoResource implements AutoCloseable {

    private final String name;

    public DemoResource(String name) {
        this.name = name;
    }

    public void work(boolean fail) {
        System.out.println("Работаем с " + name);
        if (fail) {
            throw new IllegalStateException("Сбой при работе с " + name);
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("Закрыли " + name);
    }
}
