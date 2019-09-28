package com.exemple.jersey.service;

import com.exemple.jersey.Application;
import com.exemple.jersey.model.Exercise;
import com.exemple.jersey.model.ExerciseCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
}
