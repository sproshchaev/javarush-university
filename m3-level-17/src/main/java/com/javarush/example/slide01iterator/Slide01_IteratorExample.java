package com.javarush.example.slide01iterator;

// 1. Интерфейс итератора
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// 2. Интерфейс агрегата (коллекции)
interface Aggregate<T> {
    Iterator<T> createIterator();
}

// 3. Конкретная коллекция (Массив)
class StringArray implements Aggregate<String> {
    private String[] items;

    public StringArray(String[] items) {
        this.items = items;
    }

    @Override
    public Iterator<String> createIterator() {
        return new StringArrayIterator(items);
    }
}

// 4. Конкретный итератор для массива
class StringArrayIterator implements Iterator<String> {
    private String[] items;
    private int position = 0;

    public StringArrayIterator(String[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.length;
    }

    @Override
    public String next() {
        if (hasNext()) {
            return items[position++];
        }
        throw new java.util.NoSuchElementException();
    }
}

// 5. Демонстрация работы
public class Slide01_IteratorExample {
    public static void main(String[] args) {
        String[] languages = {"Java", "Python", "C++", "JavaScript"};
        Aggregate<String> aggregate = new StringArray(languages);
        Iterator<String> iterator = aggregate.createIterator();

        System.out.println("Обход массива через итератор:");
        while (iterator.hasNext()) {
            String language = iterator.next();
            System.out.println("Язык: " + language);
        }
    }
}