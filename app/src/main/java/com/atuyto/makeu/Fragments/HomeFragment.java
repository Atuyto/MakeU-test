package com.atuyto.makeu.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.atuyto.makeu.PopUp.LoginPopUp;
import com.atuyto.makeu.R;
import com.atuyto.makeu.UserInformation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.text.DecimalFormat;


public class HomeFragment extends Fragment {

    private DatabaseReference mDatabase;
    private String uid;
    private String email;
    private Query mPostReference;
    private String weight;
    private float size;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        email = UserInformation.Email;
        weight = UserInformation.Weight;
        String taille = UserInformation.Size;
        size = Float.parseFloat(taille);


        TextView poidCorpsText = view.findViewById(R.id.poidsCorpsText);
        TextView imcText = view.findViewById(R.id.IMCText);
        TextView tailleText = view.findViewById(R.id.sizeText);



        poidCorpsText.setText("Poids : ".concat(weight).concat(" ").concat("kg"));
        imcText.setText("I.M.C : ".concat(String.valueOf(CalculIMC())));
        tailleText.setText("Taille : ".concat(taille).concat(" cm"));


        return view;

    }

    private int CalculIMC(){
        float sizeM = size / 100;
        float weightF = Float.parseFloat(weight);
        int IMC = (int) (weightF / Math.pow(sizeM, 2));
        return IMC;

    }

}
