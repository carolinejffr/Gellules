package com.example.gellules.menus;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import androidx.cursoradapter.widget.SimpleCursorAdapter;

import com.example.gellules.R;
import com.example.gellules.bdd.BdAdapter;


public class ListeEchantillons extends Activity {

    private ListView listView;

    private BdAdapter echantBdd;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_echantillons);
        listView = (ListView) findViewById(R.id.ListViewEchant);
        echantBdd = new BdAdapter(this);

        //On ouvre la base de données pour écrire dedans
        echantBdd.open();
        Cursor leCurseur = echantBdd.getData();

        // colonnes à afficher
        String[] colNoms = new String[] {BdAdapter.COL_CODE, BdAdapter.COL_LIB,BdAdapter.COL_STOCK};

        // champs dans lesquelles afficher les colonnes
        int[] colNumeros = new int[] {R.id.textViewCode, R.id.textViewLib, R.id.textViewQte};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.colonnes_liste, leCurseur,colNoms,colNumeros);

        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        echantBdd.close();

        Button buttonQuitter = (Button)findViewById(R.id.buttonQuitterListe);


        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish(); //fermeture de la fenêtre

            }

        });

    }

}