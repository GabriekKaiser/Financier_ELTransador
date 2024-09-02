package com.example.financierael_transador;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TransferenciaActivity extends AppCompatActivity {
    Spinner spinnerBancos;
    ImageView backHome;
    TextView saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transferencia_activity);

        spinnerBancos = findViewById(R.id.spinnerBancos);
        backHome = findViewById(R.id.back_home);
        saldo = findViewById(R.id.saldo);


        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para volver a la pantalla de inicio
                Intent intent = new Intent(TransferenciaActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Configurar el Spinner
        String[] bancos = {"Banco Nacional de Panama", "Banco General", "Banistmo", "Caja de Ahorros", "Bac Credomatic"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bancos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBancos.setAdapter(adapter);
    }
}
