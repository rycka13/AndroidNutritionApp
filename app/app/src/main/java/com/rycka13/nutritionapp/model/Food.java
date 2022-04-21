package com.rycka13.nutritionapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class Food {
    private String foodName;
    private double caloriesPer100Grams;
    private double gramsConsumed;
    private LocalDate date;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Food(String foodName, double caloriesPer100Grams, double gramsConsumed){
        this.foodName = foodName;
        this.caloriesPer100Grams = caloriesPer100Grams;
        this.gramsConsumed = gramsConsumed;
        this.date = LocalDate.now();
    }

    public double getGramsConsumed() {
        return gramsConsumed;
    }

    public double getCaloriesPer100Grams() {
        return caloriesPer100Grams;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setCaloriesPer100Grams(double caloriesPer100Grams) {
        this.caloriesPer100Grams = caloriesPer100Grams;
    }

    public void setGramsConsumed(double gramsConsumed) {
        this.gramsConsumed = gramsConsumed;
    }

    public LocalDate getDate() {
        return date;
    }
}
