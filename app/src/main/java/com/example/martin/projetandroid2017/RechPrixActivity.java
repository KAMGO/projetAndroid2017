package com.example.martin.projetandroid2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Martin on 12-12-17.
 */

public class RechPrixActivity extends AppCompatActivity {
    /*********************/
    /* VARIABLES LOCALES */
    /*********************/
    final static int NUM_Activity = 2;
    String tri="ASC";
    // Variables des vues du xml
    EditText et_min, et_max;
    Button btn_rechercher, btn_retour;
    TextView tv_erreur;

    /*************/
    /* LISTENERS */
    /*************/
    View.OnClickListener listenerRechercher = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Récupération des valeurs des champs EditText
            String min, max;
            min = et_min.getText().toString();
            max = et_max.getText().toString();

            // Création de la tache asynchrone
            Tache02 tache = new Tache02(RechPrixActivity.this);
            // Exécution de la tache
            tache.execute(min, max,tri);
        }
    };
    View.OnClickListener listenerRetour = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Création de l'Intent
            Intent intent = new Intent(RechPrixActivity.this, MainActivity.class);
            // Appel de la nouvelle Activity
            startActivity(intent);
        }
    };
    public void onRadioBtnClickedTri(View view) {
        // recupere des info
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.triCroissant:
                if (checked)
                    tri="ASC";
                break;
            case R.id.triDecroissant:
                if (checked)
                    tri="DESC";
                break;
        }
    }
    /******************/
    /* INSTANCIATIONS */
    /******************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rech_prix);
        // Récupération des vues xml
        btn_rechercher = (Button)findViewById(R.id.rechPrixRech);
        btn_retour = (Button)findViewById(R.id.rechPrixRetour);
        tv_erreur = (TextView)findViewById(R.id.rechPrixErreur);
        et_min = (EditText)findViewById(R.id.rechPrixMin);
        et_max = (EditText)findViewById(R.id.rechPrixMax);
        // Application des listeners
        btn_rechercher.setOnClickListener(listenerRechercher);
        btn_retour.setOnClickListener(listenerRetour);
    }

    public void populate(Integer result, ArrayList<String> tabArticleId, ArrayList<String> tabArticleNom){
        // Si tout s'est bien déroulé
        if (result == 1){
            // Création de l'Intent
            Intent intent = new Intent(RechPrixActivity.this, ResRechActivity.class);
            String[] tmp1 = new String[tabArticleId.size()];
            tabArticleId.toArray(tmp1);
            String[] tmp2 = new String[tabArticleNom.size()];
            tabArticleNom.toArray(tmp2);
            // Instanciation des paramètres
            intent.putExtra("tabArticlesId", tmp1);
            intent.putExtra("tabArticlesNom", tmp2);
            // Appel de la nouvelle Activity
            startActivityForResult(intent, NUM_Activity);
            // Si il y a eu des erreurs
        } else if (result == 100){
            tv_erreur.setHint("Aucun prix entré");
        } else if (result == 110){
            tv_erreur.setHint("Aucun prix entré");
        } else if (result == 120){
            tv_erreur.setHint("min supérieur à max");
        } else if (result == 1000) {
            tv_erreur.setText("Erreur Connexion à la DB");
        } else if (result == 1100) {
            tv_erreur.setText("Aucun article trouvé");
        } else if (result == 2000){
            tv_erreur.setText("Un problème est survenu");
        } else  if (result == -1){
            tv_erreur.setText("MalformedURLException");
        } else  if (result == -2){
            tv_erreur.setText("IOException");
        }
    }
}
