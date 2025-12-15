package io.github.vadman1.collectionframework.lesson2;

import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(new LinkedList<>());

        taskManager.addTask("Задача №1");
        taskManager.addTask("Задача №2");
        taskManager.addTask("Задача №3");
        taskManager.addTask("Задача №4");

        taskManager.printTasks();

        taskManager.setTask(1, "Задача между №1 и №3");
        taskManager.printTasks();

        taskManager.removeTask(3);
        taskManager.printTasks();

        taskManager.removeTask("Задача №3");
        taskManager.printTasks();
    }
}