package com.lucasdachman.mission;

class Task {
    private String name;
    private String description;

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
}
