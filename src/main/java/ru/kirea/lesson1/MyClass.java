package ru.kirea.lesson1;

public class MyClass {
    private int value;

    public MyClass(int value) {
        this.value = value;
    }

    public void printValue() {
        System.out.print(value);
    }

    public boolean equals(MyClass myClass) {
        return this.value == myClass.value;
    }
}
