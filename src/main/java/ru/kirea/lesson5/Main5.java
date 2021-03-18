package ru.kirea.lesson5;
/*
Задание 5.1
Приведите пример использования рекурсии.
Задание 5.2
Реализуйте простой пример бесконечной рекурсии и обычной рекурсии с условием для выхода.
Задание 5.3
Приведите пример изображающий стек вызова и стек вызова с рекурсией.
Задание 5.4
Реализуйте простой алгоритм использующий цикл и простой алгоритм использующий рекурсию.
Оцените два алгоритма с помощью базового метода System.nanoTime().
Задание 5.5
Реализуйте алгоритм двоичного рекурсивного поиска на основе массива из задания 2.1.
Оцените алгоритм двоичного рекурсивного поиска с помощью базового метода System.nanoTime() и сравните с обычным двоичным поиском.
Задание 5.6
На основе массива из задания 2.1 реализуйте алгоритм сортировки слиянием.
Оцените алгоритм сортировки слиянием с помощью базового метода System.nanoTime() и сравните с сортировкой методом sort().
*/

import ru.kirea.ArrHelper;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main5 {
    private static long timeStart = System.nanoTime();

    public static void main(String[] args) {
        //задание 5.1
        int n = 6;
        System.out.println(String.format("Факториал числа %d = %d\n", n, factorial(n)));

        //задание 5.2
        //System.out.println("Бесконечная рекурсия:");
        //infinityRecursion();
        System.out.println("Рекурсия с условием выхода:");
        exitRecursion();
        System.out.println();

        //задание 5.3
        runTask_5_3_1();
        System.out.println("Стек вызова:");
        StackExecute.printStack();
        StackExecute.clearStack();
        runTask_5_3(2);
        System.out.println("Стек вызова c рекурсией:");
        StackExecute.printStack();

        System.out.println();
        task_5_4();

        System.out.println();
        task_5_5();

        System.out.println();
        task_5_6();
    }

    //вычисление факториала с помощью рекурсии
    private static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    //бесконечная рекурсия
    private static void infinityRecursion() {
        try {
            System.out.println(String.format("Время работы: %d", System.nanoTime() - timeStart));
            TimeUnit.SECONDS.sleep(1);
            infinityRecursion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //рекурсия c условием выхода
    private static void exitRecursion() {
        try {
            long diff = System.nanoTime() - timeStart;
            System.out.println(String.format("Время работы: %d", diff));
            if (diff % 8 == 0) return;
            TimeUnit.SECONDS.sleep(1);
            exitRecursion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //стек вызова методов
    private static void runTask_5_3_1() {
        System.out.println("Запустили 5_3_1");
        StackExecute.add();
        runTask_5_3_2();
    }
    private static void runTask_5_3_2() {
        System.out.println("Запустили 5_3_2");
        StackExecute.add();
        runTask_5_3_3();
    }
    private static void runTask_5_3_3() {
        System.out.println("Запустили 5_3_3");
        StackExecute.add();
        runTask_5_3_4();
    }
    private static void runTask_5_3_4() {
        System.out.println("Запустили 5_3_4");
        StackExecute.add();
    }

    //стек вызова методов рекурсионно
    private static void runTask_5_3(int value) {
        System.out.println(String.format("Запустили 5_3 (%d)", value));
        StackExecute.add(String.valueOf(value));
        if (value <= 0) return;
        runTask_5_3(value -1);
    }

    //реализация цикла и рекурсии цикла
    private static void task_5_4() {
        int[] arr = ArrHelper.generateRandomArr(10, 50);
        System.out.println("Сформированный массив: " + Arrays.toString(arr));

        long timeStart = System.nanoTime();
        System.out.print("Обход массива циклом: ");
        for (int value: arr) {
            System.out.print(value + "; ");
        }
        System.out.println();
        System.out.println(String.format("Время обхода циклом: %d нс", System.nanoTime() - timeStart));

        timeStart = System.nanoTime();
        System.out.print("Обход массива рекурсией: ");
        ArrHelper.printArrValue(arr, 0);
        System.out.println();
        System.out.println(String.format("Время обхода рекурсией: %d нс", System.nanoTime() - timeStart));
    }

    //поиск двочным алгоритмом и рекурсией
    private static void task_5_5() {
        int[] arr = ArrHelper.generateRandomArr(10, 30);
        int[] arr2 = Arrays.copyOf(arr, arr.length); //скопируем массив, чтобы каждый метод работал одинаково - сперва сортировал
        System.out.println("Сформированный массив: " + Arrays.toString(arr));
        int finedValue = 23;
        System.out.println("Значение для поиска: " + finedValue);
        ArrHelper.binaryFind(arr, finedValue);
        ArrHelper.binaryFindRecursion(arr2, finedValue);
    }

    //сортировка методом слияния
    private static void task_5_6() {
        int[] arr = ArrHelper.generateRandomArr(20, 80);
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        System.out.println("Сформированный массив: " + Arrays.toString(arr));
        ArrHelper.sort(arr);
        System.out.println("Отсортированный массив: " + Arrays.toString(arr));
        arr2 = ArrHelper.sortMergeRecursion(arr2);
        System.out.println("Отсортированный массив слиянием: " + Arrays.toString(arr2));
    }
}
