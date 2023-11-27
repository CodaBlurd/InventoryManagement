package com.coda.core.entities;

import lombok.Data;
public class Contact {
    private int id;
    private String email;
    private String phone;

    public Contact(int id, String email, String phone) {
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
