package com.rycka13.nutritionapp.model;

import java.util.ArrayList;

public interface Model {
    void setFistName(String firstName);
    String getFirstName();
    ArrayList<Food> getFoods();
    void setFoods();
    void addFood(Food food);
    void removeFood(Food food);
    void setWeight(double weight);
    double getWeight();
    double getTodaysCalories();
    double getWeeklyCalories();
    void setGender(char gender);
    char getGender();
    void setGoal(char goal);
    char getGoal();


}
