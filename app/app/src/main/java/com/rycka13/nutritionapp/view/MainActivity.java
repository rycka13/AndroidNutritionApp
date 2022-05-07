package com.rycka13.nutritionapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rycka13.nutritionapp.view.fragments.AddFragment;
import com.rycka13.nutritionapp.view.fragments.HomeFragment;
import com.rycka13.nutritionapp.view.fragments.ListFragment;
import com.rycka13.nutritionapp.view.fragments.ProfileFragment;
import com.rycka13.nutritionapp.view.fragments.SettingsFragment;
import com.rycka13.nutritionapp.viewModel.MainActivityViewModel;
import com.rycka13.nutritionapp.R;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    private MainActivityViewModel viewModel;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init();
        checkIfSignedIn();
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);



    }

    AddFragment addFragment = new AddFragment();
    HomeFragment homeFragment = new HomeFragment();
    ListFragment listFragment = new ListFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.home){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,homeFragment).commit();
            return true;
        }
        else if (item.getItemId() == R.id.add){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,addFragment).commit();
            return true;
        }
        else if (item.getItemId() == R.id.list){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,listFragment).commit();
            return true;
        }
        else if (item.getItemId() == R.id.profile){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,profileFragment).commit();
            return true;
        }
        else if (item.getItemId() == R.id.settings){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,settingsFragment).commit();
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {

            } else
                startLoginActivity();
        });


    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    public void signOut(View v) {
        viewModel.signOut();

        startLoginActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        startLoginActivity();
    }
}