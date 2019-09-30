package com.exemple.jersey.service;

import com.exemple.jersey.Application;
import com.exemple.jersey.model.Food;
import com.exemple.jersey.model.FoodCategory;

import java.util.ArrayList;
import java.util.Collection;

public class ServiceFood {

    public Collection<Food> getAllFood() {
        return Application.getFoodList();
    }

    public Food addFood(Food food) {
        return Application.addFood(food);
    }

    public boolean deleteFood(long id) {
        Food food = Application.getFood(id);
        if(food == null) return false;
        Application.deleteFood(food);
        return true;
    }

    public Food getFood(long id) {
        return Application.getFood(id);
    }

    public Food getFoodByName(String name) {
        return Application.getFoodByName(name);
    }

    public Food updateExercise(Food food) {
        return Application.updateFood(food);
    }

    public Collection<Food> getAllFood(FoodCategory foodCategory) {
        Collection<Food> allFood = Application.getFoodList();
        ArrayList<Food> filteredFood = new ArrayList<Food>();
        for(Food food : allFood){
            if(food.getCategory() == foodCategory){
                filteredFood.add(food);
            }
        }
        return filteredFood;
    }
}
