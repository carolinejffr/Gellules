package com.example.gellules.menus;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gellules.R;
import com.example.gellules.bdd.BdAdapter;
import com.example.gellules.bdd.BdAdapterMvt;
import com.example.gellules.modeles.Echantillon;
import com.example.gellules.modeles.Mouvement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AjoutEchantillon extends AppCompatActivity {
    private SQLiteDatabase database;

    // Interface
    private Button buttonAjouter;
    private Button buttonQuitter;
    private EditText editTextRef;
    private EditText editTextLibelle;
    private EditText editTextStock;


    // Création de la fenêtre
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_echantillon);

        // Récupération des éléments de l'interface
        buttonAjouter = findViewById(R.id.buttonAjouter);
        buttonQuitter = findViewById(R.id.buttonQuitterAjout);
        editTextRef = findViewById(R.id.editTextRef);
        editTextLibelle = findViewById(R.id.editTextLibelle);
        editTextStock = findViewById(R.id.editTextStock);

        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération des textes
                String leCode = editTextRef.getText().toString();
                String leLibelle = editTextLibelle.getText().toString();
                String leStock = editTextStock.getText().toString();


                Echantillon unEchant = new Echantillon(leCode, leLibelle, leStock);
                // Obtenir la date actuelle
                LocalDate date = LocalDate.now();

                // Définir le format de date souhaité
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                // Formatter la date selon le format spécifié
                String laDate = date.format(formatter);
                Mouvement unMouvement = new Mouvement(leCode, leLibelle, "ajout", laDate, leStock);
                BdAdapter bdd = new BdAdapter(getBaseContext());
                BdAdapterMvt bddMvt = new BdAdapterMvt(getBaseContext());
                bdd.open();
                bddMvt.open();
                try
                {
                    bdd.getEchantillonWithCode(leCode);
                    bdd.insererEchantillon(unEchant);
                    bddMvt.insererMvt(unMouvement);
                    Toast toast = Toast.makeText(getBaseContext(), "Ajouté", Toast.LENGTH_SHORT);
                    toast.show();
                }
                catch(Exception e)
                {
                    Toast toast = Toast.makeText(getBaseContext(), "La ligne spécifiée existe déjà.", Toast.LENGTH_SHORT);
                    toast.show();
                }

                bdd.close();
                bddMvt.close();

                buttonQuitter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // retour en arrière
                        finish();
                    }
                });
            }
        });
    }
}
