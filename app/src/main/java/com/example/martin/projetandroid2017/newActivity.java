package com.example.martin.projetandroid2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Martin on 12-12-17.
 */

public class newActivity extends AppCompatActivity {
    /*********************/
    /* VARIABLES LOCALES */
    /*********************/
    final static int NUM_REQ = 1;
     String etat="utilise";
    String info ="Acherche";
    // Variables des vues du xml
    EditText et_nom, et_prix, et_ville, et_desc;
    Button btn_wizard, btn_accepter, btn_retour;
    TextView tv_erreur;
    RadioGroup rg_etat,rg_info;

    /*************/
    /* LISTENERS */
    /*************/
    View.OnClickListener listenerAccepter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Récupération des valeurs des champs EditText
            String nom, prix, ville, desc;
            nom = et_nom.getText().toString();
            prix = et_prix.getText().toString();
            ville = et_ville.getText().toString();
            desc = et_desc.getText().toString();
            nom=nom.trim();
            desc=desc.trim();
            ville=ville.trim();
            ville=ville.replace(' ','_');
            nom=nom.replace(' ','_');
            desc=desc.replace(' ','_');
            // Création de la tache asynchrone
            Tache01 tache = new Tache01(newActivity.this);
            // Exécution de la tache
            tache.execute(nom, prix, desc, ville, etat.toString(), info.toString());
        }
    };
    View.OnClickListener listenerRetour = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Création de l'Intent
            Intent intent = new Intent(newActivity.this, MainActivity.class);
            // Appel de la nouvelle Activity
            startActivity(intent);
        }
    };
    View.OnClickListener listenerWizard = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Création de l'Intent
            Intent intent = new Intent(newActivity.this, VilleActivity.class);
            // Appel de la nouvelle Activity
            startActivityForResult(intent, NUM_REQ);
        }
    };

    /******************/
    /* INSTANCIATIONS */
    /******************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new);
        // Récupération des vues xml
        btn_wizard = (Button)findViewById(R.id.ajoutWizard);
        btn_accepter = (Button)findViewById(R.id.ajoutAccepter);
        btn_retour = (Button)findViewById(R.id.ajoutRetour);
        tv_erreur = (TextView)findViewById(R.id.ajoutErreur);
        et_nom = (EditText)findViewById(R.id.ajoutNom);
        et_prix = (EditText)findViewById(R.id.ajoutPrix);
        et_ville = (EditText)findViewById(R.id.ajoutVille);
        et_desc = (EditText)findViewById(R.id.ajoutDesc);

        // Application des listeners
        btn_wizard.setOnClickListener(listenerWizard);
        btn_accepter.setOnClickListener(listenerAccepter);
        btn_retour.setOnClickListener(listenerRetour);
    }
    public void onRadioBtnClickedInfo(View view) {
        // recupere des infos
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.Aretire:
                if (checked)
                    info="Aretire";
                    break;
            case R.id.Aenvoyer:
                if (checked)
                   info="Aenvoye";
                    break;
        }
    }
    public void onRadioBtnClickedEtat(View view) {
        // recuperation de l etat
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.neuf:
                if (checked)
                    etat="neuf";
                    break;
            case R.id.utilise:
                if (checked)
                   etat="utilise";
                    break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Si réponse à la requete "NUM_REQ"
        if (requestCode == NUM_REQ){
            // Si code résultat "OK"
            if (resultCode == RESULT_OK){
                // Récupération de la valeur de la ville
                String ville = data.getStringExtra("ville");
                // Ecriture dans le champ ville
                et_ville.setText(ville);
            }
        }
    }

    public void populate(Integer result){
        // Si tou s'est bien déroulé
        if (result == 1){
            // Création de l'Intent
            Intent intent = new Intent(newActivity.this, MainActivity.class);
            // Appel de la nouvelle Activity
            startActivity(intent);
            // Si il y a eu des erreurs
        } else if (result == 100){
            tv_erreur.setText("Aucun nom entré");
        } else if (result == 110){
            tv_erreur.setText("Aucun prix entré");
        } else if (result == 111){
            tv_erreur.setText("Prix entré négatif");
        } else if (result == 120){
            tv_erreur.setText("Aucune description entrée");
        } else if (result == 130){
            tv_erreur.setText("Aucune ville entrée");
        } else if (result == 1000) {
            tv_erreur.setText("Erreur Connexion à la DB");
        } else if (result == 2000){
            tv_erreur.setText("Un problème est survenu");
        } else  if (result == -1){
            tv_erreur.setText("MalformedURLException");
        } else  if (result == -2){
            tv_erreur.setText("IOException");
        }
    }
}
