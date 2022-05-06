package com.rycka13.nutritionapp.viewModel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.rycka13.nutritionapp.model.instances.DatabaseInstance;
import com.rycka13.nutritionapp.model.instances.UserAuthInstance;


public class MainActivityViewModel extends AndroidViewModel {
    private final UserAuthInstance userAuthInstance;
    private DatabaseInstance databaseInstance;

    public MainActivityViewModel(Application app){
        super(app);
        userAuthInstance = UserAuthInstance.getInstance(app);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void init() {
        String userId = userAuthInstance.getCurrentUser().getValue().getUid();
        //databaseInstance = DatabaseInstance.getInstance(userId);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userAuthInstance.getCurrentUser();
    }

    public void signOut() {
        userAuthInstance.signOut();
    }
}
