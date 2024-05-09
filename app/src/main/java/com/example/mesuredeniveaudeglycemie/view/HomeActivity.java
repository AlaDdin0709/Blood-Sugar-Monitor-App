package com.example.mesuredeniveaudeglycemie.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mesuredeniveaudeglycemie.R;
import com.example.mesuredeniveaudeglycemie.controller.LoginController;

public class HomeActivity extends AppCompatActivity {

    private Button btnConsultation;
    private EditText etUserEmail;
    private EditText etPassword;
    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        etUserEmail.setText(loginController.getUserEmail()); // Affiche l'e-mail de l'utilisateur s'il est déjà connecté.

        btnConsultation.setOnClickListener(new View.OnClickListener() { // Définit un écouteur de clic pour le bouton de consultation.
            @Override
            public void onClick(View v) { // Méthode appelée lors du clic sur le bouton de consultation.
                String userEmail, password;
                boolean verifUserEmail = false, verifPassword = false;

                // Vérifie si le champ de l'e-mail n'est pas vide.
                if(!etUserEmail.getText().toString().isEmpty())
                    // Vérifie si l'e-mail saisi correspond à celui enregistré.
                    if(!loginController.getUserEmail().equals("") && !etUserEmail.getText().toString().equals(loginController.getUserEmail()))
                        Toast.makeText(HomeActivity.this, "Email incorrect", Toast.LENGTH_LONG).show();
                    else
                        verifUserEmail = true; // Marque l'e-mail comme valide.

                else
                    Toast.makeText(HomeActivity.this, "Veuillez saisir votre nom d'utilisateur !", Toast.LENGTH_SHORT).show();

                // Vérifie si le champ du mot de passe n'est pas vide.
                if(!etPassword.getText().toString().isEmpty())
                    // Vérifie si le mot de passe saisi correspond à celui enregistré.
                    if(!loginController.getUserEmail().equals("") && !etPassword.getText().toString().equals(loginController.getPassword()))
                        Toast.makeText(HomeActivity.this, "Mot de passe incorrect", Toast.LENGTH_LONG).show();
                    else
                        verifPassword = true; // Marque le mot de passe comme valide.

                else
                    Toast.makeText(HomeActivity.this, "Veuillez saisir votre mot de passe !", Toast.LENGTH_LONG).show();

                // Si les informations sont correctes, enregistre le nouvel utilisateur et ouvre l'activité principale.
                if(verifPassword && verifUserEmail) {
                    userEmail = etUserEmail.getText().toString();
                    password = etPassword.getText().toString();
                    loginController.createUser(userEmail, password, HomeActivity.this);
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Termine l'activité en cours.
                }
            }
        });
    }

    private void init()
    {
        loginController = LoginController.getInstance(HomeActivity.this);
        btnConsultation = (Button) findViewById(R.id.btnConsultation);
        etPassword = (EditText) findViewById(R.id.etUserPassword);
        etUserEmail = (EditText) findViewById(R.id.etUserEmail);
    }
}
