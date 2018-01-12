package com.example.martin.projetandroid2017;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Martin on 06-01-18.
 */

public class Tache01 extends AsyncTask<String, Void, Integer> {

    /*********************/
    /* VARIABLES LOCALES */
    /*********************/
    newActivity activity;
    ProgressDialog progress;

    /****************/
    /* CONSTRUCTEUR */
    /****************/
    public Tache01(newActivity _activity){
        activity = _activity;
        progress = new ProgressDialog(_activity);
    }

    /******************/
    /* DOINBACKGROUND */
    /******************/
    // Fait en arrière plan
    protected Integer doInBackground(String...params){
        Integer res;
        try {
            // Création de l'URL du serveur
            URL url = new URL("https://ktmjmartin.000webhostapp.com/ajouter_article.php");

            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // Passage des paramètre avec la méthode "POST"
            urlConnection.setRequestMethod("POST");

            // Envoi des paramètres suivant la méthode "POST"
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String parametres_post = "nom=" + params[0] + "&prix=" + params[1] + "&descriptif=" + params[2] + "&ville=" + params[3]+ "&etat=" + params[4]+ "&info=" + params[5];
            writer.write(parametres_post);
            writer.flush();
            writer.close();
            os.close();

            // Création du JsonReader
            InputStream input = urlConnection.getInputStream();
            InputStreamReader inputReader = new InputStreamReader(input);
           /*/ JsonReader json = new JsonReader(inputReader);
            // Lecture du fichier Json
            json.beginObject();
            json.nextName();
            res = json.nextInt();
            json.endObject();
            urlConnection.disconnect();
            return res;*/
            Scanner scanner = new Scanner(input);
            urlConnection.disconnect();
            scanner.next();
            res =Integer.parseInt(scanner.next().toString());
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
        progress.setMessage("Ecriture des données...");
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
        activity.populate(result);
    }
}

