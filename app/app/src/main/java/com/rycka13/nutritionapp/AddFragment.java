package com.rycka13.nutritionapp;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.rycka13.nutritionapp.model.DatabaseInstance;
import com.rycka13.nutritionapp.model.Food;
import com.rycka13.nutritionapp.model.UserRepository;

import java.time.LocalDate;

public class AddFragment extends Fragment {
    public AddFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Application app = (Application) getActivity().getApplication();
        UserRepository userRep = UserRepository.getInstance(app);
        DatabaseInstance databaseInstance = DatabaseInstance.getInstance();

        View view = inflater.inflate(R.layout.add_fragment, container, false);

        Button addButton = view.findViewById(R.id.addButton);
        EditText foodName = view.findViewById(R.id.foodName);
        EditText caloriesPer = view.findViewById(R.id.caloriesPer);
        EditText grams = view.findViewById(R.id.grams);

        addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Food foodToSave = new Food(foodName.getText().toString(),Double.parseDouble(caloriesPer.getText().toString()),Double.parseDouble(grams.getText().toString()), LocalDate.now().toString());
                databaseInstance.addFood(foodToSave);
                System.out.println("BUTTON PRESSED");
            }
        });

        return view;
    }
}
