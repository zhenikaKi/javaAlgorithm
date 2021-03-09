package ru.kirea.lesson6;

//задание 6.2 узел дерева
public class TreeNode<T> {
    private static int counter = 0;
    private T item;
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;
    private int treeIndex;

    public TreeNode() {
        treeIndex = ++counter;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public int getTreeIndex() {
        return treeIndex;
    }

    //задание 6.4 отображение дерева
    public void display() {
        System.out.println(item.toString() + "{" + treeIndex + "}");
    }
}
