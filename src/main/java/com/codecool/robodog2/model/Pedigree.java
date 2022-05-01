package com.codecool.robodog2.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedigree pedigree = (Pedigree) o;
        return puppyId == pedigree.puppyId && momId == pedigree.momId && dadId == pedigree.dadId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(puppyId, momId, dadId);
    }
}
