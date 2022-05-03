package com.rycka13.nutritionapp.model;

import com.rycka13.nutritionapp.model.data.Food;

import java.util.ArrayList;

public interface Model {

    double getTodaysCalories(ArrayList<Food> userFoods);
    Double getWeeklyCalories(ArrayList<Food> userFoods);
    int getPercentage(double consumed, double total);
    Double getBMI(double height, double weight);
    Double getWeightChange(double oldWeight,double newWeight);
    Double getFoodCalories(double caloriesPer100, double grams);

}
