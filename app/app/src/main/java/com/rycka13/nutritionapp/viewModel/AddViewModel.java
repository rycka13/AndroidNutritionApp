package com.rycka13.nutritionapp.viewModel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.instances.DataRepository;
import com.rycka13.nutritionapp.model.instances.UserRepository;

public class AddViewModel extends AndroidViewModel {

    private DataRepository dataRepositoryInterface;
    private final UserRepository userRepository;

    public AddViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        dataRepositoryInterface = DataRepository.getInstance(userRepository.getCurrentUser().getValue().getUid());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addFood(Food food){
        dataRepositoryInterface.addFood(food);
    }
}
