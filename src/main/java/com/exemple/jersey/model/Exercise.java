package com.exemple.jersey.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Exercise {
    Long id;
    String name;
    ExerciseCategory category;
    User author;

    public Exercise(){}

    public Exercise(String name, ExerciseCategory category, User author) {
        this.name = name;
        this.category = category;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExerciseCategory getCategory() {
        return category;
    }

    public void setCategory(ExerciseCategory category) {
        this.category = category;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
