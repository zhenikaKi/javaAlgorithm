package ru.kirea.lesson7;

public class Vertex<T> {
    private T label;
    private boolean wasVisited;

    public Vertex(T label) {
        this.label = label;
        this.wasVisited = false;
    }

    public T getLabel() {
        return label;
    }

    public boolean isWasVisited() {
        return wasVisited;
    }

    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }
}
