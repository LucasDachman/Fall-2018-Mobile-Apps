package com.lucasdachman.mission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Mission implements Serializable {

    private String name;
    private HashMap<String, Task> tasks;

    public Mission() {
        this.tasks = new HashMap<>();
        this.name = "Default";
    }

    public Mission(String name) {
       this.name = name;
       this.tasks = new HashMap<String, Task>();
    }

    public void addTask(String key, String name) {
       tasks.put(key, new Task(name));
    }

    public void addTask(String key, Task task) {
        tasks.put(key, task);
    }

    public ArrayList<Task> getTasksAsList() {
        return new ArrayList(tasks.values());
    }

    public Map<String, Task> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<String, Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTaskAt(int index) {
        return (Task) tasks.values().toArray()[index];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
