package com.rycka13.nutritionapp.viewModel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.rycka13.nutritionapp.model.instances.DataRepository;
import com.rycka13.nutritionapp.model.instances.UserRepository;


public class MainActivityViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private DataRepository dataRepositoryInterface;

    public MainActivityViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void init() {
        String userId = userRepository.getCurrentUser().getValue().getUid();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void signOut() {
        userRepository.signOut();
    }
}
