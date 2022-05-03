package com.rycka13.nutritionapp.model.instances;

import android.app.Application;

import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;
import com.firebase.ui.auth.AuthUI;


import com.rycka13.nutritionapp.model.wrappers.UserAuthWrapper;

public class UserAuthInstance {
    private final UserAuthWrapper currentUser;
    private final Application app;
    private static UserAuthInstance instance;

    private UserAuthInstance(Application app) {
        this.app = app;
        currentUser = new UserAuthWrapper();
    }

    public static synchronized UserAuthInstance getInstance(Application app) {
        if(instance == null)
            instance = new UserAuthInstance(app);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }
}
