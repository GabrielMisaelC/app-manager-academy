package com.example.app_manager_academy.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_manager_academy.R;
import com.example.app_manager_academy.api.APIClient;
import com.example.app_manager_academy.api.GeralAPI;
import com.example.app_manager_academy.model.Cliente;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

public class ClienteActivity extends AppCompatActivity {

    TextInputLayout   lNome, lEmail, lCpf, lCelular, lLugar, lObs;
    TextInputEditText edtNome, edtEmail, edtCpf, edtCelular, edtLugar, edtObs, edtNascimento;
    Button btnSalvar;
    Cliente cliente;
    ImageButton btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        init();
        layout();

        cliente = new Cliente();

    }

    public void layout(){
        lNome = findViewById(R.id.txtNome);
        lEmail = findViewById(R.id.txtEmail);
        lCpf = findViewById(R.id.txtCPF);
        lCelular = findViewById(R.id.txtCelular);
        lLugar = findViewById(R.id.txtLugar);
        lObs = findViewById(R.id.txtObs);
        edtNascimento = findViewById(R.id.edtNascimento);
    }
    public void init(){
        TextView header = findViewById(R.id.titleActivity);
        header.setText(R.string.cliente);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtCpf = findViewById(R.id.edtCPF);
        edtCelular = findViewById(R.id.edtCelular);
        edtLugar = findViewById(R.id.edtLugar);
        edtObs = findViewById(R.id.edtObs);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salva(v);
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void showSnackPositive(View view){
        final Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);

        View customSnackView = getLayoutInflater().inflate(R.layout.snackbar_sucess, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

        snackbarLayout.setPadding(0, 0, 0, 0);

        snackbarLayout.addView(customSnackView, 0);

        snackbar.show();
    }
    public void showSnackNegative(View view){
        final Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);

        View customSnackView = getLayoutInflater().inflate(R.layout.snackbar_failed, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

        snackbarLayout.setPadding(0, 0, 0, 0);

        snackbarLayout.addView(customSnackView, 0);

        snackbar.show();
    }
    public Boolean checkCampos(){
        Boolean bool = true;

        if (edtNome.getText().toString().equals("")){
            lNome.setErrorEnabled(true);
            bool = false;
        }else{
            lNome.setErrorEnabled(false);
        }
        if (edtEmail.getText().toString().equals("")){
            lEmail.setErrorEnabled(true);
            bool = false;
        }else{
            lEmail.setErrorEnabled(false);
        }
        if (edtCpf.getText().toString().equals("")){
            lCpf.setErrorEnabled(true);
            bool = false;
        }else{
            lCpf.setErrorEnabled(false);
        }
        if (edtCelular.getText().toString().equals("")){
            lCelular.setErrorEnabled(true);
            bool = false;
        }else{
            lCelular.setErrorEnabled(false);
        }
        if (edtLugar.getText().toString().equals("")){
            lLugar.setErrorEnabled(true);
            bool = false;
        }else{
            lLugar.setErrorEnabled(false);
        }

        return bool;
    }
    public void salva(View v){
        Boolean bool;

        bool = checkCampos();

        if (bool){
            showSnackPositive(v);
            cliente.setName(edtNome.getText().toString());
            cliente.setEmail(edtEmail.getText().toString());
            cliente.setCpf(edtCpf.getText().toString());
            cliente.setCellphone(edtCelular.getText().toString());
            cliente.setNascimento(edtNascimento.getText().toString());
            cliente.setObs(edtObs.getText().toString());
            cliente.setPlace(edtLugar.getText().toString());

            Gson gson = new Gson();

            String json = gson.toJson(cliente);

            Log.i("TAGTESTE", "Json => " + json);

            String apiUrl = "http://192.168.15.7:3000/client";

            APIClient.sendJsonToAPI(apiUrl, json, new APIClient.OnApiResponseListener() {
                @Override
                public void onSuccess(String response) {
                    // Sucesso: processar a resposta da API
                    Log.d("TAGTESTE", "Resposta da API: " + response);
                }

                @Override
                public void onError(String errorMessage) {
                    // Erro: lidar com o erro da API
                    Log.e("TAGTESTE", "Erro na API: " + errorMessage);
                }
            });

            try {
                Thread.sleep(2500);
                finish();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else{
            showSnackNegative(v);
        }
    }

}
