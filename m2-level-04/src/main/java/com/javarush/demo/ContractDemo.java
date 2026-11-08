package com.javarush.demo;

import java.util.Arrays;

public class ContractDemo {
    public static void main(String[] args) {
        int[] data = {5, 1, 4, 2, 3};
        Sorter[] sorters = {new BubbleSorter(), new LibrarySorter()};
        for (Sorter sorter : sorters) {
            int[] copy = Arrays.copyOf(data, data.length);
            sorter.sort(copy);
            System.out.println(sorter.getClass().getSimpleName() + ": " + Arrays.toString(copy));
        }
    }
}

// Ожидание: после вызова массив упорядочен по возрастанию
interface Sorter {
    void sort(int[] array);
}

class BubbleSorter implements Sorter {
    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
}

class LibrarySorter implements Sorter {
    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
