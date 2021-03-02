package ru.kirea.lesson4;

import ru.kirea.lesson3.TwoLink;

public class StackTwoLink<T> {
    private TwoLink<T> twoLink;

    public StackTwoLink() {
        this.twoLink = new TwoLink<>();
    }

    //добавить элемент в стек
    public void push(T item) {
        twoLink.add(item);
    }

    //получить элемент из стека
    public T pop() {
        T item = twoLink.hasNext() ? twoLink.next() : null;
        if (item != null) twoLink.remove();
        return item;
    }

    public boolean isEmpty() {
        return !twoLink.hasNext();
    }

    //метод для проверки списка
    public void print() {
        twoLink.print();
    }
}
