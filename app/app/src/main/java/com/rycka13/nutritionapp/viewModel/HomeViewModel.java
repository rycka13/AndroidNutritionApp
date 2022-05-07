package com.rycka13.nutritionapp.viewModel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.rycka13.nutritionapp.model.Model;
import com.rycka13.nutritionapp.model.ModelManager;
import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.data.User;
import com.rycka13.nutritionapp.model.data.Weight;
import com.rycka13.nutritionapp.model.instances.DataRepository;
import com.rycka13.nutritionapp.model.instances.UserRepository;

import java.util.ArrayList;

public class HomeViewModel extends AndroidViewModel {

    private UserRepository userRep;
    private DataRepository dataRepositoryInterface;
    private Model model;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        userRep = UserRepository.getInstance(application);
        dataRepositoryInterface = DataRepository.getInstance(userRep.getCurrentUser().getValue().getUid());
        model = new ModelManager();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRep.getCurrentUser();
    }

    public LiveData<ArrayList<Food>> getFood(){
        return dataRepositoryInterface.getFood();
    }

    public LiveData<ArrayList<Weight>> getUserWeight(){
        return dataRepositoryInterface.getUserWeight();
    }

    public String getBMI(double height, double weight){
        return model.getBMI(height, weight).toString();
    }

    public int getPercentage(ArrayList<Food> foods,double limit){
        return model.getPercentage(model.getTodaysCalories(foods),limit);
    }

    public LiveData<User> getUserData() {
        return dataRepositoryInterface.getUserData();
    }

    public String getTodaysCalories(ArrayList<Food> foods, Double limit){
        return model.getTodaysCalories(foods) + "/\n" + limit;
    }

    public double getWeightChange(double previousWeight, double currentWeight){
        return model.getWeightChange(previousWeight,currentWeight);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDatabaseInstance(String userId){
        dataRepositoryInterface.getInstance(userId);
    }
}
