package com.exemple.jersey;

import com.exemple.jersey.model.Exercise;
import com.exemple.jersey.model.Food;
import com.exemple.jersey.model.FoodCategory;
import com.exemple.jersey.model.ExerciseCategory;
import com.exemple.jersey.model.User;
import com.exemple.jersey.model.UserSex;

import java.util.Collection;
import java.util.HashMap;

public class Application {

    static {
        Application.exerciseList = new HashMap<Long, Exercise>();
        Application.userList = new HashMap<Long, User>();
        Application.foodList = new HashMap<Long, Food>();

        User us1 = new User("Romain", "Kuss-Brieke", 22, 69, UserSex.MAN, "login", "123456");
        User us2 = new User("Théo", "Lorette-Froidevaux", 40, 80, UserSex.MAN, "login", "azerty");
        User us3 = new User("John", "LeRouge", 80, 49, UserSex.WOMAN, "login", "password");

        Application.addUser(us1);
        Application.addUser(us2);
        Application.addUser(us3);


        Exercise ex1 = new Exercise("Un test", ExerciseCategory.WEIGHT_LOSS, us1);
        Exercise ex2 = new Exercise("Un autre test", ExerciseCategory.WEIGHT_GAIN, us1);
        Exercise ex3 = new Exercise("Un dernier test", ExerciseCategory.SECHE, us1);

        Application.addExercise(ex1);
        Application.addExercise(ex2);
        Application.addExercise(ex3);

        Food f1 = new Food(FoodCategory.CARBOHYDRATES,"French fries",100);
        Food f2 = new Food(FoodCategory.MEATS,"Salmon",200);
        Food f3 = new Food(FoodCategory.FRUITS,"BANANA",99);
        Application.addFood(f1);
        Application.addFood(f2);
        Application.addFood(f3);
    }

    private static HashMap<Long, Exercise> exerciseList;

    private static HashMap<Long, User> userList;

    private static HashMap<Long, Food> foodList;

    public static Collection<Exercise> getExerciseList() {
        return exerciseList.values();
    }

    public static Exercise getExercise(long id) {
        return Application.exerciseList.get(id);
    }

    public static User getUser(long id) {
        return Application.userList.get(id);
    }

    public static Collection<User> getUserList() {
        return userList.values();
    }

    public static User addUser(User user) {
        user.setId(Application.userList.size() + 1);
        Application.userList.put(user.getId(), user);
        return user;
    }

    public static void modifyUser(User user) {
        Application.userList.put(user.getId(), user);
    }

    public static Exercise addExercise(Exercise exercise) {
        exercise.setId((long) (Application.exerciseList.size() + 1));
        Application.exerciseList.put(exercise.getId(), exercise);
        return exercise;
    }

    public static void deleteExercise(Exercise exercise) {
        Application.exerciseList.remove(exercise.getId());
    }

    public static Exercise updateExercise(Exercise ex) {
        Application.exerciseList.put(ex.getId(), ex);
        return ex;
    }

    public static void deleteUser(User user) {
        Application.userList.remove(user.getId());
    }

    public static User getUser(String login, String password) {
        for (User u : Application.getUserList()) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) return u;
        }
        return null;
    }


    /********** FOOD **********/

    public static Collection<Food> getFoodList () {     //GET
        return foodList.values();
    }

    public static Food getFood (long id) {      //GET
        return Application.foodList.get(id);
    }

    public static Food addFood(Food food){      //PUT
        food.setId(Application.foodList.size()+1);
        Application.foodList.put(food.getId(), food);
        return food;
    }

    public static void deleteFood(Food food) {      //DELETE
        Application.foodList.remove(food.getId());
    }

    public static Food updateFood (Food food) {     //POST
        Application.foodList.put(food.getId(),food);
        return food;
    }

    /********** END-FOOD **********/
}
