package com.example.gellules.menus;

import android.os.Bundle;
import android.util.Log;
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

public class MajEchantillons extends AppCompatActivity {

    private EditText editTextCode;
    private EditText editTextQte;
    private Button buttonQuitterMaj;
    private Button buttonAjouterMaj;
    private Button buttonSupprimerMaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maj_echantillons);

        // Récupération des éléments de l'interface
        editTextCode = findViewById(R.id.editTextCode);
        editTextQte = findViewById(R.id.editTextQte);
        buttonQuitterMaj = findViewById(R.id.buttonQuitterMaj);
        buttonAjouterMaj = findViewById(R.id.buttonAjouterMaj);
        buttonSupprimerMaj = findViewById(R.id.buttonSupprimer);


        buttonAjouterMaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BdAdapter bdd = new BdAdapter(getBaseContext());
                bdd.open();
                BdAdapterMvt bddMvt = new BdAdapterMvt(getBaseContext());
                bddMvt.open();
                try
                {
                    String code = editTextCode.getText().toString();
                    String qte = editTextQte.getText().toString();
                    Echantillon unEchantillon = bdd.getEchantillonWithCode(code);
                    // Obtenir la date actuelle
                    LocalDate date = LocalDate.now();

                    // Définir le format de date souhaité
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                    // Formatter la date selon le format spécifié
                    String laDate = date.format(formatter);
                    if (Integer.parseInt(qte) >= 0)
                    {
                        int nouvelleQte = Integer.parseInt(qte) + Integer.parseInt(unEchantillon.getQuantiteStock());
                        unEchantillon.setQuantiteStock(String.valueOf(nouvelleQte));
                        bdd.updateEchantillon(code, unEchantillon);
                        Mouvement unMouvementAjout = new Mouvement(code, unEchantillon.getLibelle(), "ajout",laDate, qte);
                        bddMvt.insererMvt(unMouvementAjout);
                        Toast toast = Toast.makeText(getBaseContext(), "Modifié avec succès", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        // Si c'est - de 0
                        Toast toast = Toast.makeText(getBaseContext(), "Vous ne pouvez pas ajouter une valeur négative", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
                catch(Exception e)
                {
                    Toast toast = Toast.makeText(getBaseContext(), "échec" , Toast.LENGTH_SHORT);
                    toast.show();
                    Log.i("CA CRASH", "Échec : " + e.getClass() + " : " + e.getMessage());
                }

                bdd.close();
                bddMvt.close();
            }
        });

        buttonSupprimerMaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BdAdapter bdd = new BdAdapter(getBaseContext());
                bdd.open();
                BdAdapterMvt bddMvt = new BdAdapterMvt(getBaseContext());
                bddMvt.open();
                try
                {
                    String code = editTextCode.getText().toString();
                    String qte = editTextQte.getText().toString();
                    Echantillon unEchantillon = bdd.getEchantillonWithCode(code);
                    // Obtenir la date actuelle
                    LocalDate date = LocalDate.now();

                    // Définir le format de date souhaité
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                    // Formatter la date selon le format spécifié
                    String laDate = date.format(formatter);
                    if (Integer.parseInt(qte) > 0)
                    {
                        int nouvelleQte =  Integer.parseInt(unEchantillon.getQuantiteStock()) - Integer.parseInt(qte);
                        if (nouvelleQte <= 0)
                        {
                            Mouvement unMouvementSuppr = new Mouvement(code, bdd.getEchantillonWithCode(code).getLibelle(), "suppr",laDate, "-" +qte);
                            bddMvt.insererMvt(unMouvementSuppr);
                            bdd.removeEchantillonWithCode(code);
                            Toast toast = Toast.makeText(getBaseContext(), "Supprimé avec succès", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else
                        {
                            unEchantillon.setQuantiteStock(String.valueOf(nouvelleQte));
                            bdd.updateEchantillon(code, unEchantillon);
                            Mouvement unMouvementSuppr = new Mouvement(code, bdd.getEchantillonWithCode(code).getLibelle(), "suppr",laDate, "-" +qte);
                            bddMvt.insererMvt(unMouvementSuppr);
                            Toast toast = Toast.makeText(getBaseContext(), "Modifié avec succès", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                    else
                    {
                        // Si c'est - de 0
                        Toast toast = Toast.makeText(getBaseContext(), "Vous ne pouvez pas ajouter une valeur négative", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
                catch(Exception e)
                {
                    Toast toast = Toast.makeText(getBaseContext(), "échec" , Toast.LENGTH_SHORT);
                    toast.show();
                    Log.i("CRASH", "Échec : " + e.getClass() + " : " + e.getMessage());
                }

                bdd.close();
                bddMvt.close();
            }
        });

        buttonQuitterMaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // retour en arrière
                finish();
            }
        });

    }
}