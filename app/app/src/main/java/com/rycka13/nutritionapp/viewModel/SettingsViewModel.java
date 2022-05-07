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
import com.rycka13.nutritionapp.model.instances.DataRepository;
import com.rycka13.nutritionapp.model.instances.UserRepository;

public class SettingsViewModel extends AndroidViewModel {

    private UserRepository userRep;
    private DataRepository dataRepositoryInterface;
    private Model model;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        userRep = UserRepository.getInstance(application);
        dataRepositoryInterface = DataRepository.getInstance(userRep.getCurrentUser().getValue().getUid());
        model = new ModelManager();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRep.getCurrentUser();
    }

    public LiveData<User> getUserData() {
        return dataRepositoryInterface.getUserData();
    }

    public void addUserWeight(Weight weight){
        dataRepositoryInterface.addUserWeight(weight);
    }

    public void setUserParameters(double weight, double height, double limit, String gender){
        dataRepositoryInterface.setUserParameters(weight,height,limit,gender);
    }
}
