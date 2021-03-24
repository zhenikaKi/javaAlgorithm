package ru.kirea.lesson8;
/*
Задание 8.1
Приведите пример использование хеш-таблиц.
Задание 8.2
Приведите примеры ключей и коллизий.
Задание 8.3
Приведите примеры популярных и эффективных хеш-функций.
Задание 8.4
На основе данных массива из задания 2.3 реализуйте хеш-таблицу с помощью открытой адресации, а конкретнее метода линейного пробирования
Задание 8.5
Перестройте программный код задания 8.4 из алгоритма линейного пробирования в алгоритм двойного хеширования.
Сравните отличительные черты двух алгоритмов.
 */

import ru.kirea.lesson2.ArrHelper;

import java.util.Arrays;

public class Main8 {
    public static void main(String[] args) {
        /* Задание 8.1. карточки клиентов в больнице, расположенные на полках.
           Каждая полка обозначается буквами алфавита, карточки лежат на той полке,
           которая обозначена как первая буква фамилии клиента.
           Хеш таблица - весь стелаж с карточками, индекс хеш-таблицы - пронумированная ячейка.*/

        /* Задание 8.2. Из примера выше ключ - это ячейка с буквой "И".
           Коллизия - это карточки клиентов с фамилией "Иванов". С такой фамилией может быть много карточек клиента.*/

        /* Задание 8.3.
           Популярные хеш-функции: SHA-1, SHA256, SHA512.
           Эффективные хеш-функции: Keccak, BLAKE, Grøstl, Skein, JH/*/

        //Задание 8.4
        System.out.println("Задание 8.4");
        taskHashTable(10, false);

        //Задание 8.5
        System.out.println("Задание 8.5");
        taskHashTable(10, true);
    }

    private static void taskHashTable(int size, boolean asDouble) {
        int[] arr = ArrHelper.generateRandomArr(size, 100);
        HashTable hashTable = new HashTable(size);
        for (int value: arr) {
            hashTable.insert(new HashTableItem(value), asDouble);
        }
        System.out.println("Массив: " + Arrays.toString(arr));
        System.out.println("Хеш-таблица: " + hashTable.displayValues());

        System.out.println("Позиции в массиве и хеш-таблице:");
        for (int ind = 0; ind < arr.length; ind++) {
            System.out.println(arr[ind] + ": в массеве " + ind + ", в хеш-таблице " + hashTable.findHash(arr[ind], asDouble));
        }
    }
}
