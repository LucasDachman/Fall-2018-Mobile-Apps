package com.lucasdachman.mission;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Mission extends Orderable implements Serializable {

    private String key;
    private String name;
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

    @Exclude
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
