package ru.kirea.lesson4;
/*
Задание 4.1
На основе данных объектного списка из задания 3.4 реализуйте простой стек и его базовые методы.
Оцените время выполнения операций с помощью базового метода System.nanoTime().
Задание 4.2
На основе данных объектного списка из задания 3.4 реализуйте простую очередь и его базовые методы.
Реализуйте вспомогательные методы.
Оцените время выполнения операций с помощью базового метода System.nanoTime().
Задание 4.3
На основе данных объектного списка из задания 3.4 реализуйте простой дек и его базовые методы.
Оцените время выполнения операций с помощью базового метода System.nanoTime().
Задание 4.4
Реализуйте приоритетную очередь на основе ссылочных типов данных, например, integer.
Оцените время выполнения операций с помощью базового метода System.nanoTime().
Задание 4.5
На основе данных из задания 4.1 и 4.2, реализуйте стек и очередь на базе связанного списка.
Оцените время выполнения операций с помощью базового метода System.nanoTime().
*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main4 {
    public static void main(String[] args) {
        task_4_1(); //задание 4.1 на основе 3.4 (класс TwoLink)
        task_4_2(); //задание 4.2 на основе 3.4 (класс TwoLink)
        task_4_3(); //задание 4.3 на основе 3.4 (класс TwoLink)
        task_4_4(); //задание 4.4
        task_4_5(); //задание 4.5
    }

    //реализация стека
    private static void task_4_1() {
        long timeStart = System.nanoTime();
        StackTwoLink<String> twoLink = new StackTwoLink<>();
        twoLink.push("Катя");
        twoLink.push("Даша");
        twoLink.push("Маша");
        twoLink.push("Оля");
        System.out.print("Заполненный стек: "); //Оля; Маша; Даша; Катя;
        twoLink.print();
        System.out.print("Чтение стека: "); //Оля; Маша; Даша; Катя;
        System.out.print(twoLink.pop() + "; ");
        System.out.print(twoLink.pop() + "; ");
        System.out.print(twoLink.pop() + "; ");
        System.out.print(twoLink.pop() + "; ");
        System.out.println();
        System.out.println(String.format("Время выполнения задания 4.1: %d нс\n", System.nanoTime() - timeStart));
    }

    //реализация очереди
    private static void task_4_2() {
        long timeStart = System.nanoTime();
        QueueTwoLink<String> twoLink = new QueueTwoLink<>();
        twoLink.push("Катя");
        twoLink.push("Даша");
        twoLink.push("Маша");
        twoLink.push("Катя");
        twoLink.push("Оля");
        System.out.print("Заполненная очередь: "); //Оля; Катя; Маша; Даша; Катя;
        twoLink.print();
        System.out.println("Первый в очереди: " + twoLink.peek()); //Катя
        System.out.print("Чтение очереди: "); //Катя; Даша; Маша; Катя; Оля;
        System.out.print(twoLink.pop() + "; ");
        System.out.print(twoLink.pop() + "; ");
        System.out.print(twoLink.pop() + "; ");
        System.out.print(twoLink.pop() + "; ");
        System.out.print(twoLink.pop() + "; ");
        System.out.println();
        System.out.println(String.format("Время выполнения задания 4.2: %d нс\n", System.nanoTime() - timeStart));
    }

    //реализация дека
    private static void task_4_3() {
        long timeStart = System.nanoTime();
        DequeTwoLink<String> twoLink = new DequeTwoLink<>();
        twoLink.pushBack("Катя");
        twoLink.pushBack("Даша");
        twoLink.pushFront("Маша");
        twoLink.pushFront("Оля");
        twoLink.pushBack("Настя");
        System.out.print("Заполненный дек: "); //Настя; Даша; Катя; Маша; Оля;
        twoLink.print();
        System.out.println("Первый в деке: " + twoLink.peekFront()); //Оля
        System.out.println("Последний в деке: " + twoLink.peekBack()); //Настя
        System.out.print("Чтение дека с начала: "); ///Оля; Маша; Даша;
        System.out.print(twoLink.popFront() + "; ");
        System.out.print(twoLink.popFront() + "; ");
        System.out.println(twoLink.popFront() + "; ");
        System.out.print("Чтение дека с конца: "); ///Настя; Даша;
        System.out.print(twoLink.popBack() + "; ");
        System.out.print(twoLink.popBack() + "; ");
        System.out.println();
        System.out.println(String.format("Время выполнения задания 4.3: %d нс\n", System.nanoTime() - timeStart));
    }

    //реализация приоритетной очереди
    private static void task_4_4() {
        long timeStart = System.nanoTime();
        PriorityQueueMy<String> priorityQueue = new PriorityQueueMy<>();
        priorityQueue.push("Катя");
        priorityQueue.push("Даша", 20);
        priorityQueue.push("Маша", 15);
        priorityQueue.push("Оля");
        priorityQueue.push("Рита", 15);
        priorityQueue.push("Настя", 101);
        System.out.println("Заполненная приоритетная очередь: " + priorityQueue.toString()); //Маша; Рита; Даша; Катя; Оля; Настя;
        System.out.println("Первый в приоритетной очереди: " + priorityQueue.peek()); //Маша
        System.out.println("Последний в приоритетной очереди: " + priorityQueue.peekLast()); //Настя
        System.out.print("Чтение приоритетной очереди: "); ///Маша; Рита; Даша; Катя; Оля;
        System.out.print(priorityQueue.pop() + "; ");
        System.out.print(priorityQueue.pop() + "; ");
        System.out.print(priorityQueue.pop() + "; ");
        System.out.print(priorityQueue.pop() + "; ");
        System.out.print(priorityQueue.pop() + "; ");
        System.out.println();
        System.out.println(String.format("Время выполнения задания 4.4: %d нс\n", System.nanoTime() - timeStart));
    }

    //реализация стека и очереди на основе данных из задания 4.1 и 4.2 на базе связанного списка
    //непонятное конечно задание, его уже сделал в 4.1. и 4.2 (и стек, и очередь на базе связанного списка), ну да и ладно
    private static void task_4_5() {
        long timeStart = System.nanoTime();
        StackTwoLink<String> stackTwoLink = new StackTwoLink<>();
        stackTwoLink.push("Катя");
        stackTwoLink.push("Даша");
        stackTwoLink.push("Маша");
        stackTwoLink.push("Оля");
        System.out.print("Заполненный стек: "); //Оля; Маша; Даша; Катя;
        stackTwoLink.print();

        Stack<String> stack = new Stack<>();
        while (!stackTwoLink.isEmpty()) {
            stack.push(stackTwoLink.pop());
        }
        System.out.print("Заполненный стек Stack: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "; ");
        }
        System.out.println();

        QueueTwoLink<String> QueueTwoLink = new QueueTwoLink<>();
        QueueTwoLink.push("Люда");
        QueueTwoLink.push("Настя");
        QueueTwoLink.push("Рита");
        System.out.print("Заполненная очередь: "); //Рита; Настя; Люда;
        QueueTwoLink.print();

        Queue<String> queue = new LinkedList<>();
        while (!QueueTwoLink.isEmpty()) {
            queue.add(QueueTwoLink.pop());
        }
        System.out.print("Заполненная очередь Queue: ");
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + "; ");
        }

        System.out.println();
        System.out.println(String.format("Время выполнения задания 4.5: %d нс\n", System.nanoTime() - timeStart));
    }
}
