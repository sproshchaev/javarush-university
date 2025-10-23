package com.javarush;


public class StaticVariable {

    // Статическая переменная - общая для всех объектов
    static int totalCount = 0;

    // Конструктор класса - увеличивает счетчик при создании объекта
    public StaticVariable() {
        // totalCount = totalCount + 1;
        totalCount++;
    }

    // Метод, выводящий текущее состояние счетчика
    public void showTotalCount() {
        System.out.println("Всего создано объектов: " + totalCount);
    }

    public static void main(String[] args) {
        StaticVariable staticVariable1 = new StaticVariable();
        staticVariable1.showTotalCount();

        StaticVariable staticVariable2 = new StaticVariable();
        staticVariable2.showTotalCount();

        StaticVariable staticVariable3 = new StaticVariable();
        staticVariable3.showTotalCount();
    }

}
