package com.example.martin.projetandroid2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Martin on 12-12-17.
 */

public class ResRechActivity extends AppCompatActivity {
    /*********************/
    /* VARIABLES LOCALES */
    /*********************/
    final static int NUM_REQ = 1;
    int i;
    boolean suivant, precedent, init;
    String[] tabArticleId, tabArticleNom;
    // Variables des vues du xml
    TextView tv_art1, tv_art2, tv_art3, tv_art4, tv_art5, tv_erreur;
    Button btn_suivant, btn_precedent, btn_retour;

    /*************/
    /* LISTENERS */
    /*************/
    // Pour le bouton "Suivant"
    View.OnClickListener listenerSuivant = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (init) {
                init = false;
            }
            if (btn_precedent.getVisibility() == View.INVISIBLE) {
                btn_precedent.setVisibility(View.VISIBLE);
            }
            if (i < tabArticleNom.length) {
                if (!suivant) {
                    i += 5;
                    suivant = true;
                    precedent = false;
                }
                if (i < tabArticleNom.length) {
                    tv_art1.setText(tabArticleNom[i]);
                } else {
                    tv_art1.setText("");
                }
                i++;
                if (i < tabArticleNom.length) {
                    tv_art2.setText(tabArticleNom[i]);
                } else {
                    tv_art2.setText("");
                }
                i++;
                if (i < tabArticleNom.length) {
                    tv_art3.setText(tabArticleNom[i]);
                } else {
                    tv_art3.setText("");
                }
                i++;
                if (i < tabArticleNom.length) {
                    tv_art4.setText(tabArticleNom[i]);
                } else {
                    tv_art4.setText("");
                }
                i++;
                if (i < tabArticleNom.length) {
                    tv_art5.setText(tabArticleNom[i]);
                } else {
                    tv_art5.setText("");
                }
                i++;
                if (i >= tabArticleNom.length) {
                    btn_suivant.setVisibility(View.INVISIBLE);
                }
            }
        }
    };
    // Pour le bouton "Précédent"
    View.OnClickListener listenerPrecedent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!init) {
                if (i > 0) {
                    if (btn_suivant.getVisibility() == View.INVISIBLE) {
                        btn_suivant.setVisibility(View.VISIBLE);
                    }
                    if (!precedent) {
                        i -= 5;
                        precedent = true;
                        suivant = false;
                    }
                    i--;
                    tv_art5.setText(tabArticleNom[i]);
                    i--;
                    tv_art4.setText(tabArticleNom[i]);
                    i--;
                    tv_art3.setText(tabArticleNom[i]);
                    i--;
                    tv_art2.setText(tabArticleNom[i]);
                    i--;
                    tv_art1.setText(tabArticleNom[i]);
                }
                if (i == 0) {
                    btn_precedent.setVisibility(View.INVISIBLE);
                }
            }
        }
    };
    // Pour le bouton "Retour"
    View.OnClickListener listenerRetour = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Création de l'Intent pour le renvoi
            Intent intentRetour = new Intent();
            setResult(RESULT_OK, intentRetour);
            finish();
        }
    };
    // Pour l'article à la 1ere place dans la liste
    View.OnClickListener listenerArticle1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (tv_art1.getText().toString().compareTo("") != 0) {
                // Création de la tache asynchrone
                Tache04 tache = new Tache04(ResRechActivity.this);
                if (suivant)
                    i -= 5;
                // Exécution de la tache
                tache.execute(tabArticleId[i]);
            }
        }
    };
    // Pour l'article à la 2eme place dans la liste
    View.OnClickListener listenerArticle2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (tv_art2.getText().toString().compareTo("") != 0) {
                // Création de la tache asynchrone
                Tache04 tache = new Tache04(ResRechActivity.this);
                if (precedent)
                    i++;
                else if (suivant)
                    i -= 4;
                // Exécution de la tache
                tache.execute(tabArticleId[i]);
            }
        }
    };
    // Pour l'article à la 3eme place dans la liste
    View.OnClickListener listenerArticle3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (tv_art3.getText().toString().compareTo("") != 0) {
                // Création de la tache asynchrone
                Tache04 tache = new Tache04(ResRechActivity.this);
                if (precedent)
                    i += 2;
                else if (suivant)
                    i -= 3;
                // Exécution de la tache
                tache.execute(tabArticleId[i]);
            }
        }
    };
    // Pour l'article à la 4eme place dans la liste
    View.OnClickListener listenerArticle4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (tv_art4.getText().toString().compareTo("") != 0) {
                // Création de la tache asynchrone
                Tache04 tache = new Tache04(ResRechActivity.this);
                if (precedent)
                    i += 3;
                else if (suivant)
                    i -= 2;
                // Exécution de la tache
                tache.execute(tabArticleId[i]);
            }
        }
    };
    // Pour l'article à la 5eme place dans la liste
    View.OnClickListener listenerArticle5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (tv_art5.getText().toString().compareTo("") != 0) {
                // Création de la tache asynchrone
                Tache04 tache = new Tache04(ResRechActivity.this);
                if (precedent)
                    i += 4;
                else if (suivant)
                    i--;
                // Exécution de la tache
                tache.execute(tabArticleId[i]);
            }
        }
    };

    /******************/
    /* INSTANCIATIONS */

    /******************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_res_rech);
        i = 0;
        suivant = true;
        precedent = false;
        init = true;
        // Récupération de l'Intent
        Intent intent = getIntent();
        // Récupération des valeurs
        tabArticleId = new String[intent.getStringArrayExtra("tabArticlesId").length];
        tabArticleId = intent.getStringArrayExtra("tabArticlesId");
        tabArticleNom = new String[intent.getStringArrayExtra("tabArticlesNom").length];
        tabArticleNom = intent.getStringArrayExtra("tabArticlesNom");
        // Récupération des vues du xml
        btn_suivant = (Button) findViewById(R.id.resRechSuivant);
        btn_precedent = (Button) findViewById(R.id.resRechPrecedent);
        btn_retour = (Button) findViewById(R.id.resRechRetour);
        tv_erreur = (TextView) findViewById(R.id.resRechErreur);
        tv_art1 = (TextView) findViewById(R.id.resRechArticle1);
        tv_art2 = (TextView) findViewById(R.id.resRechArticle2);
        tv_art3 = (TextView) findViewById(R.id.resRechArticle3);
        tv_art4 = (TextView) findViewById(R.id.resRechArticle4);
        tv_art5 = (TextView) findViewById(R.id.resRechArticle5);
        // Application des listeners
        btn_suivant.setOnClickListener(listenerSuivant);
        btn_precedent.setOnClickListener(listenerPrecedent);
        btn_retour.setOnClickListener(listenerRetour);
        tv_art1.setOnClickListener(listenerArticle1);
        tv_art2.setOnClickListener(listenerArticle2);
        tv_art3.setOnClickListener(listenerArticle3);
        tv_art4.setOnClickListener(listenerArticle4);
        tv_art5.setOnClickListener(listenerArticle5);
        // Affichage des 5 premiers articles

        if (i < tabArticleNom.length) {
            tv_art1.setText(tabArticleNom[i]);
        } else {
            tv_art1.setText("");
        }
        i++;
        if (i < tabArticleNom.length) {
            tv_art2.setText(tabArticleNom[i]);
        } else {
            tv_art2.setText("");
        }
        i++;
        if (i < tabArticleNom.length) {
            tv_art3.setText(tabArticleNom[i]);
        } else {
            tv_art3.setText("");
        }
        i++;
        if (i < tabArticleNom.length) {
            tv_art4.setText(tabArticleNom[i]);
        } else {
            tv_art4.setText("");
        }
        i++;
        if (i < tabArticleNom.length) {
            tv_art5.setText(tabArticleNom[i]);
        } else {
            tv_art5.setText("");
        }
        i++;
        btn_precedent.setVisibility(View.INVISIBLE);
        if (5>= tabArticleNom.length)
            btn_suivant.setVisibility(View.INVISIBLE);
    }

    /************/
    /* POPULATE */

    /************/
    public void populate(Integer result, String[] descArticle) {
        // Si tout s'est bien déroulé
        if (result == 1) {
            // Création de l'Intent
            Intent intent = new Intent(ResRechActivity.this, DescriptifActivity.class);
            // Instanciation du paramètre
            intent.putExtra("article", descArticle);
            // Appel de la nouvelle Activity
            startActivityForResult(intent, NUM_REQ);
            // Si il y a eu des erreurs
        } else if (result == 100) {
            tv_erreur.setHint("Aucun id transmis");
        } else if (result == 101) {
            tv_erreur.setHint("id incorrect");
        } else if (result == 1000) {
            tv_erreur.setText("Erreur Connexion à la DB");
        } else if (result == 1100) {
            tv_erreur.setText("Aucun article trouvé");
        } else if (result == 2000) {
            tv_erreur.setText("Un problème est survenu");
        } else if (result == -1) {
            tv_erreur.setText("MalformedURLException");
        } else if (result == -2) {
            tv_erreur.setText("IOException");
        }
    }

    /********************/
    /* ONACTIVITYRESULT */

    /********************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Si réponse à la requete "NUM_REQ"
        if (requestCode == NUM_REQ) {
            // Si code résultat "OK"
            if (resultCode == RESULT_OK) {
                // Affichage des 5 premiers articles
               i = 0;
                precedent = false;
                if (i < tabArticleNom.length) {
                    tv_art1.setText(tabArticleNom[i]);
                } else {
                    tv_art1.setText("");
                }
                i++;
                if (i < tabArticleNom.length) {
                    tv_art2.setText(tabArticleNom[i]);
                } else {
                    tv_art2.setText("");
                }
                i++;
                if (i < tabArticleNom.length) {
                    tv_art3.setText(tabArticleNom[i]);
                } else {
                    tv_art3.setText("");
                }
                i++;
                if (i < tabArticleNom.length) {
                    tv_art4.setText(tabArticleNom[i]);
                } else {
                    tv_art4.setText("");
                }
                i++;
                if (i < tabArticleNom.length) {
                    tv_art5.setText(tabArticleNom[i]);
                } else {
                    tv_art5.setText("");
                }
                i++;
                suivant = true;
                init = true;
                btn_precedent.setVisibility(View.INVISIBLE);
                if (5< tabArticleNom.length)
                     btn_suivant.setVisibility(View.VISIBLE);
            }
        }
    }
}