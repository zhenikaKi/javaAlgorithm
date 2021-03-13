package ru.kirea.lesson4;

import ru.kirea.lesson3.TwoLink;
import ru.kirea.lesson3.TwoLinkItem;

public class QueueTwoLink<T> {
    private TwoLink<T> twoLink;
    private TwoLinkItem<T> firstItem;

    public QueueTwoLink() {
        this.twoLink = new TwoLink<>();
    }

    //добавить элемент в очередь
    public void push(T item) {
        twoLink.add(item);
        if (firstItem == null) firstItem = twoLink.getItem();
    }

    //получить первый элемент в очереди
    public T pop() {
        T item = null;
        if (firstItem != null) {
            item = firstItem.getItem();
            twoLink.remove(firstItem, false);
            firstItem = firstItem.getNextItem();
        }
        return item;
    }

    //просто просмотреть первый элемент в очереди
    public T peek() {
        return firstItem == null ? null : firstItem.getItem();
    }

    public boolean isEmpty() {
        return !twoLink.hasNext();
    }

    //метод для проверки списка
    public void print() {
        twoLink.print();
    }
}
