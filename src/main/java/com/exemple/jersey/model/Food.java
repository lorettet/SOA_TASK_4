package com.exemple.jersey.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Food {
    private long id;
    private String name;
    private int calories;
    private FoodCategory category;

    public Food () {}

    public Food (FoodCategory category, String name, int calories) {
        this.calories = calories;
        this.name = name;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public FoodCategory getCategory() { return category; }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setCategory(FoodCategory category) { this.category = category; }
}
