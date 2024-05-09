package com.example.mesuredeniveaudeglycemie.model.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PatientBaseSQLite extends SQLiteOpenHelper {

    // Nom de la table de base de données
    private static final String TABLE_NAME = "table_patients";

    // Colonne pour la date de mesure
    private static final String COL_DATE_MESURE = "DATE_MESURE";

    // Colonne pour l'âge
    private static final String COL_AGE = "AGE";

    // Colonne pour la valeur mesurée
    private static final String COL_VALEUR_MESUREE = "VALEUR_MESUREE";

    // Colonne pour indiquer si le patient était à jeun (1) ou non (0)
    private static final String COL_IS_FASTING = "IS_FASTING";

    // Requête SQL pour créer la table
    private static final String CREATE_TAB = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_DATE_MESURE + " TEXT PRIMARY KEY, " +
            COL_AGE + " INTEGER NOT NULL, " +
            COL_VALEUR_MESUREE + " REAL NOT NULL, " +
            COL_IS_FASTING + " INTEGER NOT NULL CHECK (" + COL_IS_FASTING + " IN (0, 1)));";


    public PatientBaseSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création de la table en exécutant la requête SQL
        db.execSQL(CREATE_TAB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Mise à jour de la table en cas de changement de version
        // Supprime l'ancienne table et recrée la nouvelle
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }
}
