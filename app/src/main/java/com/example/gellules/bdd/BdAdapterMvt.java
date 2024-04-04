package com.example.gellules.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gellules.modeles.Mouvement;

public class BdAdapterMvt
{
    static final int VERSION_BDD = 10   ;
    private static final String NOM_BDD = "gsb.db";
    private static final String TABLE_MVT = "mouvement";
    // Colonnes de la table mouvement
    public static final String COL_MOUVEMENT_ID = "_id";
    final int NUM_COL_MOUVEMENT_ID = 0;
    public static final String COL_MOUVEMENT_TYPE = "TYPE";
    final int NUM_COL_MOUVEMENT_TYPE = 1;
    public static final String COL_MOUVEMENT_CODE = "CODE";
    final int NUM_COL_MOUVEMENT_CODE = 2;
    public static final String COL_MOUVEMENT_LIB = "LIB";
    final int NUM_COL_MOUVEMENT_LIB = 3;
    public static final String COL_MOUVEMENT_DATE = "DATE";
    final int NUM_COL_MOUVEMENT_DATE = 4;
    public static final String COL_MOUVEMENT_QUANTITE = "QUANTITE";
    final int NUM_COL_MOUVEMENT_QUANTITE = 5;
    private final CreateBdEchantillon bdArticles;
    private final Context context;
    private SQLiteDatabase db;
    public BdAdapterMvt(Context context){
        this.context = context;
        bdArticles = new CreateBdEchantillon(context, NOM_BDD, null, VERSION_BDD);
    }
    public BdAdapterMvt open(){
        db = bdArticles.getWritableDatabase();
        return this;
    }
    public BdAdapterMvt close (){
        db.close(); return null;
    }

    public long insererMvt(Mouvement unMouvement){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_MOUVEMENT_TYPE, unMouvement.getType());
        values.put(COL_MOUVEMENT_CODE, unMouvement.getCode());
        values.put(COL_MOUVEMENT_LIB, unMouvement.getLibelle());
        values.put(COL_MOUVEMENT_DATE, unMouvement.getDate());
        values.put(COL_MOUVEMENT_QUANTITE, unMouvement.getQuantite());
        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_MVT, null, values);
    }
    public Cursor getDataTypeAjout()
    {
        return db.rawQuery("SELECT * FROM mouvement WHERE TYPE = 'ajout'", null);
    }
    public Cursor getDataTypeSuppr()
    {
        return db.rawQuery("SELECT * FROM mouvement WHERE TYPE = 'suppr'", null);
    }
}
