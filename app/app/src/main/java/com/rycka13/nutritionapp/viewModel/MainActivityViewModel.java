package com.rycka13.nutritionapp.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.rycka13.nutritionapp.model.instances.UserAuthInstance;


public class MainActivityViewModel extends AndroidViewModel {
    private final UserAuthInstance userAuthInstance;

    public MainActivityViewModel(Application app){
        super(app);
        userAuthInstance = UserAuthInstance.getInstance(app);

    }

    public void init() {
        //String userId = userRepository.getCurrentUser().getValue().getUid();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userAuthInstance.getCurrentUser();
    }

    public void signOut() {
        userAuthInstance.signOut();
    }
}
