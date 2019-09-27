package com.exemple.jersey.service;

import com.exemple.jersey.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ServiceExercise {
    public List<Exercise> getAllExercises() {
        Exercise ex1 = new Exercise(1,"Un test");
        Exercise ex2 = new Exercise(2,"Un autre test");
        Exercise ex3 = new Exercise(123456,"Un dernier test");

        List<Exercise> exercises = new ArrayList<Exercise>();
        exercises.add(ex1);
        exercises.add(ex2);
        exercises.add(ex3);

        return exercises;
    }
}
