package com.rycka13.nutritionapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.rycka13.nutritionapp.model.data.User;
import com.rycka13.nutritionapp.model.data.Weight;
import com.rycka13.nutritionapp.model.instances.DataRepository;
import com.rycka13.nutritionapp.model.instances.UserRepository;

import java.util.ArrayList;

public class ProfileViewModel extends AndroidViewModel {

    private UserRepository userRep;
    private DataRepository dataRepositoryInterface;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        userRep = UserRepository.getInstance(application);
        dataRepositoryInterface = DataRepository.getInstance(userRep.getCurrentUser().getValue().getUid());
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRep.getCurrentUser();
    }

    public LiveData<ArrayList<Weight>> getUserWeight(){
        return dataRepositoryInterface.getUserWeight();
    }

    public LiveData<User> getUserData() {
        return dataRepositoryInterface.getUserData();
    }

}
