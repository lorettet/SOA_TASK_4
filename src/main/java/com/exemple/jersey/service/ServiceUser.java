package com.exemple.jersey.service;

import com.exemple.jersey.Application;
import com.exemple.jersey.filter.UserFilterBean;
import com.exemple.jersey.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceUser {
    public Collection<User> getAllUsers() {
        return Application.getUserList();
    }

    public User addUser(String login, String password, String firstname, String lastname){
        return Application.addUser(new User(firstname, lastname, login, password));
    }

    public boolean deleteUser(long id) {
        User user = Application.getUser(id);
        if (user == null) return false;
        Application.deleteUser(user);
        return true;
    }


    public User getUser(long id) {
        return Application.getUser(id);
    }

    public User updateUser(User user) {
        if (Application.getUser(user.getId()) != null) {
            Application.modifyUser(user);
            return user;
        } else {
            return null;
        }
    }

    public List<User> getAllUsersForAge(long age) {
        Collection<User> userList = Application.getUserList();
        List<User> userListAge = new ArrayList<User>();
        for (User user : userList) {
            if (user.getAge() == age) {
                userListAge.add(user);
            }
        }
        return userListAge;
    }

    public List<User> getUsersGroup(UserFilterBean filterBean) {
        Collection<User> userList = Application.getUserList();
        List<User> usersGroup = new ArrayList<User>();
        for (User user : userList) {
            if (user.getAge() >= filterBean.getMinAge() &&
                    user.getWeight() >= filterBean.getMinWeight() &&
                    user.getSex() == filterBean.getSex()) {
                usersGroup.add(user);
            }
        }
        return usersGroup;
    }

    public User login(String login, String password) {
        return Application.getUser(login, password);
    }
}
