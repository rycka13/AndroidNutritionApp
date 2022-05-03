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
import com.rycka13.nutritionapp.model.data.Weight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeightWrapper extends LiveData<ArrayList<Weight>> {

    private ArrayList<Weight> weightList;
    private DocumentReference dbRef;
    private String userId;

    private final EventListener<DocumentSnapshot> listener = new EventListener<DocumentSnapshot>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
            Map<String,Object> hashMap;
            hashMap = value.getData();
            HashMap<String,Object> tempMap = (HashMap<String, Object>) hashMap;
            ArrayList<HashMap> weights = (ArrayList<HashMap>) tempMap.get(userId);
            setFoods(weights);
            setValue(weightList);
        }

    };

    @Override
    protected void onActive() {
        super.onActive();
        dbRef.addSnapshotListener(listener);
    }


    public WeightWrapper(DocumentReference dbRef,String userId){
        this.weightList = new ArrayList<>();
        this.dbRef = dbRef;
        this.userId = userId;
    }

    public LiveData<ArrayList<Weight>> getWeight() {
        return this;
    }

    public ArrayList<Weight> getWeightList(){
        return weightList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setFoods(ArrayList<HashMap> weights) {
        for (int i = 0; i<weights.size(); i++){
            Weight weight = new Weight((Double)weights.get(i).get("weight"),(String) weights.get(i).get("dateString"));

            this.weightList.add(weight);
        }

    }
}
