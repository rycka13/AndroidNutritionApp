package com.rycka13.nutritionapp.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseInstance{

    private String userId;
    private FirebaseFirestore db;
    private static DatabaseInstance instance;

    private DatabaseInstance(String userId){
        this.userId = userId;
        db = FirebaseFirestore.getInstance();
    }


    public static DatabaseInstance getInstance(String userId) {
        if(instance == null){
            instance = new DatabaseInstance(userId);
            instance.findUser();
        }
        return instance;
    }


    public boolean addUser() {
        ArrayList<Food> foods= new ArrayList<Food>();
        foods.add(new Food("Pie",500,1));
        foods.add(new Food("Pizza",500,1));
        HashMap<String,ArrayList<Food>> map = new HashMap<String,ArrayList<Food>>() {
        };

        map.put(userId,foods);
        db.collection("users").add(map);
//                .addOnSuccessListener(documentReference -> {
//
//                })
//                .addOnFailureListener(e -> {
//
//                });
        return true;
    }


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

//        try{
//            db.collection("users").document(userId)
//        }
//        catch (NullPointerException e){
//            addUser();
//        }
//        return true;

        return true;
    }


    public boolean addFood(Food food) {
        return false;
    }

    public boolean removeFood(Food food) {
        return false;
    }

    public ArrayList<Food> getFood() {
        return null;
    }
}
