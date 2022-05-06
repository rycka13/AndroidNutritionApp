package com.rycka13.nutritionapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.rycka13.nutritionapp.model.Model;
import com.rycka13.nutritionapp.model.ModelManager;
import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.instances.DatabaseInstance;
import com.rycka13.nutritionapp.model.instances.UserAuthInstance;

import java.util.ArrayList;

public class ListViewModel extends AndroidViewModel {


    private UserAuthInstance userRep;
    private DatabaseInstance databaseInstance;
    private Model model;

    public ListViewModel(@NonNull Application application) {
        super(application);
        userRep = UserAuthInstance.getInstance(application);
        databaseInstance = DatabaseInstance.getInstance(userRep.getCurrentUser().getValue().getUid());
        model = new ModelManager();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRep.getCurrentUser();
    }

    public LiveData<ArrayList<Food>> getFood(){
        return databaseInstance.getFood();
    }

    public String getWeeklyCalories(ArrayList<Food> foods){
        return model.getWeeklyCalories(foods).toString();
    }
}
