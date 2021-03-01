package ru.kirea.lesson3;
/*
Задание 3.1
На основе массива из домашнего задания 2.1 реализуйте простой список и коллекцию.
Оцените время выполнения преобразования.
Задание 3.2
На основе списка из задания 3.1 реализуйте основные методы добавления, удаления и получения объекта или элемента из списка.
Оценить выполненные методы с помощью базового класса System.nanoTime().
Задание 3.3
Реализуйте простой односвязный список и его базовые методы.
Задание 3.4
На основе списка из задания 3.1 реализуйте простой двусторонний список и его базовые методы.
Реализуйте список заполненный объектами из вашего класса из задания 1.3
Задание 3.5
Реализуйте итератор на основе связанных списков из задания 3.4 и выполните базовые операции итератора.
Оцените время выполнения операций с помощью базового метода System.nanoTime()
*/

import ru.kirea.ArrHelper;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        //задание 3.1
        int[] arr1 = ArrHelper.generateRandomArr(30, 100);
        System.out.println("Задание 3.1 - массив в список");
        List<Integer> list1 = ArrHelper.arrToList(arr1);
        System.out.println("Массив: " + Arrays.toString(arr1));
        System.out.println("Список: " + list1.toString() + "\n");
        System.out.println("Задание 3.1 - массив в коллекцию");
        Collection<Integer> collection1 = ArrHelper.arrToCollection(arr1);
        System.out.println("Массив: " + Arrays.toString(arr1));
        System.out.println("Коллекция: " + collection1.toString() + "\n");

        //задание 3.2
        ArrHelper.addItem(list1, 28);
        System.out.println("Список с добавленным элементом: " + list1.toString());
        ArrHelper.removeItem(list1, 14);
        System.out.println("Список с удаленным элементом: " + list1.toString());
        int item = ArrHelper.getItem(list1, 25);
        System.out.println("Полученный элемент: " + item + "\n");

        //задание 3.3
        System.out.println("Задание 3.3");
        OneLink<String> oneLink = new OneLink<>();
        oneLink.add("Петя");
        oneLink.add("Вася");
        oneLink.add("Вова");
        oneLink.print(); //Вова; Вася; Петя;
        oneLink.remove();
        oneLink.print(); //Вася; Петя;

        //задание 3.4
        System.out.println("Задание 3.4");
        TwoLink<String> twoLink = new TwoLink<>();
        twoLink.add("Катя");
        twoLink.add("Даша");
        twoLink.add("Маша");
        twoLink.add("Оля");
        twoLink.print(); //Оля; Маша; Даша; Катя;
        twoLink.remove(new TwoLinkItem<>("Маша"));
        twoLink.print(); //Оля; Даша; Катя;
        System.out.println("Задание 3.4 - на основе 3.1");
        TwoLink<Integer> twoLink2 = new TwoLink<>(list1);
        twoLink2.print();

        //задание 3.5
        System.out.println("Задание 3.5");
        Iterator<Integer> iterator = twoLink2.iterator();
        int countAll = list1.size();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            System.out.print(iterator.next() + "; ");
            iterator.remove();
        }
        System.out.println();
        System.out.print("Список после итерации: ");
        twoLink2.print(); //null
    }
}
