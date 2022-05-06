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

import java.util.ArrayList;

public class ProfileViewModel extends AndroidViewModel {

    private UserAuthInstance userRep;
    private DatabaseInstance databaseInstance;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        userRep = UserAuthInstance.getInstance(application);
        databaseInstance = DatabaseInstance.getInstance(userRep.getCurrentUser().getValue().getUid());
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRep.getCurrentUser();
    }

    public LiveData<ArrayList<Weight>> getUserWeight(){
        return databaseInstance.getUserWeight();
    }

    public LiveData<User> getUserData() {
        return databaseInstance.getUserData();
    }

}
