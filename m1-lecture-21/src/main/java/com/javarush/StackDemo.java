package com.javarush;

import java.util.Stack;

public class StackDemo {

    public static void main(String[] args) {
        // Создаем стек
        Stack<String> stack = new Stack<>();

        // Добавляем элементы
        stack.push("Первый");
        stack.push("Второй");
        stack.push("Третий");

        System.out.println(stack);

        // peek() - посмотреть верхний элемент без удаления
        System.out.println("Верхний:" + stack.peek());
        System.out.println(stack);

        // pop() - извлекаем (c удалением)
        System.out.println("Извлекаем: " + stack.pop());
        System.out.println(stack);

        // empty()
        System.out.println("Пуст: " + stack.isEmpty());

    }

}
