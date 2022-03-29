package com.example.InvoiceCollector.model;

public class Role {
    private long id;
    private long name;

    // constructor without ID
    public Role(long name) {
        this.name = name;
    }

    // constructor with ID
    public Role(long id, long name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getName() {
        return name;
    }

    public void setName(long name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
