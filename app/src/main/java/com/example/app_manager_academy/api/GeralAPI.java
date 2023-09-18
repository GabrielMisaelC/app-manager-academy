package com.example.app_manager_academy.api;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GeralAPI extends AsyncTask<String,Void,String> {

    private final String url;
    private final String metodo;

    public GeralAPI(String url, String metodo) {
        this.url = url;
        this.metodo = metodo;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(this.metodo);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
            connection.setRequestProperty("charset", "utf-8");
            String dados = strings[0];
            byte[] postData       = dados.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            connection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(10000);
            try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
                wr.write( postData );
            }
            connection.connect();
            int x =connection.getResponseCode();
            if(x==400){
                Scanner scannererro = new Scanner(connection.getErrorStream());
                while (scannererro.hasNextLine()) {
                    resposta.append(scannererro.nextLine());
                }
            }
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                resposta.append(scanner.nextLine());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String resposta) {
        if (resposta != null) {
            // Faça o tratamento da resposta aqui
            // Provavelmente, você deseja analisar o JSON de resposta neste ponto
            // Você pode usar bibliotecas como Gson para isso.
        } else {
            // Trate o caso de erro aqui
        }
    }
}