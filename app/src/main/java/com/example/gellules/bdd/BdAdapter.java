package com.example.gellules.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gellules.modeles.Echantillon;

public class BdAdapter {
    static final int VERSION_BDD = 10   ;
    private static final String NOM_BDD = "gsb.db";
    private static final String TABLE_ECHANT = "echantillons";

    public static final String COL_ID = "_id";
    static final int NUM_COL_ID = 0;
    public static final String COL_CODE = "CODE";
    static final int NUM_COL_CODE = 1;
    public static final String COL_LIB = "LIB";
    static final int NUM_COL_LIB = 2;
    public static final String COL_STOCK = "STOCK";
    static final int NUM_COL_STOCK = 3;
    private final CreateBdEchantillon bdArticles;
    private final Context context;
    private SQLiteDatabase db;
    public BdAdapter(Context context){
        this.context = context;
            bdArticles = new CreateBdEchantillon(context, NOM_BDD, null, VERSION_BDD);
    }

    //si la base n’existe pas, l’objet SQLiteOpenHelper exécute la méthode onCreate
    // si la version de la base a changé, la méthode onUpgrade sera lancée, dans les 2 cas l’appel
    // à getWritableDatabase permet d’ouvrir une connexion à la base en écriture
    public BdAdapter open(){
        db = bdArticles.getWritableDatabase();
        return this;
    }
    public BdAdapter close (){
        db.close(); return null;
    }
    public long insererEchantillon(Echantillon unEchant){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_CODE, unEchant.getCode());
        values.put(COL_LIB, unEchant.getLibelle());
        values.put(COL_STOCK, unEchant.getQuantiteStock());
        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_ECHANT, null, values);
    }
    private Echantillon cursorToEchant(Cursor c) {
        Echantillon unEchant = null;
        if (c != null) {
            Log.i("DEBUG", "Cursor count: " + c.getCount()); // Vérifier le nombre de lignes dans le curseur
            if (c.moveToFirst()) {
                unEchant = new Echantillon(null, null, null);
                unEchant.setCode(c.getString(c.getColumnIndex(COL_CODE)));
                unEchant.setLibelle(c.getString(c.getColumnIndex(COL_LIB)));
                unEchant.setQuantiteStock(c.getString(c.getColumnIndex(COL_STOCK)));
            }
        }
        // Ne fermez le curseur que si c'est nécessaire, c'est-à-dire s'il est différent de null
        if (c != null) {
            c.close();
        }
        return unEchant;
    }


    public Echantillon getEchantillonWithCode(String unCode){ //Récupère dans un Cursor les valeurs correspondant à un echantillon grâce à sa designation)
        Cursor c = db.query(TABLE_ECHANT, new String[] {COL_CODE, COL_LIB, COL_STOCK}, COL_CODE + " LIKE \"" + unCode +"\"", null, null, null, null);
        return cursorToEchant(c); }

    public int updateEchantillon(String unCode, Echantillon unEchant){ //La mise à jour d'un echantillon dans la BDD fonctionne plus ou moins comme une insertion,

        // il faut simple préciser quel echantillon on doit mettre à jour grâce à son code.
        ContentValues values = new ContentValues();
        values.put(COL_CODE, unEchant.getCode());
        values.put(COL_LIB, unEchant.getLibelle());
        values.put(COL_STOCK, unEchant.getQuantiteStock());
        return db.update(TABLE_ECHANT, values, COL_CODE + " = \"" +unCode+"\"", null);
    }
    public int removeEchantillonWithCode(String unCode) {
        //Suppression d'un echantillon de la BDD grâce à son code
        return db.delete(TABLE_ECHANT, COL_CODE + " = \"" +unCode+"\"", null);
    }
    public Cursor getData(){
        return db.rawQuery("SELECT * FROM echantillons", null);
    }
}

