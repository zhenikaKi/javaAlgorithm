package ru.kirea.lesson6;

import com.sun.xml.internal.ws.util.StringUtils;

import java.awt.*;
import java.util.*;
import java.util.List;

//задание 6.2 шаблон дерева
public class Tree<T>  {
    private TreeNode<T> root;
    private Comparator<T> comparator;

    //для визуального вывода дерева
    private List<List<TreeNode<T>>> allItemsListTree;
    private TreeNodeViewable<T> treeNodeViewable;

    private Tree() {
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void setTreeNodeViewable(TreeNodeViewable<T> treeNodeViewable) {
        this.treeNodeViewable = treeNodeViewable;
    }

    //задание 6.3 вставка
    public void insert(T item) {
        long timeStart = System.nanoTime();
        TreeNode<T> newTreeNode = new TreeNode<>();
        newTreeNode.setItem(item);

        //самый первый элемент в делеве
        if (root == null) {
            root = newTreeNode;
        } else {
            //ищем место в дереве, куда можно вставить новыйэлемент
            TreeNode<T> current = root;
            TreeNode<T> parent;
            while (true) {
                parent = current; //запомним текущий элемент, мало ли у него не будет дочерних
                if (comparator.compare(item, current.getItem()) < 0) {
                    current = current.getLeftChild();
                    //дошли до самого "минимального" элемента в дереве, ниже элементов нет. Вставляем туда новый элемент
                    if (current == null) {
                        parent.setLeftChild(newTreeNode);
                        break;
                    }
                } else {
                    current = current.getRightChild();
                    //дошли до самого "максимального" элемента в дереве, ниже элементов нет. Вставляем туда новый элемент
                    if (current == null) {
                        parent.setRightChild(newTreeNode);
                        break;
                    }
                }
            }
        }
        System.out.println(String.format("Время вставки: %d нс", System.nanoTime() - timeStart));
    }

    //задание 6.3 поиск
    public T find(T findItem) {
        long timeStart = System.nanoTime();
        TreeNode<T> current = root;
        while (!current.getItem().equals(findItem)) {
            if (comparator.compare(findItem, current.getItem()) < 0) { // элемент для поиска "меньше" элемента в дереве
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }

            if (current == null) return null; //элемент для поиска не нашли в дереве
        }
        System.out.println(String.format("Время поиска: %d нс", System.nanoTime() - timeStart));
        return findItem;
    }

    //задание 6.4 симметричный обход дерева (от минимального к максимального)
    public void symmetricView() {
        long timeStart = System.nanoTime();
        symmetricView(root);
        System.out.println(String.format("Время симметричного обхода: %d нс", System.nanoTime() - timeStart));
    }
    private void symmetricView(TreeNode<T> rootNode) {
        if (rootNode == null) return;
        symmetricView(rootNode.getLeftChild());
        rootNode.display();
        symmetricView(rootNode.getRightChild());
    }

    //задание 6.4 прямой обход дерева (от верха к низу)
    public void forwardView() {
        long timeStart = System.nanoTime();
        forwardView(root);
        System.out.println(String.format("Время прямого обхода: %d нс", System.nanoTime() - timeStart));
    }
    private void forwardView(TreeNode<T> rootNode) {
        if (rootNode == null) return;
        rootNode.display();
        forwardView(rootNode.getLeftChild());
        forwardView(rootNode.getRightChild());
    }

    //задание 6.4 обратный обход дерева (от низа к верху)
    public void reverseView() {
        long timeStart = System.nanoTime();
        reverseView(root);
        System.out.println(String.format("Время обратного обхода: %d нс", System.nanoTime() - timeStart));
    }
    private void reverseView(TreeNode<T> rootNode) {
        if (rootNode == null) return;
        reverseView(rootNode.getLeftChild());
        reverseView(rootNode.getRightChild());
        rootNode.display();
    }

    //задание 6.4 минимальное значение
    public TreeNode<T> min() {
        long timeStart = System.nanoTime();
        TreeNode<T> min = minMax(true);
        System.out.println(String.format("Время поиска минимального: %d нс", System.nanoTime() - timeStart));
        return min;
    }

    //задание 6.4 максимальное значение
    public TreeNode<T> max() {
        long timeStart = System.nanoTime();
        TreeNode<T> max = minMax(false);
        System.out.println(String.format("Время поиска макимального: %d нс", System.nanoTime() - timeStart));
        return max;
    }

    private TreeNode<T> minMax(boolean isMin) {
        TreeNode<T> current = root;
        TreeNode<T> last = null;
        while (current != null) {
            last = current;
            current = isMin ? current.getLeftChild() : current.getRightChild();
        }
        return last;
    }

    //задание 6.5 удаление
    public boolean delete(T deletedItem) {
        long timeStart = System.nanoTime();
        TreeNode<T> deletedTreeItem = root;
        TreeNode<T> parent = root;
        boolean isLeftChild = true;

        //найдем родительский элемент у удаляемого элемента
        while (comparator.compare(deletedTreeItem.getItem(), deletedItem) != 0) {
            parent = deletedTreeItem;
            isLeftChild = comparator.compare(deletedItem, deletedTreeItem.getItem()) < 0;
            deletedTreeItem = isLeftChild ? deletedTreeItem.getLeftChild() : deletedTreeItem.getRightChild();
            if (deletedTreeItem == null) {
                System.out.println(String.format("Время удаления: %d нс", System.nanoTime() - timeStart));
                return false;
            }
        }

        //смещаем элементы в дереве
        if (deletedTreeItem.getLeftChild() == null && deletedTreeItem.getRightChild() != null) { //у удаляемого элемента нет дочерних элементов
            if (deletedTreeItem.equals(root)) {
                root = null;
            } else if (isLeftChild) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
        } else if (deletedTreeItem.getRightChild() == null) { //у удаляемого элемента нет дочернего большего элемента
            if (isLeftChild) {
                parent.setLeftChild(deletedTreeItem.getLeftChild());
            } else {
                parent.setRightChild(deletedTreeItem.getLeftChild());
            }
        } else if (deletedTreeItem.getLeftChild() == null) { //у удаляемого элемента нет дочернего меньшего элемента
            if (isLeftChild) {
                parent.setLeftChild(deletedTreeItem.getRightChild());
            } else {
                parent.setRightChild(deletedTreeItem.getRightChild());
            }
        } else { //у удаляемого элемента есть меньший и больший дочерние элементы
            TreeNode<T> successor = getSuccessor(deletedTreeItem);
            if (deletedTreeItem.equals(root)) {
                root = successor;
            } else if (isLeftChild) {
                parent.setLeftChild(successor);
            } else {
                parent.setRightChild(successor);
            }
            successor.setLeftChild(deletedTreeItem.getLeftChild());
        }

        System.out.println(String.format("Время удаления: %d нс", System.nanoTime() - timeStart));
        return true;
    }

    //найти приемника, который встанет вместо удаляемого объекта
    private TreeNode<T> getSuccessor(TreeNode<T> deletedTreeItem) {
        TreeNode<T> parent = deletedTreeItem;
        TreeNode<T> successor = deletedTreeItem;
        TreeNode<T> current = deletedTreeItem.getRightChild();

        while (current != null) {
            parent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (!successor.equals(deletedTreeItem.getRightChild())) {
            parent.setLeftChild(successor.getRightChild());
            successor.setRightChild(deletedTreeItem.getRightChild());
        }

        return successor;
    }


///////////////дополнительный вывод///////////////////////
    //задание 6.4 * визуальное дерево
    public void visualView() {
        allItemsListTree = new ArrayList<>();
        if (root != null) {
            TreeNode<T> node = root;
            allItemsListTree.add(Collections.singletonList(node));
            setItemOnLevels(root, 1, 1, 1);
        }

        //получим максимальные длины значения на каждом уровне дерева и проставляем пустые места в дереве как null
        int maxWord = 0;
        List<Integer> maxWordInLine = new ArrayList<>();
        for (int row0 = 0; row0 < allItemsListTree.size(); row0++) {
            List<TreeNode<T>> itemsTree = allItemsListTree.get(row0);
            if (itemsTree.isEmpty()) {
                allItemsListTree.remove(row0);
            } else {
                int cnt = 0;
                int itemInd;
                int countItem = getCountItem(row0 + 1);
                int maxWordLength = 0;
                for (itemInd = 0; itemInd < itemsTree.size(); itemInd++) {
                    TreeNode<T> treeView = itemsTree.get(itemInd);
                    T item = treeView.getItem();
                    String value = (treeNodeViewable != null ? treeNodeViewable.getValueView(item) : item.toString());
                    cnt++;
                    maxWordLength = Math.max(maxWordLength, value.length());
                }
                for (int ind = itemInd + 1; ind <= countItem; ind++) {
                    //items.add(null);
                    itemsTree.add(null);
                }
                maxWord = Math.max(maxWord, cnt);
                allItemsListTree.set(row0, itemsTree);
                maxWordInLine.add(row0, maxWordLength);
            }
        }

        //делаем смещение элементов
        for (int row = 0; row < allItemsListTree.size(); row++) {
            if (row <  allItemsListTree.size()-1) {
                List<TreeNode<T>> nextRow = allItemsListTree.get(row+1);
                for (int itemIndex = allItemsListTree.get(row).size() - 1; itemIndex >= 0; itemIndex--) {
                    TreeNode<T> itemRow = allItemsListTree.get(row).get(itemIndex);
                    if (itemRow != null) {
                        int childR = (itemIndex + 1) * 2 - 1;
                        int childL = childR - 1;
                        for (int itemIndex2 = nextRow.size() - 1; itemIndex2 >= 0; itemIndex2--) {
                            TreeNode<T> itemRowNext = nextRow.get(itemIndex2);
                            if (itemRowNext != null) {
                                //нашли правый дочерний элемент, перемеаем его в "нужную" структуру дерева
                                if (itemRow.getRightChild() != null && itemRow.getRightChild().getTreeIndex() == itemRowNext.getTreeIndex() && childR != itemIndex2) {
                                    allItemsListTree.get(row + 1).set(childR, itemRowNext);
                                    allItemsListTree.get(row + 1).set(itemIndex2, null);
                                }

                                //нашли левый дочерний элемент, перемеаем его в "нужную" структуру дерева
                                if (itemRow.getLeftChild() != null && itemRow.getLeftChild().getTreeIndex() == itemRowNext.getTreeIndex()) {
                                    if (childL != itemIndex2) {
                                        allItemsListTree.get(row + 1).set(childL, itemRowNext);
                                        allItemsListTree.get(row + 1).set(itemIndex2, null);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        //формируем таблицу для вывода дерева
        String[][] printArr = initTablePrint(maxWordInLine, getCountItem(allItemsListTree.size()), allItemsListTree.size());

        //заполняем элементы дерева в таблицу для вывода
        for (int row = 0; row < allItemsListTree.size(); row++) {
            List<TreeNode<T>> itemsInRow = allItemsListTree.get(row);
            Point tmp = getItemStep(row, allItemsListTree.size());
            int start = tmp.x;
            int step = tmp.y;
            int maxWordColumn = maxWordInLine.get(row);

            for (int ind = 0; ind < itemsInRow.size(); ind++) {
                int x = row;
                int y = (ind == 0) ? start : ind * step + start;
                TreeNode<T> treeView = itemsInRow.get(ind);
                String value = null;
                if (treeView != null) {
                    T item = treeView.getItem();
                    value = (treeNodeViewable != null ? treeNodeViewable.getValueView(item) : item.toString());
                    if (maxWordColumn - value.length() > 0) {
                        value += String.format("%" + (maxWordColumn - value.length()) + "s", "").replace(" ", "-");
                    }
                }

                if (value == null) {
                    value = "•- " + String.format("%" + (maxWordColumn +2) + "s", "").replace(" ", "-");
                } else {
                    value = "•- " + value + " -" ;
                }
                printArr[y][x] = value;
            }
        }

        for (int row = 0; row < printArr.length; row++) {
            for (int column = 0; column < printArr[row].length; column++) {
                System.out.print(printArr[row][column]);
            }
            System.out.println();
        }
    }
    private void setItemOnLevels(TreeNode<T> rootNode, int level, Integer rootL, Integer rootR) {
        if (rootNode == null) {
            return;
        }
        if (level > allItemsListTree.size()-1) {
            allItemsListTree.add(new ArrayList<>());
        }
        List<TreeNode<T>> itemsOnLevelTree = allItemsListTree.get(level);

        TreeNode<T> leftNode = null;
        if (rootNode.getLeftChild() != null) {
            leftNode = rootNode.getLeftChild();
            itemsOnLevelTree.add(leftNode);
        }
        TreeNode<T> rightNode = null;
        if (rootNode.getRightChild() != null) {
            rightNode = rootNode.getRightChild();
            itemsOnLevelTree.add(rightNode);
        }
        allItemsListTree.set(level, itemsOnLevelTree);
        if (leftNode != null) setItemOnLevels(leftNode, level+1, leftNode.getTreeIndex(), null);
        if (rightNode != null) setItemOnLevels(rightNode, level+1, null, rightNode.getTreeIndex());
    }

    //получить максимально возможно число элементов на конкретном уровне дерева
    private int getCountItem(int row) {
        if (row == 1) return 1;
        return 2 * getCountItem(row -1);
    }

    //получить первую позицию для отображение элемента и шаг до следующей позиции
    private Point getItemStep(int currentColumn, int maxColumn) {
        int maxRow = getCountItem(maxColumn +1) - 2; //максимально число строк для отображения
        int delta = maxColumn - currentColumn - 1;
        int start = maxRow - getCountItem(delta +1) + 1;
        int step = - getCountItem(delta +2);
        return new Point(start, step);
    }

    //проинизиализировать массив, содержащий таблицу для вывода дерева
    private String[][] initTablePrint(List<Integer> maxWordInLine, int cntRow, int cntColumn) {
        int allCntRow = cntRow*2 - 1;
        String[][] result = new String[allCntRow][cntColumn];

        for (int row = 0; row < allCntRow; row++) {
            for (int column = 0; column < cntColumn; column++) {
                int maxWord = maxWordInLine.get(column);
                Point tmp = getItemStep(column, cntColumn);
                int start = tmp.x;
                int step = tmp.y;
                String value;
                if (row == start || row % step == start % step) {
                    value = ""; //тут будет значение дерева
                } else if (row > start || row < allCntRow - start) {
                    value = String.format("%" + (maxWord +5) + "s", "");
                } else {
                    value = "|" + String.format("%" + (maxWord + 4) + "s", "");
                }
                result[row][column] = value;
            }
        }

        for (int column = 0; column < cntColumn; column++) {
            int cntItem = 0;
            int maxWord = maxWordInLine.get(column);
            for (int row = 0; row < allCntRow; row++) {
                if  (result[row][column].equals("")) {
                    cntItem++;
                }
                if (result[row][column].contains("|") && cntItem % 2 == 0) {
                    result[row][column] = String.format("%" + (maxWord +5) + "s", "");
                }
            }
        }

        return result;
    }
}
