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
        System.out.println(String.format("Время выполнения задания 3.1: %d нс\n", System.nanoTime() - timeStart));
        return result;
    }
    public static Collection<Integer> arrToCollection(int[] arr) {
        long timeStart = System.nanoTime();
        Collection<Integer> result = new ArrayList<>();
        for (int value: arr) {
            result.add(value);
        }
        System.out.println(String.format("Время выполнения задания 3.1: %d нс\n", System.nanoTime() - timeStart));
        return result;
    }
}
