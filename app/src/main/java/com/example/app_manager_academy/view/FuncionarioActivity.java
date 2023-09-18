package com.example.app_manager_academy.view;

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
import com.example.app_manager_academy.model.Funcionario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

public class FuncionarioActivity extends AppCompatActivity {

    SwitchMaterial chkAdm;
    TextInputEditText edtNome, edtEmail, edtCnpj, edtCpf, edtCelular, edtNascimento;
    Button btnSalvar;
    Funcionario funcionario;
    ImageButton btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        TextView header = findViewById(R.id.titleActivity);
        header.setText(R.string.funcionario);

        funcionario = new Funcionario();

        init();
    }

    public void init(){

        chkAdm = findViewById(R.id.chkAdm);
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtCnpj = findViewById(R.id.edtCnpj);
        edtCpf = findViewById(R.id.edtCPF);
        edtNascimento = findViewById(R.id.edtNascimento);
        edtCelular = findViewById(R.id.edtCelular);

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

    public void salva(View v){
        funcionario.setName(edtNome.getText().toString());
        funcionario.setEmail(edtEmail.getText().toString());
        funcionario.setCnpj(edtCnpj.getText().toString());
        funcionario.setCpf(edtCpf.getText().toString());
        funcionario.setCellphone(edtCelular.getText().toString());
        funcionario.setNascimento(edtNascimento.getText().toString());
        funcionario.setAdm(chkAdm.isChecked());

        Gson gson = new Gson();

        String json = gson.toJson(funcionario);

        Log.i("TAGTESTE", "Json => " + json);

        String apiUrl = "http://192.168.15.7:3000/employee";

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
            showSnackPositive(v);
            Thread.sleep(2500);
            finish();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
