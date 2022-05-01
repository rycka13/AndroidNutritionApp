package com.rycka13.nutritionapp;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.rycka13.nutritionapp.model.DatabaseInstance;
import com.rycka13.nutritionapp.model.Food;
import com.rycka13.nutritionapp.model.User;
import com.rycka13.nutritionapp.model.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class SettingsFragment extends Fragment {
    public SettingsFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Application app = (Application) getActivity().getApplication();
        UserRepository userRep = UserRepository.getInstance(app);
        DatabaseInstance databaseInstance = DatabaseInstance.getInstance();

        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        Button setButton = view.findViewById(R.id.setButton);
        EditText weightT = view.findViewById(R.id.setWeight);
        EditText heightT = view.findViewById(R.id.setHeight);
        EditText genderT = view.findViewById(R.id.setGender);
        EditText calorieLimitT = view.findViewById(R.id.setCalorieLimit);

        setButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                userRep.getCurrentUser().observe(getViewLifecycleOwner(), user ->{
                    DatabaseInstance databaseInstance = DatabaseInstance.getInstance(user.getUid());
                    databaseInstance.getUserData().observe(getViewLifecycleOwner(),userParameters ->{

                        User userOld = new User();
                        if(weightT.getText().toString().isEmpty()){
                            userOld.setWeight(userParameters.getWeight());
                        }
                        else{
                            userOld.setWeight(Double.parseDouble(weightT.toString()));
                        }
                        if(heightT.getText().toString().isEmpty()){
                            userOld.setHeight(userParameters.getHeight());
                        }
                        else{
                            userOld.setHeight(Double.parseDouble(heightT.toString()));
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
                            userOld.setLimit(Double.parseDouble(calorieLimitT.toString()));
                        }

                        databaseInstance.setUserParameters(userOld.getWeight(),userOld.getHeight(),userOld.getLimit(),userOld.getGender());

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
