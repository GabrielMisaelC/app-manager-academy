package com.example.app_manager_academy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_manager_academy.view.ClienteActivity;
import com.example.app_manager_academy.view.FuncionarioActivity;
import com.example.app_manager_academy.view.LoginActivity;
import com.example.app_manager_academy.view.ProdutoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button btnCliente, btnFuncionario, btnProduto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        init();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an instance of the snackbar
                final Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);

                // inflate the custom_snackbar_view created previously
                View customSnackView = getLayoutInflater().inflate(R.layout.snackbar_sucess, null);

                // set the background of the default snackbar as transparent
                snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

                // now change the layout of the snackbar
                Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

                // set padding of the all corners as 0
                snackbarLayout.setPadding(0, 0, 0, 0);

                // add the custom snack bar layout to snackbar layout
                snackbarLayout.addView(customSnackView, 0);

                snackbar.show();
            }
        });
    }

    public void init(){
        btnCliente = findViewById(R.id.btnCliente);
        btnFuncionario = findViewById(R.id.btnFuncionario);
        btnProduto = findViewById(R.id.btnProduto);

        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
                startActivity(intent);
            }
        });
        btnFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FuncionarioActivity.class);
                startActivity(intent);
            }
        });
        btnProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProdutoActivity.class);
                startActivity(intent);
            }
        });
    }
}