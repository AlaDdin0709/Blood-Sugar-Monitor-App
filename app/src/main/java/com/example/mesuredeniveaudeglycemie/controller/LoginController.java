package com.example.mesuredeniveaudeglycemie.controller;

import static android.content.Context.MODE_PRIVATE;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.mesuredeniveaudeglycemie.model.User;

public class LoginController {
    private static LoginController instance = null; // Déclare une variable statique pour l'instance singleton de LoginController.
    private static final String SHARED_PREFS = "mySharedPrefs"; // Déclare une constante pour le nom du fichier de préférences partagées.
    private static User user; // Déclare une variable statique pour l'utilisateur.

    // Constructeur privé pour empêcher l'instanciation depuis l'extérieur.
    private LoginController(){
        super();
    }

    // Méthode statique pour obtenir l'instance singleton de LoginController.
    public static final LoginController getInstance(Context context){
        if(LoginController.instance == null){
            LoginController.instance = new LoginController();
        }
        recapUser(context); // Récupère les informations de l'utilisateur depuis les préférences partagées.
        return LoginController.instance;
    }

    // Méthode pour créer un nouvel utilisateur avec un email et un mot de passe.
    public void createUser(String userEmail, String password, Context context){
        user = new User(userEmail,password); // Crée une nouvelle instance de User.
        SharedPreferences sharedPreferences= context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",userEmail); // Enregistre l'e-mail dans les préférences partagées.
        editor.putString("password", password); // Enregistre le mot de passe dans les préférences partagées.
        editor.apply(); // Applique les modifications.
    }

    // Méthode pour récupérer les informations de l'utilisateur depuis les SharedPreferences et peupler l'instance User.
    public static void recapUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email","");
        String password = sharedPreferences.getString("password","");
        user = new User(userEmail,password); // Peuple l'instance User avec les données enregistrées.
    }

    // Méthode pour obtenir l'e-mail de l'utilisateur.
    public String getUserEmail() {
        return user.getUserEmail();
    }

    // Méthode pour obtenir le mot de passe de l'utilisateur.
    public String getPassword() {
        return user.getPassword();
    }
}
