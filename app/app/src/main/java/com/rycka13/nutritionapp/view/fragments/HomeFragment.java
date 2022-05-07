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
import androidx.lifecycle.ViewModelProvider;

import com.rycka13.nutritionapp.R;
import com.rycka13.nutritionapp.viewModel.HomeViewModel;

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
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
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

        homeViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user ->{
            homeTextView.setText("Welcome back, \n" + user.getDisplayName());
            homeViewModel.setDatabaseInstance(user.getUid());

            //DatabaseInstance databaseInstance = DatabaseInstance.getInstance(user.getUid());
            homeViewModel.getUserData().observe(getViewLifecycleOwner(),userParameters ->{
                if(userParameters.getHeight() == 0 || userParameters.getWeight() ==0){
                    weight.setText("Not enough\n data");
                }
                else{
                    weight.setText(homeViewModel.getBMI(userParameters.getHeight(), userParameters.getWeight()));
                }

                homeViewModel.getFood().observe(getViewLifecycleOwner(),foods -> {
                    progressBar.setProgress(homeViewModel.getPercentage(foods,userParameters.getLimit()));
                    calories.setText(homeViewModel.getTodaysCalories(foods,userParameters.getLimit()));
                });

                homeViewModel.getUserWeight().observe(getViewLifecycleOwner(),weights ->{
                    if(weights.size() < 2){
                        height.setText("Not enough\n data");
                    }
                    else{
                        Double change = homeViewModel.getWeightChange(weights.get(1).getWeight(),weights.get(0).getWeight());
                        if(change>0){
                            height.setText("+" +change.toString());
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
