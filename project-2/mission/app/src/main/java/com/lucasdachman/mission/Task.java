package com.lucasdachman.mission;

import java.io.Serializable;

class Task extends Orderable implements Serializable {
    private String key;
    private String name;
    private String description;

    public Task() {
        this.name = "Default";
        this.description = "Default";
    }

    public Task(String name) {
        this.name = name;
        this.description = "This is a descriiption of this task blah blah blah here is the stuff to do doit yeah";
    }


    public Task(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
