package com.example.financierael_transador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;

public class TransferenciaActivity extends AppCompatActivity {
    Spinner spinnerBancos;
    ImageView backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transferencia_activity);
        spinnerBancos = findViewById(R.id.spinnerBancos);
        backHome = findViewById(R.id.back_home);

        String[] bancos = {"Banco Nacional de Panama", "Banco General", "Banistmo", "Caja de Ahorros", "Bac Credomatic"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bancos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBancos.setAdapter(adapter);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para volver a la pantalla de inicio
                Intent intent = new Intent(TransferenciaActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}