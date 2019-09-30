package com.exemple.jersey;

import com.exemple.jersey.model.Exercise;
import com.exemple.jersey.model.Food;
import com.exemple.jersey.model.FoodCategory;
import com.exemple.jersey.model.User;

import java.util.*;

public class Application {

    static {
        Application.exerciseList = new HashMap<Long, Exercise>();
        Application.userList = new HashMap<Long, User>();
        Application.foodList = new HashMap<Long, Food>();

        Exercise ex1 = new Exercise("Un test");
        Exercise ex2 = new Exercise("Un autre test");
        Exercise ex3 = new Exercise("Un dernier test");

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

    /********** FOOD **********/

    public static Collection<Food> getFoodList () {     //GET
        return foodList.values();
    }

    public static Food getFood (long id) {      //GET
        return Application.foodList.get(id);
    }

    public static Food getFoodByName (String name) {      //GET
        List<Food> foodList = new ArrayList(Application.foodList.values());
        Iterator it = foodList.iterator();
        while(it.hasNext()) {
            Food food = (Food) it.next();
            if(food.getName().equals(name)) {
                return food;
            }
        }
        return null;
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
