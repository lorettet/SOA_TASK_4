package com.exemple.jersey.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FoodLink {
    String link;
    String foodName;
    long foodId;

    public FoodLink() {
    }

    public FoodLink(String link, Food food) {
        this.link = link;
        this.foodName = food.getName();
        this.foodId = food.getId();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }
}
