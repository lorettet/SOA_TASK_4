package com.exemple.jersey.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Exercise {
    Long id;
    String name;
    ExerciseCategory category;

    public Exercise(){}

    public Exercise(String name, ExerciseCategory cat) {
        this.name = name;
        this.category = cat;
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
}
