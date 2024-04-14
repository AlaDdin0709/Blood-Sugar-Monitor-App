package com.example.mesuredeniveaudeglycemie.model;

public class Patient {

    private int age;
    private float valeur;
    private boolean isFasting;
    private String result;


    //Flèche "Update" Controller --> Model

    public Patient(int age, float valeur, boolean isFasting) {
        this.age = age;
        this.valeur = valeur;
        this.isFasting = isFasting;
        setResult();
    }

    public void setResult() {

        if (isFasting) {
            if ((age < 7) && (valeur >= 5.5 && valeur <= 10)) {
                result = "niveau de glycemi est normal";
            } else if ((age < 7) && (valeur < 5.5)) {
                result = "niveau de glycemi est trop bas";
            } else {
                if (age >= 7 && age < 13 && (valeur >= 5 && valeur <= 10)) {
                    result = "niveau de glycemi est normal";
                } else if (age >= 7 && age < 13 && (valeur < 5)) {
                    result = "niveau de glycemi est trop bas";
                } else {

                    if (age >= 13 && (valeur >= 5 && valeur <= 7.2)) {
                        result = "niveau de glycemi est normal";
                    } else if (age >= 13 && (valeur < 5)) {
                        result = "niveau de glycemi est tros bas";
                    } else
                        result = "niveau de glycemi est tres éleves";
                }
            }
        } else if (!isFasting) {
            if (age >= 13 && (valeur <= 10.5)) {
                result = "niveau de glycemi est normal";
            } else
                result = "niveau de glycemi est tres éleves";
        } else {
            result = "Erreuuur!!!!";
        }
    }

    //Flèche "Modify" Model --> Controller
    public String getResult() {
        return result;
    }
}

