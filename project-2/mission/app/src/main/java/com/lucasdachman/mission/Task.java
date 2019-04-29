package com.lucasdachman.mission;

import java.io.Serializable;

class Task implements Serializable, Comparable<Task> {
    private String key;
    private String name;
    private String description;
    private float order;

    public Task() {
        this.name = "Default";
        this.description = "Default";
    }

    public Task(String name) {
        this.name = name;
        this.description = "This is a descriiption of this task blah blah blah here is the stuff to do doit yeah";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public int compareTo(Task o) {
        return this.getOrder() > o.getOrder() ? 1
                : this.getOrder() < o.getOrder() ? -1
                : 0;
    }
}
