package com.exemple.jersey.service;

import com.exemple.jersey.Application;
import com.exemple.jersey.model.Exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceExercise {
    public Collection<Exercise> getAllExercises() {
        return Application.getExerciseList();
    }

    public void addExercise(Exercise exercise) {
        Application.addExercise(exercise);
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
}
