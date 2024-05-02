package com.example.mesuredeniveaudeglycemie.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import com.example.mesuredeniveaudeglycemie.model.outils.PatientBaseSQLite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AccessLocal {

    // Version de la base de données
    private static final int VERSION = 1;

    // Nom du fichier de la base de données
    private static final String NOM_BDD = "MGSQLiteDatabase.db";

    // Nom de la table
    private static final String TABLE_NAME = "table_patients";

    // Colonne pour la date de mesure
    private static final String COL_DATE_MESURE = "DATE_MESURE";
    private static final int NUM_COL_DATE_MESURE = 0;

    // Colonne pour l'âge
    private static final String COL_AGE = "AGE";
    private static final int NUM_COL_AGE = 1;

    // Colonne pour la valeur mesurée
    private static final String COL_VALEUR_MESUREE = "VALEUR_MESUREE";
    private static final int NUM_COL_VALEUR_MESUREE = 2;

    // Colonne pour indiquer si le patient était à jeun (1) ou non (0)
    private static final String COL_IS_FASTING = "IS_FASTING";
    private static final int NUM_COL_IS_FASTING = 3;


    private SQLiteDatabase bd; // Base de données SQLite
    private PatientBaseSQLite patients; // Helper pour la gestion de la base de données

    public AccessLocal(Context context) {
        // Création d'un helper pour la gestion de la base de données
        patients = new PatientBaseSQLite(context, NOM_BDD, null, VERSION);
    }

    public void openForWrite() {
        // Ouverture de la base de données en mode écriture
        bd = patients.getWritableDatabase();
    }

    public void openForRead() {
        // Ouverture de la base de données en mode lecture
        bd = patients.getReadableDatabase();
    }

    public void close() {
        // Fermeture de la base de données
        bd.close();
    }

    public SQLiteDatabase getBd() {
        // Renvoyer l'objet de la base de données
        return bd;
    }

    public void insertPatient(Patient patient) {

        // Requête d'insertion d'un patient dans la table
        // COL_DATE_MESURE (TEXT PRIMARY KEY)
        // COL_AGE (INTEGER NOT NULL)
        // COL_VALEUR_MESUREE (REAL NOT NULL)
        // COL_IS_FASTING (INTEGER NOT NULL) (0 ou 1)

        openForWrite();
        ContentValues content = new ContentValues(); // Conteneur de valeurs pour l'insertion

        // Formatage de la date pour l'enregistrement
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String dateString = dateFormat.format(patient.getDate());
        content.put(COL_DATE_MESURE, dateString);

        content.put(COL_AGE, patient.getAge());
        content.put(COL_VALEUR_MESUREE, patient.getValeurMesuree());

        // Conversion de l'état à jeun en entier (0 ou 1) pour la base de données
        if (patient.isFasting()) {
            content.put(COL_IS_FASTING, 1);
        } else {
            content.put(COL_IS_FASTING, 0);
        }

        long result = bd.insert(TABLE_NAME, null, content);
        if (result == -1) {
            Log.e("Error", "Échec de l'insertion");
        } else {
            Log.i("Info", "Insertion réussie");
        }

        close();
    }

    public Patient getPatient() {

        // Requête pour récupérer le dernier patient enregistré
        // COL_DATE_MESURE (TEXT PRIMARY KEY)
        // COL_AGE (INTEGER NOT NULL)



        openForRead();
        Cursor c = bd.query(
                TABLE_NAME,
                new String[] {COL_DATE_MESURE, COL_AGE, COL_VALEUR_MESUREE, COL_IS_FASTING},
                null, // selection: filter which rows to return; null returns all rows
                null, // selectionArgs: arguments for the selection; null if no arguments
                null, // groupBy: grouping the results; null means no grouping
                null, // having: conditions for the groups; null if no conditions
                COL_DATE_MESURE + " DESC", // orderBy: order the results by date_mesure in descending order
                "1" // limit: limit the results to just one row (the most recent one)
        );

        return cursorToChapter (c);
    }


    public Patient cursorToChapter(Cursor c) {
        // Vérifie si le curseur ne contient aucune ligne
        if (c.getCount() == 0) {
            c.close();
            return null;
        }

        // Vérifie si le curseur est valide et si on peut se positionner sur la première ligne
        if (c != null && c.moveToFirst()) {

            // Récupère l'âge du patient à partir de la colonne "AGE"
            int age = c.getInt(c.getColumnIndexOrThrow(COL_AGE));

            // Récupère la valeur mesurée du patient à partir de la colonne "VALEUR_MESUREE"
            float valeurMesuree = c.getFloat(c.getColumnIndexOrThrow(COL_VALEUR_MESUREE));

            // Récupère l'état à jeun du patient à partir de la colonne "IS_FASTING" (1 pour à jeun, 0 pour non)
            boolean isFasting = c.getInt(c.getColumnIndexOrThrow(COL_IS_FASTING)) == 1;

            // Crée un nouvel objet Patient avec les valeurs récupérées
            Patient lastPatient = new Patient(new Date(), age, valeurMesuree, isFasting);

            // Fermeture du curseur
            c.close();

            // Renvoie l'objet Patient créé
            return lastPatient;
        }

        // Si aucune ligne n'est trouvée, renvoie null
        return null;
    }




}
