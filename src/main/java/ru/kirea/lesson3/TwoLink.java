package ru.kirea.lesson3;

import java.util.Iterator;
import java.util.List;

public class TwoLink<T> implements Iterator<T> {
    private TwoLinkItem<T> item;
    private boolean iterStart = false;

    public TwoLink() {
        item = null;
    }

    public TwoLink(List<T> items) {
        item = null;
        for (T itemValue: items)
            add(itemValue);
    }

    @Override
    public void remove() {
        remove(item);
    }

    @Override
    public boolean hasNext() {
        return (!iterStart && item != null) || (iterStart && item != null && item.getPrevItem() != null);
    }

    @Override
    public T next() {
        T result = null;
        if (item != null) {
            if (iterStart && item.getPrevItem() != null) {
                item = item.getPrevItem();
            }
            iterStart = true;
            result = item.getItem();
        }
        return result;
    }

    public void add(T newItem) {
        TwoLinkItem<T> addedItem = new TwoLinkItem<>(newItem);
        if (item != null) {
            item.setNextItem(addedItem);
            addedItem.setPrevItem(item);
        }
        item = addedItem;
    }

    public TwoLinkItem<T> addFirst(T newItem) {
        TwoLinkItem<T> firstItem = item;
        TwoLinkItem<T> prevItem = firstItem != null ? firstItem.getPrevItem() : null;
        while (prevItem != null) {
            firstItem = prevItem;
            prevItem = firstItem.getPrevItem();
        }

        TwoLinkItem<T> addedItem = new TwoLinkItem<>(newItem);
        if (firstItem != null) {
            addedItem.setNextItem(firstItem);
            firstItem.setPrevItem(addedItem);
        } else { //добавляется самый первый элемент
            item.setNextItem(addedItem);
            addedItem.setPrevItem(item);
            item = addedItem;
        }

        return addedItem;
    }

    //найти элемент
    public TwoLinkItem<T> findItem(T findItem) {
        TwoLinkItem<T> curItem = item;
        TwoLinkItem<T> result = null;
        while (curItem != null) {
            if (findItem.equals(curItem.getItem())) {
                result = curItem;
                break;
            } else if (curItem.getNextItem() != null && findItem.equals(curItem.getNextItem().getItem())) {
                result = curItem.getNextItem();
                break;
            };
            curItem = curItem.getPrevItem();
        }
        return result;
    }

    //найти элемент с учетом ссылки на предыдуий и следующий элементы
    public TwoLinkItem<T> findItem(TwoLinkItem<T> findItem) {
        TwoLinkItem<T> curItem = item;
        TwoLinkItem<T> result = null;
        while (curItem != null) {
            if (findItem.equals(curItem)) {
                result = curItem;
                break;
            } else if (curItem.getNextItem() != null && findItem.equals(curItem.getNextItem())) {
                result = curItem.getNextItem();
                break;
            };
            curItem = curItem.getPrevItem();
        }
        return result;
    }

    public void remove(TwoLinkItem<T> findItem) {
        remove(findItem, true);
    }
    public void remove(TwoLinkItem<T> findItem, boolean noCheckPrevAndNextItem) {
        TwoLinkItem<T> deletedItem = noCheckPrevAndNextItem ? findItem(findItem.getItem()) : findItem(findItem);
        if (deletedItem != null) {
            TwoLinkItem<T> prev = deletedItem.getPrevItem();
            TwoLinkItem<T> next = deletedItem.getNextItem();
            if (prev != null) prev.setNextItem(next);
            if (next != null) next.setPrevItem(prev);

            //если нет предыдущего и следующего элемента, значит удаляется текущий
            if (prev == null && next == null && deletedItem.equals(item)) item = null;
        }
    }

    public void print() {
        TwoLinkItem<T> curItem = item;
        while (curItem != null) {
            System.out.print(curItem.getItem() + "; ");
            curItem = curItem.getPrevItem();
        }
        System.out.println();
    }

    //задание 3.5
    public Iterator<T> iterator() {
        return this;
    }

    public TwoLinkItem<T> getItem() {
        return item;
    }
}
