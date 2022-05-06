package com.rycka13.nutritionapp.view.fragments;

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
import androidx.lifecycle.ViewModelProvider;

import com.rycka13.nutritionapp.R;
import com.rycka13.nutritionapp.model.instances.DatabaseInstance;
import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.instances.UserAuthInstance;
import com.rycka13.nutritionapp.viewModel.AddViewModel;

import java.time.LocalDate;

public class AddFragment extends Fragment {
    public AddFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Application app = (Application) getActivity().getApplication();
//        UserAuthInstance userRep = UserAuthInstance.getInstance(app);
//        DatabaseInstance databaseInstance = DatabaseInstance.getInstance();

        View view = inflater.inflate(R.layout.add_fragment, container, false);

        Button addButton = view.findViewById(R.id.addButton);
        EditText foodName = view.findViewById(R.id.foodName);
        EditText caloriesPer = view.findViewById(R.id.caloriesPer);
        EditText grams = view.findViewById(R.id.grams);
        AddViewModel addViewModel = new ViewModelProvider(this).get(AddViewModel.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(foodName.getText().toString().isEmpty() || caloriesPer.getText().toString().isEmpty() || grams.getText().toString().isEmpty()){
                    Toast.makeText(app.getApplicationContext(),"You forgot to fill in all fields",Toast.LENGTH_SHORT).show();
                }
                else if(grams.getText().toString().equals(".")){
                    Toast.makeText(app.getApplicationContext(),"Dot is not a number sir",Toast.LENGTH_SHORT).show();
                }
                else {
                    Food foodToSave = new Food(foodName.getText().toString(), Double.parseDouble(caloriesPer.getText().toString()), Double.parseDouble(grams.getText().toString()), LocalDate.now().toString());
                    addViewModel.addFood(foodToSave);
                    Toast.makeText(app.getApplicationContext(), "Food added", Toast.LENGTH_SHORT).show();
                    foodName.setText("");
                    caloriesPer.setText("");
                    grams.setText("");
                }
            }
        });

        return view;
    }
}
