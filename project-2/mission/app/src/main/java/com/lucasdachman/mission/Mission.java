package com.lucasdachman.mission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Mission implements Serializable, Comparable<Mission> {

    private String key;
    private String name;
    private float order;
    private HashMap<String, Task> tasks = new HashMap<String, Task>();

    public Mission() {
        this.name = "Default";
    }

    public Mission(String name) {
        this.name = name;
    }

    public void addTask(String key, Task task) {
        tasks.put(key, task);
    }

    public ArrayList<Task> getTasksAsList() {
        ArrayList<Task> sorted = new ArrayList<>(tasks.values());
        Collections.sort(sorted);
        return sorted;
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

    public float getOrder() {
        return order;
    }

    public void setOrder(float order) {
        this.order = order;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int compareTo(Mission o) {
        return this.getOrder() > o.getOrder() ? 1
                : this.getOrder() < o.getOrder() ? -1
                : 0;
    }
}
