package com.javarush.demo;

public class BracketsTask {
    public static void main(String[] args) {
        String[] samples = {"([]{})", "([)]", "((", "", "{[()()]}"};
        for (String sample : samples) {
            System.out.println("\"" + sample + "\" -> " + isBalanced(sample, new ArrayCharStack()));
        }
    }

    // Проверка знает только интерфейс стека
    static boolean isBalanced(String text, CharStack stack) {
        for (char c : text.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.pop();
                if (!matches(open, c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }
}

interface CharStack {
    void push(char c);

    char pop();

    boolean isEmpty();
}

class ArrayCharStack implements CharStack {
    private char[] items = new char[100];
    private int size;

    @Override
    public void push(char c) {
        items[size++] = c;
    }

    @Override
    public char pop() {
        return items[--size];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
