package io.github.vadman1.collectionframework.lesson2;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<String> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public TaskManager(List<String> tasks) {
        this.tasks = tasks;
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void setTask(int index, String task) {
        tasks.set(index, task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public void printTasks() {
        System.out.println(tasks);
    }
}