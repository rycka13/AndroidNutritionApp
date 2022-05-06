package com.rycka13.nutritionapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.rycka13.nutritionapp.model.Model;
import com.rycka13.nutritionapp.model.ModelManager;
import com.rycka13.nutritionapp.model.data.User;
import com.rycka13.nutritionapp.model.data.Weight;
import com.rycka13.nutritionapp.model.instances.DatabaseInstance;
import com.rycka13.nutritionapp.model.instances.UserAuthInstance;

public class SettingsViewModel extends AndroidViewModel {

    private UserAuthInstance userRep;
    private DatabaseInstance databaseInstance;
    private Model model;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        userRep = UserAuthInstance.getInstance(application);
        databaseInstance = DatabaseInstance.getInstance(userRep.getCurrentUser().getValue().getUid());
        model = new ModelManager();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRep.getCurrentUser();
    }

    public LiveData<User> getUserData() {
        return databaseInstance.getUserData();
    }

    public void addUserWeight(Weight weight){
        databaseInstance.addUserWeight(weight);
    }

    public void setUserParameters(double weight, double height, double limit, String gender){
        databaseInstance.setUserParameters(weight,height,limit,gender);
    }
}
