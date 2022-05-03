package com.rycka13.nutritionapp.model.wrappers;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.rycka13.nutritionapp.model.data.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodWrapper extends LiveData<ArrayList<Food>> {

    private ArrayList<Food> foodsList;
    private DocumentReference dbRef;
    private String userId;

    private final EventListener<DocumentSnapshot> listener = new EventListener<DocumentSnapshot>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
            Map<String,Object> hashMap;
            hashMap = value.getData();
            HashMap<String,Object> tempMap = (HashMap<String, Object>) hashMap;
            ArrayList<HashMap> foods = (ArrayList<HashMap>) tempMap.get(userId);
            setFoods(foods);
            setValue(foodsList);
        }

    };

    @Override
    protected void onActive() {
        super.onActive();
        dbRef.addSnapshotListener(listener);
    }


    public FoodWrapper(DocumentReference dbRef,String userId){
        this.foodsList = new ArrayList<>();
        this.dbRef = dbRef;
        this.userId = userId;
    }

    public LiveData<ArrayList<Food>> getFoods() {
        return this;
    }

    public ArrayList<Food> getFoodsList(){
        return foodsList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setFoods(ArrayList<HashMap> foods) {
        for (int i = 0; i<foods.size(); i++){
            Food food = new Food((String) foods.get(i).get("foodName"),(Double) foods.get(i).get("caloriesPer100Grams"),(Double)foods.get(i).get("gramsConsumed"),(String) foods.get(i).get("date"));
            System.out.println("setFoods debug"+food.toString());
            this.foodsList.add(food);
        }

    }
}
