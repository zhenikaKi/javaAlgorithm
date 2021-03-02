package ru.kirea.lesson4;

import java.util.LinkedList;

public class PriorityQueueMy<T> {
    private LinkedList<PriorityQueueItem<T>> queue;

    public PriorityQueueMy() {
        this.queue = new LinkedList<>();
    }

    //добавить элемент с приоритетом по умолчанию в приоритетную очередь
    public void push(T item) {
        add(item, PriorityQueueItem.DEFAULT_PRIOR);
    }

    //добавить элемент c определенным приоритетом в приоритетную очередь
    public void push(T item, int prior) {
        add(item, prior);
    }

    //добавить элемент в приоритетную очередь
    private void add(T item, int prior) {
        PriorityQueueItem<T> queueItem = new PriorityQueueItem<>(item, prior);
        if (queue.isEmpty()) { //добавление самого первого элемента
            queue.add(queueItem);
        } else {
            int position = getPosition(prior);
            queue.add(position, queueItem);
        }
    }

    //получить позицию в списке с опеределенным приоритетом
    private int getPosition(int prior) {
        if (queue.isEmpty()) return 0;

        int result = queue.size();
        while (result != 0 && prior < queue.get(result -1).getPrior()) {
            result--;
        }
        return result;
    }

    //получить первый элемент в очереди
    public T pop() {
        T result = null;
        if (!queue.isEmpty()) {
            result = queue.get(0).getItem();
            queue.remove(0);
        }
        return result;
    }

    //просто просмотреть первый элемент в очереди
    public T peek() {
        return queue.isEmpty() ? null : queue.get(0).getItem();
    }

    //просто просмотреть последний элемент в очереди
    public T peekLast() {
        return queue.isEmpty() ? null : queue.get(queue.size()-1).getItem();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
