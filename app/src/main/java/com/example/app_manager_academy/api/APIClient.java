package com.example.app_manager_academy.api;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APIClient {

    private static final String TAG = "TAGTESTE";
    private static final int TIMEOUT_MS = 30000; // Tempo limite da conexão (10 segundos)

    public interface OnApiResponseListener {
        void onSuccess(String response);

        void onError(String errorMessage);
    }

    public static void sendJsonToAPI(String apiUrl, String json, OnApiResponseListener listener) {
        new SendJsonTask(apiUrl, json, listener).execute();
    }

    private static class SendJsonTask extends AsyncTask<Void, Void, String> {
        private final String apiUrl;
        private final String json;
        private final OnApiResponseListener listener;

        public SendJsonTask(String apiUrl, String json, OnApiResponseListener listener) {
            this.apiUrl = apiUrl;
            this.json = json;
            this.listener = listener;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                connection.setConnectTimeout(TIMEOUT_MS);
                connection.setDoOutput(true);

                // Escreva o JSON no corpo da requisição
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = json.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    String response = convertStreamToString(inputStream);
                    inputStream.close();
                    return response;
                } else {
                    return "Erro de servidor: " + responseCode;
                }
            } catch (IOException e) {
                Log.e(TAG, "Erro ao enviar JSON para API", e);
                return "Erro de E/S: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.startsWith("Erro")) {
                listener.onError(result);
            } else {
                listener.onSuccess(result);
            }
        }
    }

    private static String convertStreamToString(InputStream is) {
        java.util.Scanner scanner = new java.util.Scanner(is).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
