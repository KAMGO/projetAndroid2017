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

public class Tache02 extends AsyncTask<String, Void, Integer> {

    /*********************/
    /* VARIABLES LOCALES */
    /*********************/
    RechPrixActivity activity;
    ProgressDialog progress;
    private ArrayList<String> tabArticleId,  tabArticleNom;

    /****************/
    /* CONSTRUCTEUR */
    /****************/
    public Tache02(RechPrixActivity _activity){
        activity = _activity;
        progress = new ProgressDialog(_activity);
    }

    /******************/
    /* DOINBACKGROUND */
    /******************/
    protected Integer doInBackground(String...params){
        Integer res;
        tabArticleId = new ArrayList<String>();
        tabArticleNom = new ArrayList<String>();
        try {
            // Création de l'URL du serveur avec ses paramètres
            URL url = new URL("https://ktmjmartin.000webhostapp.com/rechercher_par_prix.php?mini=" + params[0] + "&maxi=" + params[1] + "&tri=" + params[2]);
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // Passage des paramètre avec la méthode "GET"
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Création du JsonReader
            InputStream input = urlConnection.getInputStream();
            InputStreamReader inputReader = new InputStreamReader(input);
            //json.Lenient(true);
            //JsonReader json = new JsonReader(inputReader);
            /*/json.setLenient(true);
            // Lecture du fichier Json
           // json.beginObject();
            //json.nextName();
           // res = json.nextInt();
            if (json.isLenient()){
                //json.nextName();
               /* json.beginArray();
                while (json.hasNext()){
                    json.beginObject();
                    json.nextName();
                    tabArticleId.add(json.nextString());
                    json.nextName();
                    tabArticleNom.add(json.nextString());
                    json.endObject();
                }
                json.endArray();
                json.endObject();
            }*/
            Scanner scanner = new Scanner(input);
            urlConnection.disconnect();
            scanner.next();
            res =Integer.parseInt(scanner.next().toString());
            int i=1;
            if(res==1) {
                while (scanner.hasNext()){
                    if(i%4==1)
                     scanner.next();
                    if(i%4==2)
                    tabArticleId.add(scanner.next().toString());
                    if(i%4==3)
                        scanner.next();
                    if(i%4==0)
                    tabArticleNom.add(scanner.next().toString());
                    i++;
               }
            }
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
        activity.populate(result, tabArticleId, tabArticleNom);
    }
}

