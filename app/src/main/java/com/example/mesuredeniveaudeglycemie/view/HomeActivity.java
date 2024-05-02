package com.example.mesuredeniveaudeglycemie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mesuredeniveaudeglycemie.R;

public class HomeActivity extends AppCompatActivity {

    private Button btnConsultation; // Button to initiate consultation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Set the layout

        // Initialize UI components
        init();

        // Button click listener for consultation
        btnConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to launch MainActivity
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

                // Close the current activity
                finish();
            }
        });
    }

    private void init() {
        btnConsultation = findViewById(R.id.btnConsultation); // Find the button by its ID
    }
}
