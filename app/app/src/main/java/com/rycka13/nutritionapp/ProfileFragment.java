package com.rycka13.nutritionapp;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.rycka13.nutritionapp.model.DatabaseInstance;
import com.rycka13.nutritionapp.model.UserRepository;

public class ProfileFragment extends Fragment {
    public ProfileFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Application app = (Application) getActivity().getApplication();
        UserRepository userRep = UserRepository.getInstance(app);
        DatabaseInstance databaseInstance = DatabaseInstance.getInstance();

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        Button button = view.findViewById(R.id.signOut);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRep.signOut();
            }

        });


        return view;
    }
}
