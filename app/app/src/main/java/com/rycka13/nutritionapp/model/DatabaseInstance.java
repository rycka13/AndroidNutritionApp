package com.rycka13.nutritionapp.model;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseInstance{

    private String userId;
    private FirebaseFirestore db;
    private static DatabaseInstance instance;
    private FoodWrapper fr;
    private UserWrapper uw;

    private DatabaseInstance(String userId){
        this.userId = userId;
        db = FirebaseFirestore.getInstance();
    }
//reset on sign out!!!!!!!!!!!!

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static DatabaseInstance getInstance(String userId) {
        if(instance == null){
            instance = new DatabaseInstance(userId);
            instance.findUser();
        }
        return instance;
    }

    public static DatabaseInstance getInstance() {

        return instance;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setInstance(String userId){
        this.userId =userId;
        findUser();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addUser() {
        ArrayList<Food> foods= new ArrayList<Food>();

        HashMap<String,ArrayList<Food>> map = new HashMap<>();
        HashMap<String,Object> mapParameters = new HashMap<>();

        map.put(userId,foods);

        mapParameters.put("weight",0.0);
        mapParameters.put("height",0.0);
        mapParameters.put("limit",2000.0);
        mapParameters.put("gender","Unknown");

        db.collection("users").document(userId).set(map);
        db.collection("userParameters").document(userId).set(mapParameters);

        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean findUser() {

        DocumentReference docIdRef = db.collection("users").document(userId); //https://stackoverflow.com/questions/53332471/checking-if-a-document-exists-in-a-firestore-collection
        docIdRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {

                } else {
                    addUser();

                }
            } else {

            }
        });

        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addFood(Food food) {


        db.collection("users").addSnapshotListener((value, error) -> {
            for(DocumentChange dc : value.getDocumentChanges()){
                if(dc.getType() == DocumentChange.Type.ADDED){

                    Map<String,Object> hashMap;
                    hashMap = dc.getDocument().getData();
                    HashMap<String,Object> tempMap = (HashMap<String, Object>) hashMap;
                    ArrayList<HashMap> foods = (ArrayList<HashMap>) tempMap.get(userId);
                    ArrayList<Food> foodsList = new ArrayList<>();
                    for (int i = 0; i<foods.size(); i++){
                        Food foodExtract = new Food((String) foods.get(i).get("foodName"),(Double) foods.get(i).get("caloriesPer100Grams"),(Double)foods.get(i).get("gramsConsumed"),(String) foods.get(i).get("date"));
                        foodsList.add(foodExtract);
                    }
                    foodsList.add(food);
                    HashMap<String,ArrayList<Food>> map = new HashMap<>();
                    map.put(userId,foodsList);

                    db.collection("users").document(userId).set(map);
                }
            }
        });

        return true;
    }

    public boolean removeFood(Food food) {
        db.collection("users").addSnapshotListener((value, error) -> {
            for(DocumentChange dc : value.getDocumentChanges()){
                if(dc.getType() == DocumentChange.Type.ADDED){
                    Map<String,Object> hashMap;
                    hashMap = dc.getDocument().getData();
                    ArrayList<Food> foods = (ArrayList<Food>) hashMap.get(userId);
                    foods.remove(food);
                    HashMap<String,ArrayList<Food>> map = new HashMap<>();
                    map.put(userId,foods);

                    db.collection("users").document(userId).set(map);
                }
            }
        });
        return true;
    }

    public LiveData<ArrayList<Food>> getFood() {

        DocumentReference docRef = db.collection("users").document(userId);
        fr = new FoodWrapper(docRef,userId);

        return fr.getFoods();
    }

    public boolean setUserParameters(double weight,double height,double limit,String gender){
        HashMap<String,Object> map = new HashMap<>();

        map.put("weight",weight);
        map.put("height",height);
        map.put("limit",limit);
        map.put("gender",gender);
        db.collection("userParameters").document(userId).set(map);

        return true;
    }

    public LiveData<User> getUserData(){

        DocumentReference docRef = db.collection("userParameters").document(userId);

        uw = new UserWrapper(docRef,userId);

        return uw;
    }
}
