package com.coda.core.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    User user;


    @BeforeEach
    void setUp() {
        user = new User(2, "chris", new Contact(1,
                "email@test.com", "09089777"),
                "password");
    }

    // == Constructor Tests ==

    @Test
    void testConstructorWithValidValues() {
        //== constructor tests ==
        assertEquals(2, user.getId());
        assertEquals("chris", user.getName());
        assertEquals("password", user.getPassword());
        assertEquals(1, user.getContact().getId());

    }

}