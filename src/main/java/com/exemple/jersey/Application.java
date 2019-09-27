package com.exemple.jersey;

import com.exemple.jersey.model.Exercise;
import com.exemple.jersey.model.User;
import org.glassfish.jersey.jaxb.internal.XmlJaxbElementProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Application {

    static {
        Application.exerciseList = new HashMap<Long, Exercise>();
        Application.userList = new HashMap<Long, User>();

        Exercise ex1 = new Exercise("Un test");
        Exercise ex2 = new Exercise("Un autre test");
        Exercise ex3 = new Exercise("Un dernier test");

        Application.addExercise(ex1);
        Application.addExercise(ex2);
        Application.addExercise(ex3);
    }

    private static HashMap<Long, Exercise> exerciseList;

    private static HashMap<Long, User> userList;

    public static Collection<Exercise> getExerciseList() {
        return exerciseList.values();
    }

    public static Exercise getExercise(long id){
        return Application.exerciseList.get(id);
    }

    public static Collection<User> getUserList() {
        return userList.values();
    }

    public static void addUser(User user){
        user.setId(Application.userList.size()+1);
        Application.userList.put(user.getId(), user);
    }

    public static void addExercise(Exercise exercise){
        exercise.setId(Application.exerciseList.size()+1);
        Application.exerciseList.put(exercise.getId(), exercise);
    }

    public static void deleteExercise(Exercise exercise)
    {
        Application.exerciseList.remove(exercise.getId());
    }
}
