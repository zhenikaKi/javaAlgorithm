package ru.kirea.lesson7;

import java.util.LinkedList;
import java.util.Queue;

//задание 7.2
public class Graph<T> {
    private int maxVertex;
    private Vertex[] vertexList;
    private int[][] adjMax;
    private int size;
    private Stack stack;

    public Graph(int maxVertex) {
        stack = new Stack(maxVertex);
        this.maxVertex = maxVertex;
        vertexList = new Vertex[maxVertex];
        adjMax = new int[maxVertex][maxVertex];
        size = 0;

        for (int indI = 0; indI < maxVertex; indI++) {
            for (int indJ = 0; indJ < maxVertex; indJ++) {
                adjMax[indI][indJ] = 0;
            }
        }
    }

    //добавить элемент в граф
    public void add(T item) {
        vertexList[size++] = new Vertex<>(item);
    }

    //добавить грань между элементами
    public void addEdge(int start, int end) {
        adjMax[start][end] = 1;
        adjMax[end][start] = 1;
    }

    //показать элемент графа
    public void display(int index) {
        String vertex = (index < 0 || index > size) ? null : vertexList[index].getLabel().toString();
        System.out.println(vertex);
    }

    //задание 7.3 - обход дерева в глубину
    public void viewDeep() {
        long timeStart = System.nanoTime();
        if (size == 0) {
            System.out.println(String.format("Время выполнения задания 7.3 (обход в глубину): %d нс", System.nanoTime() - timeStart));
            return;
        }

        vertexList[0].setWasVisited(true);
        display(0);
        stack.push(0);
        while (!stack.isEmpty()) {
            int unvisited = getAdjUnvisited(stack.peek());
            if (unvisited == -1) {
                stack.pop();
            } else {
                vertexList[unvisited].setWasVisited(true);
                display(unvisited);
                stack.push(unvisited);
            }
        }

        for (int ind = 0; ind < size; ind++) {
            vertexList[ind].setWasVisited(false);
        }
        System.out.println(String.format("Время выполнения задания 7.3 (обход в глубину): %d нс", System.nanoTime() - timeStart));
    }

    private int getAdjUnvisited(int index) {
        for (int ind = 0; ind < size; ind++) {
            if (adjMax[index][ind] == 1 && !vertexList[ind].isWasVisited()) {
                return ind;
            }
        }

        return -1;
    }

    //задание 7.4 - обход дерева в ширину
    public void viewWidth() {
        long timeStart = System.nanoTime();
        if (size == 0) {
            System.out.println(String.format("Время выполнения задания 7.4 (обход в ширину): %d нс", System.nanoTime() - timeStart));
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        vertexList[0].setWasVisited(true);
        display(0);
        queue.add(0);
        int v2;
        while (!queue.isEmpty()) {
            int v1 = queue.remove();
            while ((v2 = getAdjUnvisited(v1)) != -1) {
                vertexList[v2].setWasVisited(true);
                display(v2);
                queue.add(v2);
            }
        }

        for (int ind = 0; ind < size; ind++) {
            vertexList[ind].setWasVisited(false);
        }
        System.out.println(String.format("Время выполнения задания 7.4 (обход в ширину): %d нс", System.nanoTime() - timeStart));

    }
}
