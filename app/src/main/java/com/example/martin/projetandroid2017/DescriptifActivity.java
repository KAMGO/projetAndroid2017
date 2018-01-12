package com.example.martin.projetandroid2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Martin on 12-12-17.
 */

public class DescriptifActivity  extends AppCompatActivity {

    /*********************/
    /* VARIABLES LOCALES */
    /*********************/
    // Variables des vues du xml
    TextView tv_nom, tv_prix, tv_ville, tv_desc ,tv_etat ,tv_info;
    Button btn_retour;

    /************/
    /* LISTENER */
    /************/
    View.OnClickListener listenerRetour = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Création de l'Intent pour le renvoi
            Intent intentRetour = new Intent();
            // Retour vers l'ancienne Activity
            setResult(RESULT_OK, intentRetour);
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
        setContentView(R.layout.layout_discriptif);
        // Récupération de l'Intent
        Intent intent = getIntent();
        // Récupération des valeurs de l'article
        String[] article = intent.getStringArrayExtra("article");
        // Récupération des vues xml
        btn_retour = (Button)findViewById(R.id.descRetour);
        tv_nom = (TextView)findViewById(R.id.descNom);
        tv_prix = (TextView)findViewById(R.id.descPrix);
        tv_ville = (TextView)findViewById(R.id.descVille);
        tv_desc = (TextView)findViewById(R.id.descDesc);
        tv_etat = (TextView)findViewById(R.id.descEtat);
        tv_info = (TextView)findViewById(R.id.descInfo);
        // Application des listeners
        btn_retour.setOnClickListener(listenerRetour);
        String nom=article[0], ville=article[3], desc=article[2];
        // Affichage de l'article
        nom=nom.trim();
        desc=desc.trim();
        ville=ville.trim();
        ville=ville.replace('_',' ');
        nom=nom.replace('_',' ');
        desc=desc.replace('_',' ');
        tv_nom.setText(nom);
        tv_prix.setText(article[1]);
        tv_ville.setText(ville);
        tv_desc.setText(desc);
        tv_etat.setText(article[4]);
        tv_info.setText(article[5]);
    }
}
