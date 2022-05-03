package com.rycka13.nutritionapp.view.fragments;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.rycka13.nutritionapp.R;
import com.rycka13.nutritionapp.model.instances.DatabaseInstance;
import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.Model;
import com.rycka13.nutritionapp.model.ModelManager;
import com.rycka13.nutritionapp.model.instances.UserAuthInstance;

import java.util.ArrayList;

public class HomeFragment extends Fragment{
    public HomeFragment(){
        // require a empty public constructor
    }

    //TODO change weight to BMI and height to weight loss/gain scale

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Application app = (Application) getActivity().getApplication();
        UserAuthInstance userRep = UserAuthInstance.getInstance(app);
        Model model = new ModelManager();

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ProgressBar progressBar;
        TextView calories;
        TextView weight;
        TextView height;
        progressBar = view.findViewById(R.id.progressBar);
        calories = view.findViewById(R.id.calories);
        weight = view.findViewById(R.id.weightValue);
        height = view.findViewById(R.id.heightValue);


        TextView homeTextView = view.findViewById(R.id.homeUserName);

        userRep.getCurrentUser().observe(getViewLifecycleOwner(), user ->{
            homeTextView.setText("Welcome back, \n" + user.getDisplayName());

            DatabaseInstance databaseInstance = DatabaseInstance.getInstance(user.getUid());
            databaseInstance.getUserData().observe(getViewLifecycleOwner(),userParameters ->{
                if(userParameters.getHeight() == 0 || userParameters.getWeight() ==0){
                    weight.setText("Not enough\n data");
                }
                else{
                    weight.setText(model.getBMI(userParameters.getHeight(), userParameters.getWeight()).toString());
                }

                databaseInstance.getFood().observe(getViewLifecycleOwner(),foods -> {
                    progressBar.setProgress(model.getPercentage(model.getTodaysCalories((ArrayList<Food>) foods),userParameters.getLimit()));
                    calories.setText(model.getTodaysCalories((ArrayList<Food>) foods) + "/\n" + userParameters.getLimit());
                });

                databaseInstance.getUserWeight().observe(getViewLifecycleOwner(),weights ->{
                    if(weights.size() < 2){
                        height.setText("Not enough\n data");
                    }
                    else{
                        Double change = model.getWeightChange(weights.get(1).getWeight(),weights.get(0).getWeight());
                        if(change>0){
                            height.setText("+" +change.toString());
                        }
                        if(change<0){
                            height.setText("-" + change.toString());
                        }
                        else {

                            height.setText(change.toString());

                        }
                    }

                });

            });
        });



        return view;
    }
}
