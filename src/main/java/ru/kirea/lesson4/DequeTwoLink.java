package ru.kirea.lesson4;

import ru.kirea.lesson3.TwoLink;
import ru.kirea.lesson3.TwoLinkItem;

public class DequeTwoLink<T> {
    private TwoLink<T> twoLink;
    private TwoLinkItem<T> firstItem;
    private TwoLinkItem<T> lastItem;

    public DequeTwoLink() {
        this.twoLink = new TwoLink<>();
    }

    //добавить элемент в начало дека
    public void pushFront(T item) {
        firstItem = twoLink.addFirst(item);
        if (lastItem == null) lastItem = twoLink.getItem();
    }

    //добавить элемент в конец дека
    public void pushBack(T item) {
        twoLink.add(item);
        if (firstItem == null) firstItem = twoLink.getItem();
        lastItem = twoLink.getItem();
    }

    //получить первый элемент в деке
    public T popFront() {
        T item = null;
        if (firstItem != null) {
            item = firstItem.getItem();
            twoLink.remove(firstItem);
            firstItem = firstItem.getNextItem();
        }
        return item;
    }

    //получить последний элемент в деке
    public T popBack() {
        T item = null;
        if (lastItem != null) {
            item = lastItem.getItem();
            twoLink.remove(lastItem, false);
            lastItem = lastItem.getPrevItem();
        }
        return item;
    }

    //просто просмотреть первый элемент в деке
    public T peekFront() {
        return firstItem == null ? null : firstItem.getItem();
    }

    //просто просмотреть последний элемент в деке
    public T peekBack() {
        return lastItem == null ? null : lastItem.getItem();
    }

    //метод для проверки списка
    public void print() {
        twoLink.print();
    }
}
