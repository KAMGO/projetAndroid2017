package com.example.martin.projetandroid2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Martin on 12-12-17.
 */

public class VilleActivity extends AppCompatActivity {
    /*********************/
    /* VARIABLES LOCALES */
    /*********************/
    // Variables des vues du xml
    Button btn_accepter, btn_retour;
    RadioGroup rg_ville;
    RadioButton rb_ville;

    /*************/
    /* LISTENERS */
    /*************/
    // Pour le bouton "Accepter"
    View.OnClickListener listenerAccepter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Récupération de la valeur du RadioBouton sélectionné
            rb_ville = (RadioButton)findViewById(rg_ville.getCheckedRadioButtonId());
            String ville = rb_ville.getText().toString();

            // Création de l'Intent pour le renvoi
            Intent intentRetour = new Intent();
            // Initialisation de la valeur "ville"
            intentRetour.putExtra("ville", ville);
            // Retour vers l'ancienne Activity
            setResult(RESULT_OK, intentRetour);
            // Fermeture de l'Activity
            finish();
        }
    };
    // Pour le bouton "Retour"
    View.OnClickListener listenerRetour = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Création de l'Intent pour le retour
            Intent intentRetour = new Intent();
            // Retour vers l'ancienne Activity
            setResult(RESULT_CANCELED, intentRetour);
            // Fermeture de l'Activity
            finish();
        }
    };

    /******************/
    /* INSTANCIATIONS */
    /******************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ville);
        // Récupération des vues du xml
        btn_accepter = (Button)findViewById(R.id.villeAccepter);
        btn_retour = (Button)findViewById(R.id.villeRetour);
        rg_ville = (RadioGroup)findViewById(R.id.groupe);
        // Application des listeners
        btn_accepter.setOnClickListener(listenerAccepter);
        btn_retour.setOnClickListener(listenerRetour);
    }
}
