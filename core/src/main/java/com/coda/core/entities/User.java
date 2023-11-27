package com.coda.core.entities;

import lombok.Data;

public class User {
    private int id;
    private String name;
    private Contact contact;
    private String password;

    public User(int id, String name, Contact contact, String password) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public String getPassword() {
        return password;
    }
}