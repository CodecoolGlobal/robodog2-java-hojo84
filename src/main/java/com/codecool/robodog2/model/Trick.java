package com.codecool.robodog2.model;

public class Trick {

    private long id;
    private String name;

    public Trick(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Trick() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
