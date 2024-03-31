package com.example.mesuredeniveaudeglycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import  android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private TextView tvAge , tvReponse ,  ageValue;
    private SeekBar sbAge;
    private RadioButton rbtOui , rbtNon;
    private Button btnConsulter;
    private  EditText etValeur;

    private void init()
    {
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
            public void onClick(View v) {
                calculer(v);
            }

        });
    }
        public void calculer(View view) {
            String valeurStr = etValeur.getText().toString();
            double valeur = Double.parseDouble(valeurStr);
            int age = sbAge.getProgress();
            if (valeur==0.0 || age==0 || valeurStr.isEmpty())
            {
                tvReponse.setText("Erreur!!!");
            }
            else {
                if (rbtOui.isChecked()) {
                    if ((age < 7) && (valeur >= 5.5 && valeur <= 10)) {
                        tvReponse.setText("niveau de glycemi est normal");
                    } else if ((age < 7) && (valeur < 5.5)) {
                        tvReponse.setText("niveau de glycemi est trop bas");
                    } else
                    {
                    if (age >= 7 && age < 13 && (valeur >= 5 && valeur <= 10)) {
                        tvReponse.setText("niveau de glycemi est normal");
                    } else if (age >= 7 && age < 13 && (valeur < 5)) {
                        tvReponse.setText("niveau de glycemi est trop bas");
                    } else
                    {

                    if (age >= 13 && (valeur >= 5 && valeur <= 7.2)) {
                        tvReponse.setText("niveau de glycemi est normal");
                    } else if (age >= 13 && (valeur < 5)) {
                        tvReponse.setText("niveau de glycemi est tros bas");
                    } else
                        tvReponse.setText("niveau de glycemi est tres éleves");
                }}}

                else if (rbtNon.isChecked()) {
                    if (age >= 13 && (valeur <= 10.5)) {
                        tvReponse.setText("niveau de glycemi est normal");
                    } else
                        tvReponse.setText("niveau de glycemi est tres éleves");
                } else {
                    tvReponse.setText("Erreuuur!!!!");
                }
            }
        }
    }