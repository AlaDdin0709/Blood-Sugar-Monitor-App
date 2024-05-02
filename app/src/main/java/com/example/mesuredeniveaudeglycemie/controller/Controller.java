package com.example.mesuredeniveaudeglycemie.controller;

import android.content.Context;

import com.example.mesuredeniveaudeglycemie.model.AccessLocal;
import com.example.mesuredeniveaudeglycemie.model.Patient;

import java.util.Date;

public class Controller {

    // Instance unique du contrôleur (Singleton)
    private static Controller instance = null;

    // Patient actuellement sélectionné
    private static Patient patient;

    // Accès aux données locales
    private static AccessLocal accessLocal;

    // Accès aux données distantes (non implémenté pour le moment)
    // private static AccessDistant accessDistant;

    private Controller() {
        super();
    }

    /**
     * Retourne l'instance unique du contrôleur.
     * Si aucune instance n'existe, elle est créée.
     *
     * @param context Le contexte de l'application
     * @return L'instance unique du contrôleur
     */
    public static final Controller getInstance(Context context) {
        if (Controller.instance == null) {
            Controller.instance = new Controller();
            accessLocal = new AccessLocal(context);

            // Récupérer le patient enregistré localement
            patient = accessLocal.getPatient();

            // Commentaire pour le code distant (non implémenté)
            // accessDistant = new AccessDistant();
            // accessDistant.envoi("dernier", new JSONArray());
        }
        return Controller.instance;
    }

    /**
     * Crée un nouvel enregistrement de patient.
     *
     * @param age L'âge du patient
     * @param valeurMesuree La valeur de glycémie mesurée
     * @param isFasting Indique si le patient était à jeun
     */
    public void createPatient(int age, float valeurMesuree, boolean isFasting) {
        patient = new Patient(new Date(), age, valeurMesuree, isFasting);

        // Enregistrer le patient localement
        accessLocal.insertPatient(patient);

        // Commentaire pour le code distant (non implémenté)
        // accessDistant.envoi("enreg", patient.convertToJSONArray());
    }

    /**
     * Récupère la réponse associée aux données du patient.
     * (L'implémentation de la génération de la réponse n'est pas fournie)
     *
     * @return La réponse basée sur les données du patient
     */
    public String getReponse() {
        return patient.getReponse();
    }

    /**
     * Retourne le patient actuellement sélectionné.
     *
     * @return Le patient courant
     */
    public static Patient getPatient() {
        return patient;
    }
}
