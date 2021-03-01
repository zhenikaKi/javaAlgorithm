package ru.kirea.lesson3;

public class TwoLinkItem<T> {
    private T item;
    private TwoLinkItem<T> prevItem;
    private TwoLinkItem<T> nextItem;

    public TwoLinkItem(T item) {
        this.item = item;
    }

    public TwoLinkItem<T> getNextItem() {
        return nextItem;
    }

    public void setNextItem(TwoLinkItem<T> nextItem) {
        this.nextItem = nextItem;
    }

    public TwoLinkItem<T> getPrevItem() {
        return prevItem;
    }

    public void setPrevItem(TwoLinkItem<T> prevItem) {
        this.prevItem = prevItem;
    }

    public T getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "{" +
                (prevItem == null ? "null" : prevItem.getItem()) + " -> " +
                item + " -> " +
                (nextItem == null ? "null" : nextItem.getItem()) +
                "}";
    }
}
