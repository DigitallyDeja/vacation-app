package com.example.d424vacationplanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class LoginTest {

    @Test
    public void loginFailsWhenPasswordEmpty() {
        String username = "testuser";
        String password = "";

        boolean loginSuccess = validateLogin(username, password);
        assertFalse(loginSuccess);
    }

    private boolean validateLogin(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }
}

