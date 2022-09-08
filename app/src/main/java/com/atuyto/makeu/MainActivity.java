package com.atuyto.makeu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.atuyto.makeu.Fragments.EntreinementFragment;
import com.atuyto.makeu.Fragments.HomeFragment;
import com.atuyto.makeu.Fragments.SettingFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements SensorEventListener  {

    // variable
    private int CountFragment = 1;
    private NavigationBarView navigationview;
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean run= false;
    private float stepsCount;
    private int stepcount;
    TextView titlepage;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout, HomeFragment.class, null).commit();


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if(ContextCompat.checkSelfPermission(this,

                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){ //ask for permission

            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);

        }


        titlepage = findViewById(R.id.pageTitle);


        // initialisation des variable
        Button settingButton = findViewById(R.id.setting_button);
        NavigationBarView navigationview = findViewById(R.id.bottom_nav);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            run = true;
        }



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

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null)
        {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null)
            sensorManager.unregisterListener(this, sensor);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor == sensor){
            stepcount = (int) sensorEvent.values[0];


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}