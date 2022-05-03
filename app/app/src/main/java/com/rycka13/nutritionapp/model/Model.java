package com.rycka13.nutritionapp.model;

import com.rycka13.nutritionapp.model.data.Food;

import java.util.ArrayList;

public interface Model {

    double getTodaysCalories(ArrayList<Food> userFoods);
    double getWeeklyCalories(ArrayList<Food> userFoods);
    int getPercentage(double consumed, double total);

}
