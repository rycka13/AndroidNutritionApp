package com.rycka13.nutritionapp.view.fragments;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rycka13.nutritionapp.R;
import com.rycka13.nutritionapp.model.data.Weight;
import com.rycka13.nutritionapp.model.data.User;
import com.rycka13.nutritionapp.viewModel.SettingsViewModel;

import java.time.LocalDate;

public class SettingsFragment extends Fragment {
    public SettingsFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Application app = (Application) getActivity().getApplication();

        SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);



        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        Button setButton = view.findViewById(R.id.setButton);
        EditText weightT = view.findViewById(R.id.setWeight);
        EditText heightT = view.findViewById(R.id.setHeight);
        EditText genderT = view.findViewById(R.id.setGender);
        EditText calorieLimitT = view.findViewById(R.id.setCalorieLimit);

        settingsViewModel.getUserData().observe(getViewLifecycleOwner(),userParameters ->{
            weightT.setHint(userParameters.getWeight().toString());
            heightT.setHint(userParameters.getHeight().toString());
            genderT.setHint(userParameters.getGender().toString());
            calorieLimitT.setHint(userParameters.getLimit().toString());
        });

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                settingsViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user ->{

                    settingsViewModel.getUserData().observe(getViewLifecycleOwner(),userParameters ->{

                        User userOld = new User();
                        if(weightT.getText().toString().isEmpty()){
                            userOld.setWeight(userParameters.getWeight());
                        }
                        else{
                            userOld.setWeight(Double.parseDouble(weightT.getText().toString()));
                            settingsViewModel.addUserWeight(new Weight(Double.parseDouble(weightT.getText().toString()), LocalDate.now().toString()));
                        }
                        if(heightT.getText().toString().isEmpty()){
                            userOld.setHeight(userParameters.getHeight());
                        }
                        else{
                            userOld.setHeight(Double.parseDouble(heightT.getText().toString()));
                        }
                        if(genderT.getText().toString().isEmpty()){
                            userOld.setGender(userParameters.getGender());
                        }
                        else{
                            userOld.setGender(genderT.getText().toString());
                        }
                        if(calorieLimitT.getText().toString().isEmpty()){
                            userOld.setLimit(userParameters.getLimit());
                        }
                        else{
                            userOld.setLimit(Double.parseDouble(calorieLimitT.getText().toString()));
                        }

                        settingsViewModel.setUserParameters(userOld.getWeight(),userOld.getHeight(),userOld.getLimit(),userOld.getGender());

                        weightT.setText("");
                        heightT.setText("");
                        genderT.setText("");
                        calorieLimitT.setText("");

                    });
                });

            }
        });

        return view;
    }
}
