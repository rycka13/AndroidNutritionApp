package com.rycka13.nutritionapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model{

    public ModelManager() {

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public double getTodaysCalories(ArrayList<Food> userFoods) {
        LocalDate todaysDate = LocalDate.now();

        if(userFoods.size()==0){
            return 0;
        }

        int index=userFoods.size()-1;
        double consumedCalories = 0;

        while (userFoods.get(index).getDate().equals(todaysDate.toString())){
            consumedCalories+= userFoods.get(index).getCaloriesPer100Grams()/100 * userFoods.get(index).getGramsConsumed();
            index--;
        }
        return consumedCalories;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double getWeeklyCalories(ArrayList<Food> userFoods) {
        LocalDate lastDate = LocalDate.now();
        LocalDate firstDate = lastDate.minusDays(7);


        int index=userFoods.size()-1;
        double consumedCalories = 0;

        while (LocalDate.parse(userFoods.get(index).getDate()).isAfter(firstDate)){
            consumedCalories+= userFoods.get(index).getCaloriesPer100Grams()/100 * userFoods.get(index).getGramsConsumed();
            index--;
        }
        return consumedCalories;
    }

    @Override
    public int getPercentage(double consumed, double total) {
        int ret = (int) Math.round(consumed/total*100);
        return ret;
    }


}
