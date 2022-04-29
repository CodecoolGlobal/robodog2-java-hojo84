package com.codecool.robodog2.model;

public class Pedigree {

    private long id;
    private long puppyId;
    private long momId;
    private long dadId;

    public Pedigree() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPuppyId() {
        return puppyId;
    }

    public void setPuppyId(long puppyId) {
        this.puppyId = puppyId;
    }

    public long getMomId() {
        return momId;
    }

    public void setMomId(long momId) {
        this.momId = momId;
    }

    public long getDadId() {
        return dadId;
    }

    public void setDadId(long dadId) {
        this.dadId = dadId;
    }
}
