package ru.kirea.lesson6;
/*
Задание 6.1
Приведите пример использования древовидной структуры.
Задание 6.2
Реализуйте класс узла дерева и базовый шаблон дерева с базовыми методами.
Задание 6.3
Реализуйте методы поиска и вставки узла в дерево.
Задание 6.4
Реализуйте базовые методы обхода дерева и метода дисплей.
Реализуйте поиск максимума и минимума.
Задание 6.5
Реализуйте метод удаления узла и выполните оценку времени базовых методов дерева с помощью System.nanoTime().
Задание 6.6
Реализуйте на основе массива из задания 2.1 алгоритм пирамидальной сортировки с реализацией бинарной пирамиды.
Выполните оценку алгоритма пирамидальной сортировки с помощью System.nanoTime() и сравните с предыдущими алгоритмами сортировки.
Задание 6.7
Приведите пример сбалансированного дерева и его применения.
 */

import ru.kirea.ArrHelper;

import java.util.Arrays;

public class Main6 {
    public static void main(String[] args) {
        //в дереве будем хранить список ФИО
        Tree<Human> treeHumans = new Tree<>(Human::compareTo);

        /*задание 6.1: деревовидная структура дерева - компания олрифлейм со своими работниками,
          которые набирают к себе в команду других работников, а те еще других.*/

        //6.3
        System.out.println("Заполнение дерева");
        treeHumans.insert(new Human("Иванов", "Максим", "Петрович"));
        treeHumans.insert(new Human("Сидорова", "Наталья", "Викторовна"));
        treeHumans.insert(new Human("Смирнов", "Петр", "Петрович"));
        treeHumans.insert(new Human("Иванов", "Александр", "Евгеньевич"));
        treeHumans.insert(new Human("Сидоров", "Юрий"));
        treeHumans.insert(new Human("Артемова", "Анастасия", "Петровна"));
        treeHumans.insert(new Human("Иванов", "Александр", "Михайлович"));
        treeHumans.insert(new Human("Зуев", "Виктор", "Викторович"));
        treeHumans.insert(new Human("Петров", "Константин"));
        treeHumans.insert(new Human("Борисов", "Николай", "Юрьевич"));
        System.out.println();

        //6.3
        Human min = treeHumans.min().getItem();
        System.out.println("Первый по алфавиту: " + min);
        System.out.println();

        //6.3
        Human max = treeHumans.max().getItem();
        System.out.println("Последний по алфавиту: " + max);
        System.out.println();

        //6.4
        System.out.println("Симметричный обход дерева");
        treeHumans.symmetricView();
        System.out.println();

        //6.4
        System.out.println("Прямой обход дерева");
        treeHumans.forwardView();
        System.out.println();

        //6.4
        System.out.println("Обратный обход дерева");
        treeHumans.reverseView();
        System.out.println();

        //6.5
        System.out.println("Удаление левого элемента");
        treeHumans.delete(new Human("Петров", "Константин"));
        System.out.println("Удаление правого элемента");
        treeHumans.delete(new Human("Смирнов", "Петр", "Петрович"));
        System.out.println("Удаление элемента с дочерними элементами");
        treeHumans.delete(new Human("Сидорова", "Наталья", "Викторовна"));
        treeHumans.symmetricView();
        System.out.println();

        //6.6
        int[] arr = ArrHelper.generateRandomArr(30, 50);
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        long timeStart = System.nanoTime();
        Arrays.sort(arr);
        System.out.println(String.format("Время сортировки (sort): %d нс", System.nanoTime() - timeStart));
        ArrHelper.heapSort(arr2);
        System.out.println("Отсортированный первый массив: " + Arrays.toString(arr));
        System.out.println("Отсортированный второй массив: " + Arrays.toString(arr2));
    }
}
