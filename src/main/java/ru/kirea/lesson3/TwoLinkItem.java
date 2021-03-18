package ru.kirea.lesson3;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoLinkItem<?> that = (TwoLinkItem<?>) o;
        return Objects.equals(item, that.item) &&
                Objects.equals(prevItem, that.prevItem) &&
                Objects.equals(nextItem, that.nextItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, prevItem, nextItem);
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
