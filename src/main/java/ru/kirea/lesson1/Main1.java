package ru.kirea.lesson1;
/*
Задание 1.1
Приведите пример алгоритмов и структур данных из жизни.
Задание 1.2
Приведите пример алгоритмов и структур данных в программировании.
Задание 1.3
Напишите программный код, в котором все данные хранятся только в переменных трех типов данных: Ссылочные, примитивные и своего класса содержащего: конструктор и метод отображения данных.
Выведите написанные данные.
Задание 1.4
Дополните предыдущий код сравнением ваших данных с другой переменной, данный код должен имитировать простейший поиск перебором.
Оцените время выполнения алгоритма с помощью базового метода System.nanoTime().
*/

import java.util.Random;

public class Main1 {
    public static void main(String[] args) {
        int size = 10;

        /*Задание 1.1. Алгоритм из жизни - приготовление супа:
        * 1. поставить вариться курицу.
        * 2. Засупать крупу.
        * 3. Нарезать картофель, лук, морковь. Высыпать в суп.
        * 4. Посолить.
        * 5. Размельчить курицу.
        * 6. Доварить суп.*/

        /*Задание 1.2. Алгоритм из программирования - сохранение файла.
        * 1. сформировать список с данными.
        * 2. создать файл.
        * 3. циклом пройтись по списку и записать все в файл.
        * 4. сохранить файл.*/

        //Задание 1.3
        int[] arrayInt = new int[size]; //массив примитивных типов
        Integer[] arrayInteger = new Integer[size]; //массив ссылочных типов
        MyClass[] arrayMyClass = new MyClass[size]; //массив абстрактного типа данных

        //заполняем массивы случайными числами от 0 до 20
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int ind = 0; ind < size; ind++) {
            arrayInt[ind] = random.nextInt(20);
            arrayInteger[ind] = random.nextInt(20);
            arrayMyClass[ind] = new MyClass(random.nextInt(20));
        }
        System.out.println("Время заполнения: " + (System.nanoTime() - startTime));

        //выведем все масивы
        startTime = System.nanoTime();
        System.out.print("Примитивный массив: ");
        for (int ind = 0; ind < size; ind++) {
            System.out.print(arrayInt[ind] + "  ");
        }
        System.out.println();
        System.out.println("Время вывода: " + (System.nanoTime() - startTime));

        startTime = System.nanoTime();
        System.out.print("Ссылочный массив: ");
        for (int ind = 0; ind < size; ind++) {
            System.out.print(arrayInteger[ind] + "  ");
        }
        System.out.println();
        System.out.println("Время вывода: " + (System.nanoTime() - startTime));

        startTime = System.nanoTime();
        System.out.print("Массив абстрактного типа: ");
        for (int ind = 0; ind < size; ind++) {
            arrayMyClass[ind].printValue();
            System.out.print("  ");
        }
        System.out.println();
        System.out.println("Время вывода: " + (System.nanoTime() - startTime));

        //Задание 1.4 сравниваем значение
        int value = random.nextInt(20);

        startTime = System.nanoTime();
        boolean check = false;
        System.out.print("Примитивный массив: ");
        for (int ind = 0; ind < size; ind++) {
            check = (arrayInt[ind] == value);
            if (check) break;
        }
        System.out.println(value + " - " + check);
        System.out.println("Время поиска: " + (System.nanoTime() - startTime));

        startTime = System.nanoTime();
        check = false;
        Integer valueInteger = value;
        System.out.print("Ссылочный массив: ");
        for (int ind = 0; ind < size; ind++) {
            check = (arrayInteger[ind].equals(valueInteger));
            if (check) break;
        }
        System.out.println(value + " - " + check);
        System.out.println("Время поиска: " + (System.nanoTime() - startTime));

        startTime = System.nanoTime();
        check = false;
        MyClass valueMyClass = new MyClass(value);
        System.out.print("Массив абстрактного типа: ");
        for (int ind = 0; ind < size; ind++) {
            check = (arrayMyClass[ind].equals(valueMyClass));
            if (check) break;
        }
        System.out.println(value + " - " + check);
        System.out.println("Время поиска: " + (System.nanoTime() - startTime));
    }
}
