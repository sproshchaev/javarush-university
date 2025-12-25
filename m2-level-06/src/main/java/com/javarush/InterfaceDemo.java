package com.javarush;

// 1) Интерфейс-контракт с ОДНИМ методом
interface Task {
    void execute();
}

// 2) Класс, который реализует контракт
class HelloTask implements Task {
    @Override
    public void execute() {
        System.out.println("Hello World!");
    }
}

public class InterfaceDemo {

    public static void main(String[] args) {
        // Способ 1: используем конкретный класс
        Task task1 = new HelloTask();
        task1.execute();

        // Способ 2: используем анонимный класс
        Task task2 = new Task() {
            // Переопределение метода
            @Override
            public void execute() {
                System.out.println("Я выполняюсь из анонимного класса: Hello World!");
            }
        };
        task2.execute();

    }

}
