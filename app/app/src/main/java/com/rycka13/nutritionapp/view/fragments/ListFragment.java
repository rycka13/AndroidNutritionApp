package com.rycka13.nutritionapp.view.fragments;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rycka13.nutritionapp.R;
import com.rycka13.nutritionapp.model.Model;
import com.rycka13.nutritionapp.model.ModelManager;
import com.rycka13.nutritionapp.model.adapters.FoodListAdapter;
import com.rycka13.nutritionapp.model.adapters.WeightListAdapter;
import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.data.Weight;
import com.rycka13.nutritionapp.model.instances.DatabaseInstance;
import com.rycka13.nutritionapp.model.instances.UserAuthInstance;
import com.rycka13.nutritionapp.viewModel.ListViewModel;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    RecyclerView foodList;
    FoodListAdapter foodListAdapter;
    Model model;


    public ListFragment(){
        // require a empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        model = new ModelManager();
        Application app = (Application) getActivity().getApplication();
        ListViewModel listViewModel = new ViewModelProvider(this).get(ListViewModel.class);

        View view = inflater.inflate(R.layout.list_fragment, container, false);


        TextView weeklyCalories = view.findViewById(R.id.weeklyCalories);
        TextView foodName = view.findViewById(R.id.foodName);
        TextView foodDate = view.findViewById(R.id.foodDate);
        TextView calories = view.findViewById(R.id.calories);
        foodList = view.findViewById(R.id.foodRecyclerView);


        foodList.hasFixedSize();
        foodList.setLayoutManager(new LinearLayoutManager(getActivity()));





        listViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user ->{

            listViewModel.getFood().observe(getViewLifecycleOwner(),foodsReceived ->{
                ArrayList<Food> foods = new ArrayList<>(foodsReceived);

                weeklyCalories.setText(listViewModel.getWeeklyCalories(foods));

                foodListAdapter = new FoodListAdapter(foods);

                foodList.setAdapter(foodListAdapter);

            });
        });



        return view;
    }
}
