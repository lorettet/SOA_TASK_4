package com.exemple.jersey.model;

import com.exemple.jersey.Application;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Exercise {
    Long id;
    String name;
    String description;
    ExerciseCategory category;
    User author;
    List<FoodLink> foodLinks;

    public Exercise(){
        //foodLinks = new ArrayList<FoodLink>();
    }

    public Exercise(String name, String description, ExerciseCategory category, User author) {
        foodLinks = new ArrayList<FoodLink>();
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FoodLink> getFoodLinks() {
        return foodLinks;
    }

    public void setFoodLinks(List<FoodLink> foodLinks) {
        this.foodLinks = foodLinks;
    }

    public void addFood(String uri, Food food) {
        this.foodLinks.add(new FoodLink(uri, food));
    }

    public FoodLink obtainFoodLinkFromID(long foodId) {
        for(FoodLink l : this.foodLinks)
        {
            if(l.getFoodId() == foodId) return l;
        }
        return null;
    }

    public void deleteFood(FoodLink foodLink) {
        foodLinks.remove(foodLink);
    }
}
