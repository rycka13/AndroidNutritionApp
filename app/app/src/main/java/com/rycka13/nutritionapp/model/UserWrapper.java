package com.rycka13.nutritionapp.model;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserWrapper extends LiveData<User> {
    private DocumentReference dbRef;
    private String userId;
    private User user;

    private final EventListener<DocumentSnapshot> listener = new EventListener<DocumentSnapshot>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
            Map<String,Object> hashMap;
            hashMap = value.getData();
            HashMap<String,Object> tempMap = (HashMap<String, Object>) hashMap;
            user = new User();
            if(!tempMap.isEmpty()){
                for (Map.Entry<String,Object> set : tempMap.entrySet() ){
                    switch (set.getKey()){
                        case "height":
                            user.setHeight((Double) set.getValue());
                            break;
                        case "weight":
                            user.setWeight((Double) set.getValue());
                            break;
                        case "limit":
                            user.setLimit((Double) set.getValue());
                            break;
                        case "gender":
                            user.setGender((String) set.getValue());
                            break;
                    }
                }

            }
            setValue(user);
        }

    };

    @Override
    protected void onActive() {
        super.onActive();
        dbRef.addSnapshotListener(listener);
    }


    public UserWrapper(DocumentReference dbRef,String userId){
        this.dbRef = dbRef;
        this.userId = userId;
    }

    public LiveData<User> getUser() {
        return this;
    }


}
