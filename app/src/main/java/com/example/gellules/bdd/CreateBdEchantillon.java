package com.example.gellules.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateBdEchantillon extends SQLiteOpenHelper {

    private static final String TABLE_ECHANT = "echantillons";
    private static final String TABLE_MOUVEMENT = "mouvement";

    static final String COL_ID = "_id";

    private static final String COL_CODE = "CODE";
    private static final String COL_LIB = "LIB";
    private static final String COL_STOCK = "STOCK";

    // Colonnes de la table mouvement
    private static final String COL_MOUVEMENT_ID = "_id";
    private static final String COL_MOUVEMENT_TYPE = "TYPE";
    private static final String COL_MOUVEMENT_CODE = "CODE";
    private static final String COL_MOUVEMENT_LIB = "LIB";
    private static final String COL_MOUVEMENT_DATE = "DATE";
    private static final String COL_MOUVEMENT_QUANTITE = "QUANTITE";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_ECHANT + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_CODE + " TEXT NOT NULL, " +
            COL_LIB + " TEXT NOT NULL, " +
            COL_STOCK + " TEXT NOT NULL);";

    private static final String CREATE_MOUVEMENT = "CREATE TABLE " + TABLE_MOUVEMENT + " (" +
            COL_MOUVEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_MOUVEMENT_TYPE + " TEXT NOT NULL, " +
            COL_MOUVEMENT_CODE + " TEXT NOT NULL, " +
            COL_MOUVEMENT_LIB + " TEXT NOT NULL, " +
            COL_MOUVEMENT_DATE + " DATETIME NOT NULL, " +
            COL_MOUVEMENT_QUANTITE + " TEXT NOT NULL);";

    public CreateBdEchantillon(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
        db.execSQL(CREATE_MOUVEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ECHANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOUVEMENT);
        onCreate(db);
    }
}
