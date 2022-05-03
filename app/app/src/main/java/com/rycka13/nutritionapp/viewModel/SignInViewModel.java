package com.rycka13.nutritionapp.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.rycka13.nutritionapp.model.instances.UserAuthInstance;


public class SignInViewModel extends AndroidViewModel {
    private final UserAuthInstance userAuthInstance;

    public SignInViewModel(Application app){
        super(app);
        userAuthInstance = UserAuthInstance.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userAuthInstance.getCurrentUser();
    }
}
