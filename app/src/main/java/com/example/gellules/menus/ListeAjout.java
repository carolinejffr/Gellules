package com.example.gellules.menus;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import com.example.gellules.R;
import com.example.gellules.bdd.BdAdapterMvt;

public class ListeAjout extends Activity
{
    // Classe des mouvements d'ajouts
    ListView listView;
    BdAdapterMvt mvtBdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_ajout);

        listView = (ListView) findViewById(R.id.listViewAjout);
        mvtBdd = new BdAdapterMvt(this);

        //On ouvre la base de données pour écrire dedans
        mvtBdd.open();
        Cursor leCurseur = mvtBdd.getDataTypeAjout();

        // colonnes à afficher
        String[] colNoms = new String[] {BdAdapterMvt.COL_MOUVEMENT_LIB, BdAdapterMvt.COL_MOUVEMENT_DATE, BdAdapterMvt.COL_MOUVEMENT_QUANTITE};

        // champs dans lesquelles afficher les colonnes
        int[] colNumeros = new int[] {R.id.textViewLibAjout, R.id.textViewDateAjout, R.id.textViewQteAjout};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.colonnes_liste_ajout, leCurseur,colNoms,colNumeros);

        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        mvtBdd.close();

        Button buttonQuitter = (Button)findViewById(R.id.buttonQuitterListeAjout);

        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish(); //fermeture de la fenêtre

            }

        });
    }
}
