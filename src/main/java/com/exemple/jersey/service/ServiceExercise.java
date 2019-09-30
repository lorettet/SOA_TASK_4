package com.exemple.jersey.service;

import com.exemple.jersey.Application;
import com.exemple.jersey.endpoint.EndpointFood;
import com.exemple.jersey.exception.UnknownFoodException;
import com.exemple.jersey.model.Exercise;
import com.exemple.jersey.model.ExerciseCategory;
import com.exemple.jersey.model.Food;
import com.exemple.jersey.model.FoodLink;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Collection;

public class ServiceExercise {
    public Collection<Exercise> getAllExercises() {
        return Application.getExerciseList();
    }

    public Exercise addExercise(Exercise exercise) {
        return Application.addExercise(exercise);
    }

    public boolean deleteExercise(long id) {
        Exercise ex = Application.getExercise(id);
        if(ex == null) return false;
        Application.deleteExercise(ex);
        return true;
    }

    public Exercise getExercise(long id) {
        return Application.getExercise(id);
    }

    public Exercise updateExercise(Exercise ex) {
        return Application.updateExercise(ex);
    }

    public Collection<Exercise> getAllExercises(ExerciseCategory exerciseCategory) {
        Collection<Exercise> allExercise = Application.getExerciseList();
        ArrayList<Exercise> filteredExercise = new ArrayList<Exercise>();
        for(Exercise ex : allExercise){
            if(ex.getCategory() == exerciseCategory){
                filteredExercise.add(ex);
            }
        }
        return filteredExercise;
    }

    public Exercise addFood(long exerciseId, long foodId, UriInfo uriInfo) {
        Exercise exercise = Application.getExercise(exerciseId);
        Food food = Application.getFood(foodId);
        if(exercise == null) throw new NotFoundException();
        if(food == null) throw new UnknownFoodException(String.valueOf(foodId));
        String uri = uriInfo.getBaseUriBuilder().path(EndpointFood.class).path(String.valueOf(foodId)).build().toString();
        return Application.addFoodToExercise(exercise, food, uri);
    }

    public Exercise deleteFood(long exerciseId, long foodId) {
        Exercise exercise = Application.getExercise(exerciseId);
        FoodLink foodLink = exercise.obtainFoodLinkFromID(foodId);
        if(exercise == null || foodLink == null) throw new NotFoundException();
        return Application.deleteFoodFromExercise(exercise, foodLink);
    }
}
