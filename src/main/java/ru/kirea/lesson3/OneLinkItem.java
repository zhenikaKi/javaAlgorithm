package ru.kirea.lesson3;

public class OneLinkItem<T> {
    private T item;
    private OneLinkItem<T> nextItem;

    public OneLinkItem(T item) {
        this.item = item;
    }

    public OneLinkItem<T> getNextItem() {
        return nextItem;
    }

    public void setNextItem(OneLinkItem<T> nextItem) {
        this.nextItem = nextItem;
    }

    public T getItem() {
        return item;
    }
}
