package com.example.financierael_transador;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        // Obtener el TextView
        TextView textViewGreeting = findViewById(R.id.saludo_home);

        // Obtener el nombre del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Usuario");

        String greetingMessage = "Hola" + username;

        // Configurar el TextView con el mensaje
        textViewGreeting.setText(greetingMessage);

    }
}