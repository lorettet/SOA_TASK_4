package com.exemple.jersey.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.security.Principal;

@XmlRootElement
public class User implements Principal {
    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private long id;
    private long age;
    private int weight;
    private UserSex sex;
    private Role role;

    public User(String firstname, String lastname, long age, int weight, UserSex sex, String login, String password, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String firstname, String lastname, String login, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public User(){}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public UserSex getSex() {
        return sex;
    }

    public void setSex(UserSex sex) {
        this.sex = sex;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getName() {
        return this.firstname + " " + this.lastname;
    }
}
