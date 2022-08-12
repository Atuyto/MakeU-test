package com.atuyto.makeu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.atuyto.makeu.Fragments.EntreinementFragment;
import com.atuyto.makeu.Fragments.HomeFragment;
import com.atuyto.makeu.Fragments.SettingFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity{

    // variable
    private int CountFragment = 1;
    private NavigationBarView navigationview;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout, HomeFragment.class, null).commit();


        // initialisation des variable
        Button settingButton = findViewById(R.id.setting_button);
        NavigationBarView navigationview = findViewById(R.id.bottom_nav);


        //changement de fragment en fonction de l'icon dans la barre de navigation
        navigationview.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_page: {
                    CountFragment = 1;
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout, HomeFragment.class, null).commit();
                    return true;
                }
                case R.id.Entreinement_page:{
                    CountFragment = 3;
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout, EntreinementFragment.class, null).commit();
                    return true;
                }
                default:
                    return false;
            }
        });

        // verification --> si le bouton setting est selectionner --> fragment paramettre
        settingButton.setOnClickListener(view -> {
            CountFragment = 2;
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout, SettingFragment.class, null).commit();
        });
    }

    // Gestion du bouton retour
    @Override
    public void onBackPressed() {

        if(CountFragment == 2 || CountFragment ==3){
            CountFragment = 1;
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout, HomeFragment.class, null).commit();
            
        }
        else
            super.onBackPressed();
    }
}