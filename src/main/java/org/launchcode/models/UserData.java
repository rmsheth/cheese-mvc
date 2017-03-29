package org.launchcode.models;

import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
public class UserData {

    static ArrayList<User> users = new ArrayList<>();

    // getAll
    public static ArrayList<User> getAll() {
        return users;
    }

    // add
    public static void add(User newUser) {
        users.add(newUser);
    }

    // remove
    public static void remove(int id) {
        User userToRemove = getById(id);
        users.remove(userToRemove);
    }

    // getById
    public static User getById(int id) {

        User foundUser = null;

        for (User user : users) {
            if (user.getUserId() == id) {
                foundUser = user;
            }
        }

        return foundUser;
    }

}
