package org.launchcode.models;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by LaunchCode
 */
public class User {

    @NotNull
    @Size(min = 5, max = 15)
    private String username;

    @NotNull
    @Size(min = 1, message = "password must not be empty")
    private String password;

    /**
     * The `@email` annotation validates for email address
     */
    @Email
    private String email;

    private int userId;
    private static int nextId = 1;

    public User(String username, String password, String email) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
        userId = nextId;
        nextId++;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
