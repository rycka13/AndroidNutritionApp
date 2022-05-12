package com.rycka13.nutritionapp.model;

import androidx.lifecycle.LiveData;

import com.rycka13.nutritionapp.model.DAO.DatabaseDAO;
import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.data.User;
import com.rycka13.nutritionapp.model.data.Weight;

import java.util.ArrayList;

public interface DataRepositoryInterface {
    void addFood(Food food);
    void removeFood(Food food);
    LiveData<ArrayList<Food>> getFood();
    void setUserParameters(double weight,double height,double limit,String gender);
    LiveData<User> getUserData();
    void addUserWeight(Weight weight);
    LiveData<ArrayList<Weight>> getUserWeight();
}
