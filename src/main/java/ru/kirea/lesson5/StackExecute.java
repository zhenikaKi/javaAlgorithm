package ru.kirea.lesson5;

import java.util.ArrayList;
import java.util.List;

public class StackExecute {
    private static StackExecute instance;
    private List<String> executed;

    private StackExecute() {
        executed = new ArrayList<>();
    }

    private static void init() {
        if (instance == null) instance = new StackExecute();
    }

    //добавить имя метода в стек вызова
    public static void add() {
        init();
        //считываем имя метода, его id = 2 (id 1 - текущий add, 0 - getStackTrace)
        instance.executed.add(Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    public static void add(String... paramValues) {
        init();
        StringBuilder params = new StringBuilder();
        if (paramValues != null && paramValues.length > 0) {
            for (String value: paramValues) {
                params.append(params.length() > 0 ? ", " : "(").append(value);
            }
            params.append(")");
        }
        //считываем имя метода, его id = 2 (id 1 - текущий add, 0 - getStackTrace)
        instance.executed.add(Thread.currentThread().getStackTrace()[2].getMethodName() + params.toString());
    }

    //распечатать список вызовов
    public static void printStack() {
        init();
        for (int ind = 0; ind < instance.executed.size(); ind++) {
            System.out.println(String.format("[%d] %s", ind, instance.executed.get(ind)));
        }
    }

    //очистить стек вызовов
    public static void clearStack() {
        init();
        instance.executed = new ArrayList<>();
    }
}
