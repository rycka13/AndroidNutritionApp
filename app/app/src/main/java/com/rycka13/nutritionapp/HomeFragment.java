package com.rycka13.nutritionapp;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rycka13.nutritionapp.model.User;
import com.rycka13.nutritionapp.model.UserRepository;

public class HomeFragment extends Fragment{
    public HomeFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Application app = (Application) getActivity().getApplication();
        UserRepository userRep = UserRepository.getInstance(app);

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ProgressBar progressBar;
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setProgress(20);

        TextView homeTextView = view.findViewById(R.id.homeUserName);

        userRep.getCurrentUser().observe(getViewLifecycleOwner(), user ->{
            homeTextView.setText("Welcome back, \n" + user.getDisplayName());
        });
        return view;
    }
}
