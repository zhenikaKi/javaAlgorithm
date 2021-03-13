package ru.kirea.lesson8;

public class HashTable {
    private HashTableItem[] hashArr;
    private int arrSize;
    private HashTableItem nonItem;

    public HashTable(int arrSize) {
        this.arrSize = arrSize;
        hashArr = new HashTableItem[arrSize];
        nonItem = new HashTableItem(-1);
    }

    //вывести ключи
    public String displayValues() {
        StringBuilder result = new StringBuilder();
        for (int ind = 0; ind < arrSize; ind++) {
            result.append(hashArr[ind] == null ? "null" : hashArr[ind].getKey()).append(" ");
        }
        return result.toString();
    }

    private int hashFunc(int key) {
        return key % arrSize;
    }

    private int hashFuncDouble(int key) {
        return 5 - key % 5;
    }

    //добавление значения
    public void insert(HashTableItem item, boolean asDouble) {

        int key = item.getKey();
        int hash = hashFunc(key);
        int step = hashFuncDouble(key);
        while (hashArr[hash] != null && hashArr[hash].getKey() != -1) {
            if (asDouble) {
                hash += step;
            } else {
                ++hash;
            }
            hash %= arrSize;
        }
        hashArr[hash] = item;
    }

    //поиск нужного элемента
    public HashTableItem find(int key, boolean asDouble) {
        int hash = findHash(key, asDouble);
        return hash < 0 ? null : hashArr[hash];
    }

    //поиск позицииэлемента
    public int findHash(int key, boolean asDouble) {
        int hash = hashFunc(key);
        int step = hashFuncDouble(key);
        while (hashArr[hash] != null) {
            if (hashArr[hash].getKey() == key) {
                return hash;
            }
            if (asDouble) {
                hash += step;
            } else {
                ++hash;
            }
            hash %= arrSize;

        }
        return -1;
    }

    //удаление значения
    public HashTableItem delete(int key, boolean asDouble) {
        int hash = findHash(key, asDouble);
        if (hash != -1) {
            HashTableItem deletedItem = hashArr[hash];
            hashArr[hash] = nonItem;
            return deletedItem;
        }
        return null; //не нашли элемент для удаления
    }

    //увеличение таблицы
    private int getPrime(int min) {
        for (int ind = min+1; true; ind++) {
            if (isPrime(ind)) {
                return ind;
            }
        }
    }

    private boolean isPrime(int ind) {
        for (int ind2 = 2; (ind2*ind2 <= ind); ind2++) {
            if (ind % ind2 == 0) {
                return false;
            }
        }
        return true;
    }
}
