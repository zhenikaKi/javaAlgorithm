package ru.kirea.lesson3;

public class OneLink<T> {
    private OneLinkItem<T> item;

    public OneLink() {
        item = null;
    }

    public void add(T newItem) {
        OneLinkItem<T> addedItem = new OneLinkItem<>(newItem);
        addedItem.setNextItem(item);
        item = addedItem;
    }

    public void remove() {
        item = item.getNextItem();
    }

    public void print() {
        OneLinkItem<T> curItem = item;
        while (curItem != null) {
            System.out.print(curItem.getItem() + "; ");
            curItem = curItem.getNextItem();
        }
        System.out.println();
    }
}
