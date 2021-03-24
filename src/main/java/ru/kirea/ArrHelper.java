package ru.kirea;

import java.util.*;

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

    //задание 2.2 поиск двоичным алгоритмом
    public static boolean binaryFind(int[] arr, int findValue) {
        boolean fined = false;
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
        return fined;
    }

    //Задание 2.3  сортировка стандартным методом
    public static void sort(int[] arr) {
        long timeStart = System.nanoTime();
        Arrays.sort(arr);
        System.out.println(String.format("Время сортировки: %d нс", System.nanoTime() - timeStart));
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

    //задание 5.4 напечатать элемент массива рекурсией
    public static void printArrValue(int[] arr, int index) {
        if (index < 0 || index > arr.length - 1) return;
        System.out.print(arr[index] + "; ");
        printArrValue(arr, index +1);
    }

    //задание 5.5 - поиск двоичным алгоритмом с рекурсией
    public static boolean binaryFindRecursion(int[] arr, int findValue) {
        boolean fined;
        long timeStarBinary = System.nanoTime();
        Arrays.sort(arr);
        int startPosition = 0;
        int endPosition = arr.length - 1;
        fined = checkBinaryFindRecursion(arr, findValue, startPosition, endPosition);
        System.out.println(String.format("Двоичный поиск рекурсией. Результат: %b. Время: %d", fined, System.nanoTime() - timeStarBinary));
        return fined;
    }
    private static boolean checkBinaryFindRecursion(int[] arr, int findValue, int startPosition, int endPosition) {
        if (startPosition > endPosition) return false;

        int newStartPosition = startPosition;
        int newEndPosition = endPosition;
        int middlePosition = (startPosition + endPosition) / 2;
        if (arr[middlePosition] == findValue) {
            return true;
        } else if (arr[middlePosition] < findValue) {
            newStartPosition = middlePosition + 1;
        } else {
            newEndPosition = middlePosition - 1;
        }
        return checkBinaryFindRecursion(arr, findValue, newStartPosition, newEndPosition);
    }

    //задание 5.6 - сортировка слиянием с рекурсией
    public static int[] sortMergeRecursion(int[] arr) {
        long timeStart = System.nanoTime();
        int [] result = sortMerge(arr);
        System.out.println(String.format("Время сортировки слиянием с рекурсией: %d нс", System.nanoTime() - timeStart));
        return result;
    }
    private static int[] sortMerge(int[] arr) {
        int length = arr.length;
        if (length < 2) return arr;

        int middlePosition = length / 2;
        return merge(sortMerge(Arrays.copyOfRange(arr, 0, middlePosition)),
                sortMerge(Arrays.copyOfRange(arr, middlePosition, length)));
    }
    private static int[] merge(int[] arrA, int[] arrB) {
        int[] result = new int[arrA.length + arrB.length];
        int aIndex = 0;
        int bIndex = 0;

        for (int ind = 0; ind < result.length; ind++) {
            result[ind] = arrA[aIndex] < arrB[bIndex] ? arrA[aIndex++] : arrB[bIndex++];
            if (aIndex == arrA.length) {
                System.arraycopy(arrB, bIndex, result, ++ind, arrB.length - bIndex);
                break;
            }
            if (bIndex == arrB.length) {
                System.arraycopy(arrA, aIndex, result, ++ind, arrA.length - aIndex);
                break;
            }
        }
        return result;
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
