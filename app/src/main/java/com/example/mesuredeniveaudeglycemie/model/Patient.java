package com.example.mesuredeniveaudeglycemie.model;

import org.json.JSONArray;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Patient {

    private Date dateMesure; // Date de la mesure
    private int age; // Âge du patient
    private float valeurMesuree; // Valeur de glycémie mesurée
    private boolean isFasting; // Indique si le patient était à jeun
    private static String reponse; // Réponse générée à partir des données du patient

    // Constructeur par défaut (sans arguments)
    public Patient() {
    }

    // Constructeur principal (pour créer un nouvel objet Patient)
    // Flèche "Update" Controller --> Model
    public Patient(Date date, int age, float valeurMesuree, boolean isFasting) {
        dateMesure = date;
        this.age = age;
        this.valeurMesuree = valeurMesuree;
        this.isFasting = isFasting;

        // Calculer la réponse en fonction des données du patient
        calculer();
    }

    // Méthode privée pour calculer la réponse en fonction des données du patient
    private void calculer() {
        if (isFasting) { // Si le patient était à jeun
            if (age >= 13) { // Cas pour les patients âgés de 13 ans ou plus
                if (valeurMesuree < 5.0f) {
                    reponse = "Niveau de glycémie est trop bas";
                } else if (valeurMesuree >= 5.0f && valeurMesuree <= 7.2f) {
                    reponse = "Niveau de glycémie est normale";
                } else {
                    reponse = "Niveau de glycémie est trop élevé";
                }
            } else if (age >= 6 && age <= 12) { // Cas pour les patients âgés de 6 à 12 ans
                if (valeurMesuree < 5.0f) {
                    reponse = "Niveau de glycémie est trop bas";
                } else if (valeurMesuree >= 5.0f && valeurMesuree <= 10.0f) {
                    reponse = "Niveau de glycémie est normale";
                } else {
                    reponse = "Niveau de glycémie est trop élevé";
                }
            } else if (age < 6) { // Cas pour les patients âgés de moins de 6 ans
                if (valeurMesuree < 5.5f) {
                    reponse = "Niveau de glycémie est trop bas";
                } else if (valeurMesuree >= 5.5f && valeurMesuree <= 10.0f) {
                    reponse = "Niveau de glycémie est normale";
                } else {
                    reponse = "Niveau de glycémie est trop élevé";
                }
            }
        } else { // Si le patient n'était pas à jeun
            if (valeurMesuree < 5.5f) {
                reponse = "Niveau de glycémie est trop bas";
            } else if (valeurMesuree > 10.5f) {
                reponse = "Niveau de glycémie est trop élevé";
            } else {
                reponse = "Ce niveau de glycémie est normale après les repas";
            }
        }
    }

    // Accesseurs pour les attributs du patient
    public int getAge() {
        return age;
    }

    public float getValeurMesuree() {
        return valeurMesuree;
    }

    public boolean isFasting() {
        return isFasting;
    }

    public Date getDate() {
        return dateMesure;
    }

    // Méthode pour récupérer la réponse générée
    // Flèche "Notify" Model --> Controller
    public String getReponse() {
        return reponse;
    }

    // Mutateurs pour les attributs du patient
    public void setAge(int age) {
        this.age = age;
    }

    public void setValeurMesuree(float valeurMesuree) {
        this.valeurMesuree = valeurMesuree;
    }

    public void setFasting(boolean fasting) {
        isFasting = fasting;
        calculer(); // Recalculer la réponse si le statut de

    }

    /**
     * conversion du patient en format JSONArray
     * @return
     */
    public JSONArray convertToJSONArray (){
        List laListe = new ArrayList<>();
        laListe.add(dateMesure);
        laListe.add(age);
        laListe.add(isFasting);
        laListe.add(valeurMesuree);
        return new JSONArray(laListe);
    }



}