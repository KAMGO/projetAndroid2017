package com.example.martin.projetandroid2017;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Martin on 06-01-18.
 */

public class Tache04 extends AsyncTask<String, Void, Integer> {

    /*********************/
    /* VARIABLES LOCALES */
    /*********************/
    ResRechActivity activity;
    ProgressDialog progress;
    String[] descArticle;

    /****************/
    /* CONSTRUCTEUR */
    /****************/
    public Tache04(ResRechActivity _activity){
        activity = _activity;
        progress = new ProgressDialog(_activity);
    }

    /******************/
    /* DOINBACKGROUND */
    /******************/
    protected Integer doInBackground(String...params){
        descArticle = new String[6];//new String[6];
        Integer res;
        try {
            // Création de l'URL du serveur avec ses paramètres
            URL url = new URL("https://ktmjmartin.000webhostapp.com/obtenir_descriptif_article.php?id_article=" + params[0]);
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // Passage des paramètre avec la méthode "GET"
            urlConnection.setRequestMethod("GET");

            // Création du JsonReader
            InputStream input = urlConnection.getInputStream();
            InputStreamReader inputReader = new InputStreamReader(input);
            /*/JsonReader json = new JsonReader(inputReader);
            // Lecture du fichier Json
            json.beginObject();
            json.nextName();
            res = json.nextInt();
            if (res == 1){
                json.nextName();
                json.beginArray();
                json.beginObject();
                json.nextName();
                for (int i = 0; i < 4; i++) {
                    descArticle[i] = json.nextString();
                    if (i != 3)
                        json.nextName();
                }
                json.endObject();
                json.endArray();
                json.endObject();
            }*/
            Scanner scanner = new Scanner(input);
            urlConnection.disconnect();
            scanner.next();
            res =Integer.parseInt(scanner.next().toString());
            int i=1,j=0;
            if(res==1) {
                while(scanner.hasNext()){
                    if(i%2==1)
                        scanner.next();
                    if(i%2==0){
                        if(j<6) {
                            descArticle[j] = scanner.next().toString();
                            j++;
                        }
                    }
                    i++;
                }
                return res;
            }
            else
                return res;
        } catch (MalformedURLException e){
            return -1;
        } catch (IOException e){
            return -2;
        }
    }

    /****************/
    /* ONPREEXECUTE */
    /****************/
    // Avant l'exécution du "doInBackground"
    protected void onPreExecute()
    {
        // Ouverture fernêtre de chargement
        progress.setTitle("Veuillez patienter");
        progress.setMessage("Récupération des données en cours...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
    }

    /*****************/
    /* ONPOSTEXECUTE */
    /*****************/
    protected void onPostExecute(Integer result){
        // Fermeture fernêtre de chargement
        if(progress.isShowing())
        {
            progress.dismiss();
        }
        // Appel de la fonction "populate" de l'Activity
        activity.populate(result, descArticle);
    }
}

