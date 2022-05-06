package com.rycka13.nutritionapp.model.instances;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.data.User;
import com.rycka13.nutritionapp.model.data.Weight;
import com.rycka13.nutritionapp.model.wrappers.FoodWrapper;
import com.rycka13.nutritionapp.model.wrappers.UserWrapper;
import com.rycka13.nutritionapp.model.wrappers.WeightWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseInstance{

    private String userId;
    private FirebaseFirestore db;
    private static DatabaseInstance instance;
    private FoodWrapper fr;
    private UserWrapper uw;
    private WeightWrapper ww;

    private DatabaseInstance(String userId){
        this.userId = userId;
        db = FirebaseFirestore.getInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static DatabaseInstance getInstance(String userId) {
        if(instance == null){
            instance = new DatabaseInstance(userId);
            instance.findUser();
        }
        return instance;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setInstance(String userId){
        this.userId =userId;
        findUser();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addUser() {
        ArrayList<Food> foods= new ArrayList<>();
        ArrayList<Weight> weights= new ArrayList<>();

        HashMap<String,ArrayList<Food>> map = new HashMap<>();
        HashMap<String,Object> mapParameters = new HashMap<>();
        HashMap<String,ArrayList<Weight>> mapWeight = new HashMap<>();

        map.put(userId,foods);
        mapWeight.put(userId,weights);

        mapParameters.put("weight",0.0);
        mapParameters.put("height",0.0);
        mapParameters.put("limit",2000.0);
        mapParameters.put("gender","Unknown");

        db.collection("usersFoods").document(userId).set(map);
        db.collection("usersParameters").document(userId).set(mapParameters);
        db.collection("usersWeight").document(userId).set(mapWeight);

        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean findUser() {

        DocumentReference docIdRef = db.collection("usersFoods").document(userId); //https://stackoverflow.com/questions/53332471/checking-if-a-document-exists-in-a-firestore-collection
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


        db.collection("usersFoods").addSnapshotListener((value, error) -> {
            for(DocumentChange dc : value.getDocumentChanges()){
                if(dc.getType() == DocumentChange.Type.ADDED){

                    Map<String,Object> hashMap;
                    hashMap = dc.getDocument().getData();
                    HashMap<String,Object> tempMap = (HashMap<String, Object>) hashMap;
                    ArrayList<HashMap> foods = (ArrayList<HashMap>) tempMap.get(userId);
                    if(foods!=null) {
                        ArrayList<Food> foodsList = new ArrayList<>();
                        for (int i = 0; i < foods.size(); i++) {
                            Food foodExtract = new Food((String) foods.get(i).get("foodName"), (Double) foods.get(i).get("caloriesPer100Grams"), (Double) foods.get(i).get("gramsConsumed"), (String) foods.get(i).get("date"));
                            foodsList.add(foodExtract);
                        }
                        foodsList.add(0, food);
                        HashMap<String, ArrayList<Food>> map = new HashMap<>();
                        map.put(userId, foodsList);

                        db.collection("usersFoods").document(userId).set(map);
                    }
                }
            }
        });

        return true;
    }

    public boolean removeFood(Food food) {
        db.collection("usersFoods").addSnapshotListener((value, error) -> {
            for(DocumentChange dc : value.getDocumentChanges()){
                if(dc.getType() == DocumentChange.Type.ADDED){
                    Map<String,Object> hashMap;
                    hashMap = dc.getDocument().getData();
                    ArrayList<Food> foods = (ArrayList<Food>) hashMap.get(userId);
                    foods.remove(food);
                    HashMap<String,ArrayList<Food>> map = new HashMap<>();
                    map.put(userId,foods);

                    db.collection("usersFoods").document(userId).set(map);
                }
            }
        });
        return true;
    }

    public LiveData<ArrayList<Food>> getFood() {

        DocumentReference docRef = db.collection("usersFoods").document(userId);
        fr = new FoodWrapper(docRef,userId);

        return fr.getFoods();
    }

    public boolean setUserParameters(double weight,double height,double limit,String gender){
        HashMap<String,Object> map = new HashMap<>();

        map.put("weight",weight);
        map.put("height",height);
        map.put("limit",limit);
        map.put("gender",gender);
        db.collection("usersParameters").document(userId).set(map);

        return true;
    }

    public LiveData<User> getUserData(){

        DocumentReference docRef = db.collection("usersParameters").document(userId);

        uw = new UserWrapper(docRef,userId);

        return uw;
    }

    public boolean addUserWeight(Weight weight){
        db.collection("usersWeight").addSnapshotListener((value, error) -> {
            for(DocumentChange dc : value.getDocumentChanges()){
                if(dc.getType() == DocumentChange.Type.ADDED){

                    Map<String,Object> hashMap;
                    hashMap = dc.getDocument().getData();
                    HashMap<String,Object> tempMap = (HashMap<String, Object>) hashMap;
                    ArrayList<HashMap> weightsReceived = (ArrayList<HashMap>) tempMap.get(userId);
                    if(weightsReceived != null) {
                        ArrayList<Weight> weightsList = new ArrayList<>();
                        for (int i = 0; i < weightsReceived.size(); i++) {
                            Weight weightExtract = new Weight((Double) weightsReceived.get(i).get("weight"), (String) weightsReceived.get(i).get("dateString"));
                            weightsList.add(weightExtract);
                        }
                        weightsList.add(0, weight);
                        HashMap<String, ArrayList<Weight>> map = new HashMap<>();
                        map.put(userId, weightsList);

                        db.collection("usersWeight").document(userId).set(map);
                    }
                }
            }
        });

        return true;
    }

    public LiveData<ArrayList<Weight>> getUserWeight(){
        DocumentReference docRef = db.collection("usersWeight").document(userId);

        ww = new WeightWrapper(docRef,userId);

        return ww;
    }
}
