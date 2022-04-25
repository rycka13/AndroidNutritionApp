package com.rycka13.nutritionapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model{

    private User user;
    private FirebaseUser firebaseUser;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ModelManager(FirebaseUser user){
        this.firebaseUser = user;
        this.user = new User(firebaseUser.getDisplayName());
    }

    @Override
    public void setFistName(String firstName){
        user.setFirstName(firstName);
    }

    @Override
    public String getFirstName() {
        return user.getFirstName();
    }

    @Override
    public ArrayList<Food> getFoods() {
        return user.getFoods();
    }

    @Override
    public void setFoods() {

    }

    @Override
    public void addFood(Food food) {
        user.addFood(food);
    }

    @Override
    public void removeFood(Food food) {
        user.removeFood(food);
    }

    @Override
    public void setWeight(double weight) {
        user.setWeight(weight);
    }

    @Override
    public double getWeight() {
        return user.getWeight();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public double getTodaysCalories() {
        LocalDate todaysDate = LocalDate.now();

        ArrayList<Food> userFoods = user.getFoods();

        int index=userFoods.size()-1;
        double consumedCalories = 0;

        while (userFoods.get(index).getDate().equals(todaysDate)){
            consumedCalories+= userFoods.get(index).getCaloriesPer100Grams()/100 * userFoods.get(index).getGramsConsumed();
            index--;
        }
        return consumedCalories;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public double getWeeklyCalories() {
        LocalDate lastDate = LocalDate.now();
        LocalDate firstDate = lastDate.minusDays(7);

        ArrayList<Food> userFoods = user.getFoods();

        int index=userFoods.size()-1;
        double consumedCalories = 0;

        while (userFoods.get(index).getDate().isAfter(firstDate)){
            consumedCalories+= userFoods.get(index).getCaloriesPer100Grams()/100 * userFoods.get(index).getGramsConsumed();
            index--;
        }
        return consumedCalories;
    }

    @Override
    public void setGender(char gender) {
        user.setGender(gender);
    }

    @Override
    public char getGender() {
        return user.getGender();
    }

    @Override
    public void setGoal(char goal) {
        user.setGoal(goal);
    }

    @Override
    public char getGoal() {
        return user.getGoal();
    }

    @Override
    public String getUserNumber() {
        return user.getUserNumber();
    }
}
