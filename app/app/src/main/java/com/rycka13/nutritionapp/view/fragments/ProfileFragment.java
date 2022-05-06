package com.rycka13.nutritionapp.view.fragments;

import android.app.Application;
import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.rycka13.nutritionapp.R;
import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.instances.DatabaseInstance;
import com.rycka13.nutritionapp.model.instances.UserAuthInstance;
import com.rycka13.nutritionapp.model.data.Weight;
import com.rycka13.nutritionapp.model.adapters.WeightListAdapter;
import com.rycka13.nutritionapp.view.MainActivity;
import com.rycka13.nutritionapp.view.SignInActivity;
import com.rycka13.nutritionapp.viewModel.ProfileViewModel;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    RecyclerView weightList;
    WeightListAdapter weightListAdapter;

    public ProfileFragment(){
        // require a empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Application app = (Application) getActivity().getApplication();
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        //UserAuthInstance userRep = UserAuthInstance.getInstance(app);

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        Button button = view.findViewById(R.id.signOut);
        TextView weight = view.findViewById(R.id.weight);
        TextView height = view.findViewById(R.id.height);
        TextView gender = view.findViewById(R.id.gender);
        weightList = view.findViewById(R.id.weightRecyclerView);


        weightList.hasFixedSize();
        weightList.setLayoutManager(new LinearLayoutManager(getActivity()));





        profileViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user ->{

            //DatabaseInstance databaseInstance = DatabaseInstance.getInstance(user.getUid());
            profileViewModel.getUserData().observe(getViewLifecycleOwner(),userParameters ->{

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        FirebaseAuth.getInstance().signOut();
                        //databaseInstance.setInstance(null);
                        startActivity(new Intent(app, SignInActivity.class));
//                MainActivity main = (MainActivity) getActivity();
//                main.signOut(view);
//                startActivity(new Intent(app, SignInActivity.class));
                    }

                });

                weight.setText(userParameters.getWeight().toString());
                height.setText(userParameters.getHeight().toString());
                gender.setText(userParameters.getGender());
                profileViewModel.getUserWeight().observe(getViewLifecycleOwner(),weightsList -> {

                    ArrayList<Weight> weights = new ArrayList<>(weightsList);

                    weightListAdapter = new WeightListAdapter(weights);

                    weightList.setAdapter(weightListAdapter);
                });

            });
        });






        return view;
    }
}
