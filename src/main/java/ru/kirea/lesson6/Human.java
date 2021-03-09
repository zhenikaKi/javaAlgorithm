package ru.kirea.lesson6;

import java.util.Objects;

public class Human implements Comparable<Human> {
    private String firstName;
    private String name;
    private String secondName;

    public Human(String firstName, String name) {
        this.firstName = firstName;
        this.name = name;
    }

    public Human(String firstName, String name, String secondName) {
        this.firstName = firstName;
        this.name = name;
        this.secondName = secondName;
    }

    @Override
    public int compareTo(Human h) {
        int result = this.firstName.compareTo(h.firstName); //сравниваем сперва по фамилии
        if (result == 0) result = this.name.compareTo(h.name); //затем по имени
        if (result == 0 && h.secondName != null) result = this.secondName.compareTo(h.secondName); //затем по отчеству
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(firstName, human.firstName) &&
                Objects.equals(name, human.name) &&
                Objects.equals(secondName, human.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, name, secondName);
    }

    @Override
    public String toString() {
        return firstName + " " + name + (secondName == null ? "" : " " + secondName);
    }

    public String getShortString() {
        return firstName + " " + name.substring(0, 1) + "." + (secondName == null ? "" : " " + secondName.substring(0, 1) + ".");
    }
}
