package ru.kirea;

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
}
