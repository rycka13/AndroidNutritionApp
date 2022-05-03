package com.rycka13.nutritionapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.rycka13.nutritionapp.model.data.Food;

import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model{

    public ModelManager() {

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public double getTodaysCalories(ArrayList<Food> userFoods) {
        LocalDate todaysDate = LocalDate.now();

        double consumedCalories = 0;

        for (int i = 0; i < userFoods.size(); i++) {
            if(!userFoods.get(i).getDate().equals(todaysDate.toString())) {
                break;
            }
            consumedCalories+= userFoods.get(i).getCaloriesPer100Grams()/100 * userFoods.get(i).getGramsConsumed();
        }
        return Math.floor(consumedCalories);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Double getWeeklyCalories(ArrayList<Food> userFoods) {
        LocalDate lastDate = LocalDate.now();
        LocalDate firstDate = lastDate.minusDays(7);

        double consumedCalories = 0;

        for (int i = 0; i < userFoods.size(); i++){
            if(!LocalDate.parse(userFoods.get(i).getDate()).isAfter(firstDate)) {
                break;
            }
            consumedCalories+= userFoods.get(i).getCaloriesPer100Grams()/100 * userFoods.get(i).getGramsConsumed();
        }
        return Math.floor(consumedCalories);
    }

    @Override
    public int getPercentage(double consumed, double total) {
        return (int) Math.round(consumed/total*100);
    }

    @Override
    public Double getBMI(double height, double weight) {
        return Math.floor(weight/(height*height/10000));
    }

    @Override
    public Double getWeightChange(double oldWeight, double newWeight) {
        return Math.floor(newWeight - oldWeight);
    }

    @Override
    public Double getFoodCalories(double caloriesPer100, double grams) {
        return Math.floor(caloriesPer100/100*grams);
    }


}
