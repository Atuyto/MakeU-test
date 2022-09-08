package com.atuyto.makeu.Fragments;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.atuyto.makeu.MainActivity;
import com.atuyto.makeu.PopUp.LoginPopUp;
import com.atuyto.makeu.R;
import com.atuyto.makeu.UserInformation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.text.DecimalFormat;


public class HomeFragment extends Fragment {

    private View view;
    private DatabaseReference mDatabase;
    private String uid, email, weightStr;
    private float size;
    private int GoalWeightUser = 0;
    public static TextView textpas;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        email = UserInformation.Email;
        weightStr = UserInformation.Weight;
        String taille = UserInformation.Size;
        size = Float.parseFloat(taille);




        GoalWeightUser = 61;



        TextView poidCorpsText = view.findViewById(R.id.poidsCorpsText);
        TextView tailleText = view.findViewById(R.id.sizeText);

        TextView textPas = view.findViewById(R.id.ratio_pas);

        poidCorpsText.setText("Poids : ".concat(weightStr).concat(" ").concat("kg"));
        tailleText.setText("Taille : ".concat(taille).concat(" cm"));




        CalculRatioPoids();
        CalculIMC();

        return view;

    }

    private void CalculIMC(){
        TextView imcText = view.findViewById(R.id.IMCText);
        float sizeM = size / 100;
        float weightF = Float.parseFloat(weightStr);
        int IMC = (int) (weightF / Math.pow(sizeM, 2));
        imcText.setText("I.M.C : ".concat(String.valueOf(IMC)));

    }

    private void CalculRatioPoids(){
        ProgressBar ProgressBarPoids = view.findViewById(R.id.ProgressBarPoid);
        TextView ratioPerc = view.findViewById(R.id.rattioPoidPerc);
        TextView ratioPoids = view.findViewById(R.id.ratioPoid);
        float weightInt = Float.parseFloat(weightStr);
        float resultFloat = (weightInt / GoalWeightUser) * 100;
        int result = (int) resultFloat;
        ratioPoids.setText(weightStr.concat(" / ").concat(String.valueOf(GoalWeightUser)).concat(" Kg"));
        ratioPerc.setText(String.valueOf(result).concat(" %"));
        ProgressBarPoids.setProgress(result);
    }




}
