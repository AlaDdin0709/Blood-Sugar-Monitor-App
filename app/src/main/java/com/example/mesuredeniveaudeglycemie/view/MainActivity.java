package com.example.mesuredeniveaudeglycemie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import  android.widget.EditText;
import android.widget.Toast;

import com.example.mesuredeniveaudeglycemie.R;
import com.example.mesuredeniveaudeglycemie.controller.Controller;

public class MainActivity extends AppCompatActivity {
    private TextView tvAge , tvReponse ,  ageValue;
    private SeekBar sbAge;
    private RadioButton rbtOui , rbtNon;
    private Button btnConsulter;
    private  EditText etValeur;
    private Controller controller;

    private void init()
    {
        controller = Controller.getInstance();
        tvAge = findViewById(R.id.tvAge);
        tvReponse = findViewById(R.id.tvReponse);
        sbAge = findViewById(R.id.sbAge);
        btnConsulter = findViewById(R.id.btnConsulter);
        rbtOui = findViewById(R.id.rbtOui);
        rbtNon = findViewById(R.id.rbtNon);
        etValeur = findViewById(R.id.etValeur);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAge.setText("VOtre age: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age;
                float valeur;
                Log.i("Information", "button cliqué");
                boolean verifAge = false, verifValeur = false;
                if(sbAge.getProgress()!=0)
                    verifAge = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre age !", Toast.LENGTH_SHORT).show();
                if(!etValeur.getText().toString().isEmpty())
                    verifValeur = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre valeur mesurée !", Toast.LENGTH_LONG).show();
                if(verifAge && verifValeur)
                {
                    age = sbAge.getProgress();
                    valeur = Float.valueOf(etValeur.getText().toString());
                    controller.createPatient(age , valeur , rbtOui.isChecked());
                    tvReponse.setText(controller.getResult());
                }
            }
        });
    }


    }