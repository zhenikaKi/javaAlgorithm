package ru.kirea.lesson7;

public class Stack {
    private int[] stackArr;
    private  int top;

    public Stack(int maxSize) {
        stackArr = new int[maxSize];
        top = -1;
    }

    public void push(int index) {
        stackArr[++top] = index;
    }

    public int pop() {
        return stackArr[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int peek() {
        return stackArr[top];
    }
}
