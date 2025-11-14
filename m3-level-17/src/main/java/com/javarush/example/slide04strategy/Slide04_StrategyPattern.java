package com.javarush.example.slide04strategy;

import java.util.Arrays;

// Контекст - класс для сортировки данных
class Sorter {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(int[] data) {
        System.out.println("До сортировки: " + Arrays.toString(data));
        strategy.sort(data);
        System.out.println("После сортировки (" + strategy.getName() + "): " + Arrays.toString(data));
    }
}

// Интерфейс стратегии сортировки
interface SortStrategy {
    void sort(int[] data);
    String getName();
}

// Конкретная стратегия - Пузырьковая сортировка
class BubbleSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    // Меняем элементы местами
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Пузырьковая сортировка";
    }
}

// Конкретная стратегия - Быстрая сортировка
class QuickSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    @Override
    public String getName() {
        return "Быстрая сортировка";
    }
}

// Конкретная стратегия - Стандартная сортировка Java
class JavaSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] data) {
        Arrays.sort(data);
    }

    @Override
    public String getName() {
        return "Стандартная сортировка Java";
    }
}

// Демонстрация работы паттерна Strategy
public class Slide04_StrategyPattern {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        int[] data = {64, 34, 25, 12, 22, 11, 90};

        // Используем разные стратегии сортировки для одних и тех же данных
        sorter.setStrategy(new BubbleSortStrategy());
        sorter.sort(data.clone());

        System.out.println();

        sorter.setStrategy(new QuickSortStrategy());
        sorter.sort(data.clone());

        System.out.println();

        sorter.setStrategy(new JavaSortStrategy());
        sorter.sort(data.clone());
    }
}