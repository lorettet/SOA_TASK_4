package com.exemple.jersey.service;

import com.exemple.jersey.Application;
import com.exemple.jersey.model.User;

public class ServiceUser {
    public User addUser(String login, String password, String firstname, String lastname){
        return Application.addUser(new User(firstname, lastname, login, password));
    }

    public User login(String login, String password) {
        return Application.getUser(login,password);
    }
}
