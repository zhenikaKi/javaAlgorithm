package ru.kirea.lesson4;

public class PriorityQueueItem<T> {
    public static final int DEFAULT_PRIOR = 100; //приоритет в очереди по умолчанию
    private int prior;
    private T item;

    public PriorityQueueItem(T item, int prior) {
        this.item = item;
        this.prior = prior;
    }

    public int getPrior() {
        return prior;
    }

    public T getItem() {
        return item;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
