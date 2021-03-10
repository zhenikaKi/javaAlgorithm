package ru.kirea.lesson2;

import java.util.Arrays;
import java.util.Random;

public class ArrHelper {
    //Задание 2.1
    public static int[] task_2_1() {
        long timeStart = System.nanoTime();

        //заполняем масив
        int size = 30;
        int[] arr = generateRandomArr(size, 50);
        int[] arr2;

        System.out.println(String.format("Массив: %s", Arrays.toString(arr)));
        System.out.println(String.format("10-ый элемент массива: %d", arr[10]));
        arr2 = Arrays.copyOf(arr, size/2);
        System.out.println(String.format("Новый массив (половина исходного): %s", Arrays.toString(arr2)));
        Arrays.sort(arr);
        System.out.println(String.format("Отсортированный массив: %s", Arrays.toString(arr)));
        System.out.println(String.format("Массивы совпадают или нет: %b", Arrays.equals(arr, arr2)));

        System.out.println(String.format("Время выполнения задания 2.1: %d нс\n", System.nanoTime() - timeStart));
        return arr2;
    }

    //Задание 2.1
    public static void task_2_2(int[] arr, int findValue) {
        long timeStart = System.nanoTime();
        System.out.println(String.format("Значение для поиска: %d", findValue));
        System.out.println(String.format("Массив: %s", Arrays.toString(arr)));

        //ищем значение линейным алгоритмом
        boolean fined = false;
        long timeStartLine = System.nanoTime();
        for (int value: arr) {
            if (value == findValue) {
                fined = true;
                break;
            }
        }
        System.out.println(String.format("Линейный поиск. Результат: %b. Время: %d", fined, System.nanoTime() - timeStartLine));

        //ищем значение двоичным алгоритмом
        fined = false;
        long timeStarBinary = System.nanoTime();
        Arrays.sort(arr);
        int startPosition = 0;
        int endPosition = arr.length - 1;
        while (startPosition <= endPosition) {
            int middlePosition = (startPosition + endPosition) / 2;
            if (arr[middlePosition] == findValue) {
                fined = true;
                break;
            } else if (arr[middlePosition] < findValue) {
                startPosition = middlePosition + 1;
            } else {
                endPosition = middlePosition - 1;
            }
        }
        System.out.println(String.format("Двоичный поиск. Результат: %b. Время: %d", fined, System.nanoTime() - timeStarBinary));

        System.out.println(String.format("Время выполнения задания 2.2: %d нс\n", System.nanoTime() - timeStart));
    }

    //Задание 2.3
    public static void task_2_3(int[] arr) {
        long timeStart = System.nanoTime();
        Arrays.sort(arr);
        System.out.println(String.format("Время выполнения задания 2.3: %d нс\n", System.nanoTime() - timeStart));
    }

    //Задание 2.4
    public static void task_2_4(int[] arr) {
        long timeStart = System.nanoTime();
        for (int indI = 0; indI < arr.length-1; indI++) {
            for (int indJ = indI+1; indJ < arr.length; indJ++) {
                if (arr[indJ] < arr[indI]) {
                    swap(arr, indJ, indI);
                }
            }
        }
        System.out.println(String.format("Время выполнения задания 2.4: %d нс\n", System.nanoTime() - timeStart));
    }

    //Задание 2.5
    public static void task_2_5(int[] arr) {
        long timeStart = System.nanoTime();
        for (int indI = 0; indI < arr.length-1; indI++) {
            int min = indI;
            for (int indJ = indI+1; indJ < arr.length; indJ++) {
                if (arr[indJ] < arr[min]) {
                    min = indJ;
                }
            }
            swap(arr, indI, min);
        }
        System.out.println(String.format("Время выполнения задания 2.5: %d нс\n", System.nanoTime() - timeStart));
    }

    //Задание 2.6
    public static void task_2_6(int[] arr) {
        long timeStart = System.nanoTime();
        int in;
        int tmp;
        for (int ind = 1; ind < arr.length; ind++) {
            tmp = arr[ind];
            in = ind;
            while (in > 0 && arr[in-1] >= tmp) {
                arr[in] = arr[in-1];
                in--;
            }
            arr[in] = tmp;
        }
        System.out.println(String.format("Время выполнения задания 2.6: %d нс\n", System.nanoTime() - timeStart));
    }

    //изменение элементов местами
    private static void swap(int[] arr, int oldPosition, int newPosition) {
        int tmp;
        tmp = arr[oldPosition];
        arr[oldPosition] = arr[newPosition];
        arr[newPosition] = tmp;
    }

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
