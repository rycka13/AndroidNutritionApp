package com.rycka13.nutritionapp.model.instances;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rycka13.nutritionapp.model.DAO.DatabaseDAO;
import com.rycka13.nutritionapp.model.DataRepositoryInterface;
import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.data.User;
import com.rycka13.nutritionapp.model.data.Weight;
import com.rycka13.nutritionapp.model.wrappers.FoodWrapper;
import com.rycka13.nutritionapp.model.wrappers.UserWrapper;
import com.rycka13.nutritionapp.model.wrappers.WeightWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataRepository implements DataRepositoryInterface {

    private DatabaseDAO databaseDAO;
    private static DataRepository instance;


    private DataRepository(String userId) {
        databaseDAO = DatabaseDAO.getInstance(userId);
        databaseDAO.findUser();
    }

    public static DataRepository getInstance(String userId) {
        if (instance == null) {
            instance = new DataRepository(userId);
        }
        return instance;
    }

    @Override
    public void addFood(Food food) {
        databaseDAO.addFood(food);
    }

    @Override
    public void removeFood(Food food) {
        databaseDAO.removeFood(food);
    }

    @Override
    public LiveData<ArrayList<Food>> getFood() {
        return databaseDAO.getFood();
    }

    @Override
    public void setUserParameters(double weight, double height, double limit, String gender) {
        databaseDAO.setUserParameters(weight, height, limit, gender);
    }

    @Override
    public LiveData<User> getUserData() {
        return databaseDAO.getUserData();
    }

    @Override
    public void addUserWeight(Weight weight) {
        databaseDAO.addUserWeight(weight);
    }

    @Override
    public LiveData<ArrayList<Weight>> getUserWeight() {
        return databaseDAO.getUserWeight();
    }
}

