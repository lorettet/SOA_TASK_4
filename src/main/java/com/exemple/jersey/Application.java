package com.exemple.jersey;

import com.exemple.jersey.model.*;

import java.util.*;

public class Application {

    private static int lastIdUser = 0;
    private static int lastIdExercise = 0;
    private static int lastIdFood = 0;

    public static final String KEY_JWT = "ThishasTOBEaveryl0ngk3y&alsoQU1t3c0mpl3c4T3D";

    static {
        Application.exerciseList = new HashMap<Long, Exercise>();
        Application.userList = new HashMap<Long, User>();
        Application.foodList = new HashMap<Long, Food>();

        User us1 = new User("Romain", "Kuss-Brieke", 22, 69, UserSex.MAN, "login", "123456", Role.ADMIN);
        User us2 = new User("Théo", "Lorette-Froidevaux", 40, 80, UserSex.MAN, "login", "azerty", Role.REGISTERED);
        User us3 = new User("John", "LeRouge", 80, 49, UserSex.WOMAN, "login", "password", Role.VISITOR);

        Application.addUser(us1);
        Application.addUser(us2);
        Application.addUser(us3);


        Exercise ex1 = new Exercise("Un test", "Description of the 1st exercise", ExerciseCategory.WEIGHT_LOSS, us1);
        Exercise ex2 = new Exercise("Un autre test", "Description of the 2nd exercise", ExerciseCategory.WEIGHT_GAIN, us1);
        Exercise ex3 = new Exercise("Un dernier test", "Description of the 3rd exercise", ExerciseCategory.SECHE, us1);

        Application.addExercise(ex1);
        Application.addExercise(ex2);
        Application.addExercise(ex3);

        Food f1 = new Food(FoodCategory.CARBOHYDRATES,"French fries",100);
        Food f2 = new Food(FoodCategory.MEATS,"Salmon",200);
        Food f3 = new Food(FoodCategory.FRUITS,"BANANA",99);
        Application.addFood(f1);
        Application.addFood(f2);
        Application.addFood(f3);

        Application.addFoodToExercise(ex1,f1, "http://localhost:8080/SOA_TASK_3_war_exploded/rest/food/1");
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
        user.setId(++Application.lastIdUser);
        Application.userList.put(user.getId(), user);
        return user;
    }

    public static void modifyUser(User user) {
        Application.userList.put(user.getId(), user);
    }

    public static Exercise addExercise(Exercise exercise) {
        exercise.setId((long) ++Application.lastIdExercise);
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
        food.setId(++Application.lastIdFood);
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

    public static Exercise addFoodToExercise(Exercise exercise, Food food, String uri) {
        exercise.addFood(uri, food);
        return exercise;
    }

    public static Exercise deleteFoodFromExercise(Exercise exercise, FoodLink foodLink) {
        exercise.deleteFood(foodLink);
        return exercise;
    }

    /********** END-FOOD **********/
}
