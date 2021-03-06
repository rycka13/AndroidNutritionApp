package com.rycka13.nutritionapp.model.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class Food {
    private String foodName;
    private double caloriesPer100Grams;
    private double gramsConsumed;
    private String dateString;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Food(String foodName, double caloriesPer100Grams, double gramsConsumed, String dateString){
        this.foodName = foodName;
        this.caloriesPer100Grams = caloriesPer100Grams;
        this.gramsConsumed = gramsConsumed;
        this.dateString = dateString;
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

    @Override
    public String toString() {
        return "Food{" +
                "foodName='" + foodName + '\'' +
                ", caloriesPer100Grams=" + caloriesPer100Grams +
                ", gramsConsumed=" + gramsConsumed +
                ", date=" + dateString +
                '}';
    }

    public String getDate() {
        return dateString;
    }
}
