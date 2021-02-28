package com.br.igorsily.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testeData() {
        User user = new User();
        assertNotNull(user.getCreatedAt());
    }

}