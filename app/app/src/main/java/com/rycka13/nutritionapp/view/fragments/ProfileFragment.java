package com.rycka13.nutritionapp.view.fragments;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rycka13.nutritionapp.R;
import com.rycka13.nutritionapp.model.instances.DatabaseInstance;
import com.rycka13.nutritionapp.model.instances.UserAuthInstance;
import com.rycka13.nutritionapp.model.data.Weight;
import com.rycka13.nutritionapp.model.adapters.WeightListAdapter;

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
        UserAuthInstance userRep = UserAuthInstance.getInstance(app);
        DatabaseInstance databaseInstance = DatabaseInstance.getInstance();

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        Button button = view.findViewById(R.id.signOut);


        weightList = view.findViewById(R.id.weightRecyclerView);
        weightList.hasFixedSize();
        weightList.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Weight> weights = new ArrayList<>();

        //TODO get the weights
        weights.add(new Weight(80,"2022-04-29"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(50,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(20,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(80,"2022-04-28"));
        weights.add(new Weight(10,"2022-04-27"));

        weightListAdapter = new WeightListAdapter(weights);

        weightList.setAdapter(weightListAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRep.signOut();
            }

        });


        return view;
    }
}
