package com.rycka13.nutritionapp;

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
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rycka13.nutritionapp.model.DatabaseInstance;
import com.rycka13.nutritionapp.model.Food;
import com.rycka13.nutritionapp.model.Model;
import com.rycka13.nutritionapp.model.ModelManager;
import com.rycka13.nutritionapp.model.User;
import com.rycka13.nutritionapp.model.UserRepository;

import java.util.ArrayList;

public class HomeFragment extends Fragment{
    public HomeFragment(){
        // require a empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Application app = (Application) getActivity().getApplication();
        UserRepository userRep = UserRepository.getInstance(app);
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

                weight.setText(userParameters.getWeight().toString());
                height.setText(userParameters.getHeight().toString());
                databaseInstance.getFood().observe(getViewLifecycleOwner(),foods -> {
                    progressBar.setProgress(model.getPercentage(model.getTodaysCalories((ArrayList<Food>) foods),userParameters.getLimit()));
                    calories.setText(model.getTodaysCalories((ArrayList<Food>) foods) + "/\n" + userParameters.getLimit());
                });

            });
        });



        return view;
    }
}
