package com.exemple.jersey.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Exercise {
    long id;
    String name;

    public Exercise(){}

    public Exercise(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
