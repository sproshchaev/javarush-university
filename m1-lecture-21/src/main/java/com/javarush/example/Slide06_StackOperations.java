package com.javarush.example;

import java.util.Stack;

/**
 * Демонстрация работы со стеком (Stack) в Java.
 * Показывает все основные операции и принцип LIFO (Last In, First Out).
 */
public class Slide06_StackOperations {

    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы со стеком ===");

        // Демонстрация базовых операций
        demonstrateBasicOperations();

        System.out.println("\n=== Практический пример: Отмена действий ===");
        // Практический пример использования стека
        demonstrateUndoFunctionality();

        System.out.println("\n=== Работа с поиском в стеке ===");
        // Демонстрация метода search()
        demonstrateSearchOperation();
    }

    /**
     * Демонстрирует основные операции со стеком: push, pop, peek, empty
     */
    public static void demonstrateBasicOperations() {
        System.out.println("1. Базовые операции со стеком:");

        // Создаем стек для строк
        Stack<String> stack = new Stack<>();

        // Проверяем, пуст ли стек
        System.out.println("Стек пуст? " + stack.empty());

        // Добавляем элементы в стек (push)
        System.out.println("\nДобавляем элементы в стек:");
        stack.push("Элемент 1");
        stack.push("Элемент 2");
        stack.push("Элемент 3");

        System.out.println("Содержимое стека: " + stack);
        System.out.println("Размер стека: " + stack.size());
        System.out.println("Стек пуст? " + stack.empty());

        // Просматриваем верхний элемент без удаления (peek)
        System.out.println("\nПросмотр верхнего элемента (peek):");
        String topElement = stack.peek();
        System.out.println("Верхний элемент: " + topElement);
        System.out.println("Стек после peek: " + stack);

        // Извлекаем элементы из стека (pop) - в порядке LIFO
        System.out.println("\nИзвлечение элементов (pop):");
        while (!stack.empty()) {
            String element = stack.pop();
            System.out.println("Извлечен: " + element);
            System.out.println("Текущий стек: " + stack);
        }

        System.out.println("Стек пуст? " + stack.empty());
    }

    /**
     * Практический пример: система отмены действий (Undo)
     */
    public static void demonstrateUndoFunctionality() {
        System.out.println("2. Система отмены действий (Undo):");

        // Стек для хранения действий пользователя
        Stack<String> actionStack = new Stack<>();

        // Пользователь выполняет действия
        System.out.println("Пользователь выполняет действия:");
        performAction(actionStack, "Написать текст 'Привет'");
        performAction(actionStack, "Добавить жирный шрифт");
        performAction(actionStack, "Изменить цвет на красный");
        performAction(actionStack, "Добавить подчеркивание");

        System.out.println("\nТекущие действия: " + actionStack);

        // Пользователь отменяет последние действия
        System.out.println("\nПользователь отменяет действия:");
        undoLastAction(actionStack);
        undoLastAction(actionStack);

        System.out.println("Оставшиеся действия: " + actionStack);

        // Добавляем новое действие
        performAction(actionStack, "Изменить размер шрифта");
        System.out.println("После нового действия: " + actionStack);
    }

    /**
     * Демонстрирует работу метода search()
     */
    public static void demonstrateSearchOperation() {
        System.out.println("3. Поиск элементов в стеке (search):");

        Stack<Integer> numberStack = new Stack<>();

        // Заполняем стек числами
        numberStack.push(10);
        numberStack.push(20);
        numberStack.push(30);
        numberStack.push(40);
        numberStack.push(50);

        System.out.println("Стек чисел: " + numberStack);

        // Ищем элементы (поиск ведется от вершины, позиция 1-based)
        System.out.println("Поиск числа 30: позиция " + numberStack.search(30));
        System.out.println("Поиск числа 10: позиция " + numberStack.search(10));
        System.out.println("Поиск числа 50: позиция " + numberStack.search(50));
        System.out.println("Поиск числа 99: позиция " + numberStack.search(99));

        // Демонстрация: как работает поиск при изменении стека
        System.out.println("\nИзвлекаем два элемента...");
        numberStack.pop();
        numberStack.pop();
        System.out.println("Стек после извлечения: " + numberStack);
        System.out.println("Поиск числа 30 теперь: позиция " + numberStack.search(30));
    }

    /**
     * Выполняет действие и добавляет его в стек
     */
    private static void performAction(Stack<String> stack, String action) {
        stack.push(action);
        System.out.println("Выполнено: " + action);
    }

    /**
     * Отменяет последнее действие
     */
    private static void undoLastAction(Stack<String> stack) {
        if (!stack.empty()) {
            String lastAction = stack.pop();
            System.out.println("Отменено: " + lastAction);
        } else {
            System.out.println("Нечего отменять!");
        }
    }
}