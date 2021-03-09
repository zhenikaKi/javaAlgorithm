package ru.kirea;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ArrHelper {
    //заполнение массива слуйчайными числами
    public static int[] generateRandomArr(int size, int maxValue) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int ind = 0; ind < size; ind++) {
            arr[ind] = random.nextInt(maxValue);
        }

        return arr;
    }

    //Задание 3.1
    public static List<Integer> arrToList(int[] arr) {
        long timeStart = System.nanoTime();
        List<Integer> result = new ArrayList<>();
        for (int value: arr) {
            result.add(value);
        }
        System.out.println(String.format("Время выполнения задания 3.1 (toList): %d нс", System.nanoTime() - timeStart));
        return result;
    }
    public static Collection<Integer> arrToCollection(int[] arr) {
        long timeStart = System.nanoTime();
        Collection<Integer> result = new ArrayList<>();
        for (int value: arr) {
            result.add(value);
        }
        System.out.println(String.format("Время выполнения задания 3.1 (toCollection): %d нс", System.nanoTime() - timeStart));
        return result;
    }

    //Задание 3.2
    public static <T> void addItem(List<T> list, T item) {
        long timeStart = System.nanoTime();
        list.add(item);
        System.out.println(String.format("Время выполнения задания 3.2 (add): %d нс", System.nanoTime() - timeStart));
    }
    public static <T> void removeItem(List<T> list, int index) {
        long timeStart = System.nanoTime();
        list.remove(index);
        System.out.println(String.format("Время выполнения задания 3.2 (remove): %d нс", System.nanoTime() - timeStart));
    }
    public static <T> T getItem(List<T> list, int index) {
        long timeStart = System.nanoTime();
        T item = list.get(index);
        System.out.println(String.format("Время выполнения задания 3.2 (get): %d нс", System.nanoTime() - timeStart));
        return item;
    }

    //задание 6.6 сортировка массива чисел с помощью бинарной пирамиды
    public static void heapSort(int[] arr) {
        long timeStart = System.nanoTime();
        int size = arr.length;
        for (int ind = arr.length/2; ind > 0; ind--) {
            heapify(arr, size, ind);
        }
        while (size > 1) {
            swap(arr, 0, size - 1);
            size--;
            heapify(arr, size, 0);
        }
        System.out.println(String.format("Время выполнения задания 6.6 (сортировка с бин.пирамидой): %d нс", System.nanoTime() - timeStart));
    }

    private static void heapify(int[] arr, int size, int ind) {
        int left = left(ind);
        int right = right(ind);
        int largest = ind;
        if (left < size && arr[ind] < arr[left]) {
            largest = left;
        }
        if (right < size && arr[largest] < arr[right]) {
            largest = right;
        }
        if (ind != largest) {
            swap(arr, ind, largest);
            heapify(arr, size, largest);
        }
    }

    private static void swap(int[] arr, int ind, int largest) {
        int tmp = arr[ind];
        arr[ind] = arr[largest];
        arr[largest] = tmp;
    }

    private static int right(int ind) {
        return 2 * ind + 2;
    }

    private static int left(int ind) {
        return 2 * ind + 1;
    }
}
