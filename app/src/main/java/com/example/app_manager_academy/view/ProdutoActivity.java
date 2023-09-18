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
import com.example.app_manager_academy.model.Produto;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

public class ProdutoActivity extends AppCompatActivity {

    SwitchMaterial chkStatus;
    TextInputEditText edtDescricao, edtPrice, edtPorcentagem, edtObs;
    Button btnSalvar;
    ImageButton btnBack;
    Produto produto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        TextView header = findViewById(R.id.titleActivity);
        header.setText(R.string.produto);

        produto = new Produto();

        init();
    }

    public void init(){
        chkStatus = findViewById(R.id.chkProduto);
        edtDescricao = findViewById(R.id.edtDescricao);
        edtPrice = findViewById(R.id.edtPreco);
        edtPorcentagem = findViewById(R.id.edtPorcentagem);
        edtObs = findViewById(R.id.edtObs);
        btnBack = findViewById(R.id.btnBack);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salva(v);
            }
        });
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
        produto.setStatus(chkStatus.isChecked());
        produto.setPrice(Double.valueOf(edtPrice.getText().toString()));
        produto.setDescription(edtDescricao.getText().toString());
        produto.setPercentage(Integer.parseInt(edtPorcentagem.getText().toString()));
        produto.setObs(edtObs.getText().toString());


        Gson gson = new Gson();

        String json = gson.toJson(produto);

        Log.i("TAGTESTE", "Json => " + json);

        String apiUrl = "http://192.168.15.7:3000/product";

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
