package com.example.nba.payload;
import com.example.nba.model.User;

import javax.validation.constraints.*;

public class SignUpRequest {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}