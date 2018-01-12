package com.example.martin.projetandroid2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /*********************/
    /* VARIABLES LOCALES */
    /*********************/
    // Variables des vues du xml
    Button btn_ajout, btn_rechPrix, btn_rechVille;

    /*************/
    /* LISTENERS */
    /*************/
    View.OnClickListener listenerAjout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Création de l'Intent
            Intent intent = new Intent(MainActivity.this, newActivity.class);
            // Appel de la nouvelle Activity
            startActivity(intent);
        }
    };
    View.OnClickListener listenerRechPrix = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Création de l'Intent
            Intent intent = new Intent(MainActivity.this, RechPrixActivity.class);
            // Appel de la nouvelle Activity
            startActivity(intent);
        }
    };
    View.OnClickListener listenerRechVille = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Création de l'Intent
            Intent intent = new Intent(MainActivity.this, RechVilleActivity.class);
            // Appel de la nouvelle Activity
            startActivity(intent);
        }
    };

    /******************/
    /* INSTANCIATIONS */
    /******************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Récupération des vues xml
        btn_ajout = (Button)findViewById(R.id.newArt);
        btn_rechPrix = (Button)findViewById(R.id.rechPrix);
        btn_rechVille = (Button)findViewById(R.id.rechVille);
        // Application des listeners
        btn_ajout.setOnClickListener(listenerAjout);
        btn_rechPrix.setOnClickListener(listenerRechPrix);
        btn_rechVille.setOnClickListener(listenerRechVille);
    }
}
