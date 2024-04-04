package com.example.gellules.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gellules.R;
import com.example.gellules.bdd.BdAdapter;
import com.example.gellules.bdd.BdAdapterMvt;
import com.example.gellules.modeles.Echantillon;
import com.example.gellules.modeles.Mouvement;

public class MainActivity extends AppCompatActivity {
    // OnCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des boutons
        Button buttonAjoutEchantillon = (Button)findViewById(R.id.buttonAjoutEchantillon);
        Button buttonListeEchantillons = (Button)findViewById(R.id.buttonListeEchantillons);
        Button buttonMajEchantillons = (Button)findViewById(R.id.buttonMajEchantillons);
        Button buttonListeAjout = (Button)findViewById(R.id.buttonListeAjout);
        Button buttonListeSuppr = (Button)findViewById(R.id.buttonListeSuppr);

        // Ouverture vue ajout échantillon
        buttonAjoutEchantillon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AjoutEchantillon.class));
            }
        });

        // vue liste
        buttonListeEchantillons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListeEchantillons.class));
            }
        });

        // vue mise à jour
        buttonMajEchantillons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MajEchantillons.class));
            }
        });

        // vue liste ajout
        buttonListeAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListeAjout.class));
            }
        });

        // vue liste suppr
        buttonListeSuppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListeSuppr.class));
            }
        });


        // jeuEssaiBd();
    }

    public void jeuEssaiBd(){
        //Création d'une instance de la classe echantBDD
        BdAdapter echantBdd = new BdAdapter(this);
        //On ouvre la base de données pour écrire dedans
        echantBdd.open();
        //On insère DES ECHANTILLONS DANS LA BD

        echantBdd.insererEchantillon(new Echantillon("code1", "lib1", "3"));

        echantBdd.insererEchantillon(new Echantillon("code2", "lib2", "5"));

        echantBdd.insererEchantillon(new Echantillon("code3", "lib3", "7"));

        echantBdd.insererEchantillon(new Echantillon("code4", "lib4", "6"));

        Cursor unCurseur = echantBdd.getData();

        System.out.println("il y a "+String.valueOf(unCurseur.getCount())+" echantillons dans la BD");

        echantBdd.close();

        // test mouvements
        BdAdapterMvt mvtBdd = new BdAdapterMvt(this);
        mvtBdd.open();
        Mouvement unMvtAjout = new Mouvement("1", "test ajout", "ajout", "04-04-2024", "1");
        Mouvement unMvtSuppr = new Mouvement("2", "test suppr", "suppr", "04-04-2024", "1");
        mvtBdd.insererMvt(unMvtAjout);
        mvtBdd.insererMvt(unMvtSuppr);
        mvtBdd.close();

    }
}