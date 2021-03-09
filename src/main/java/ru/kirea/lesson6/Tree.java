package ru.kirea.lesson6;

import javafx.scene.control.TreeView;

import java.util.*;

//задание 6.2 шаблон дерева
public class Tree<T extends TreeNodeViewable>  {
    private TreeNode<T> root;
    private Comparator<T> comparator;

    //для визуального вывода дерева
    //private List<List<TreeView>> allItemsList;
    private List<List<TreeNode<T>>> allItemsListTree;

    private Tree() {
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
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

}
