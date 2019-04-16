package com.lucasdachman.mission;

import java.util.ArrayList;

class Mission {

    private String name;
    private ArrayList<Task> tasks;

    public Mission(String name) {
       this.name = name;
       this.tasks = new ArrayList<Task>();
    }

    public void addTask(String name) {
       tasks.add(new Task(name));
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
