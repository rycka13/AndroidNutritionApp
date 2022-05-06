package com.rycka13.nutritionapp.viewModel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.instances.DatabaseInstance;
import com.rycka13.nutritionapp.model.instances.UserAuthInstance;

public class AddViewModel extends AndroidViewModel {

    private DatabaseInstance databaseInstance;
    private final UserAuthInstance userAuthInstance;

    public AddViewModel(@NonNull Application application) {
        super(application);
        userAuthInstance = UserAuthInstance.getInstance(application);
        databaseInstance = DatabaseInstance.getInstance(userAuthInstance.getCurrentUser().getValue().getUid());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addFood(Food food){
        databaseInstance.addFood(food);
    }
}
