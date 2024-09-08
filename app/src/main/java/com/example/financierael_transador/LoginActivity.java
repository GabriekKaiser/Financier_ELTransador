package com.example.financierael_transador;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.financierael_transador.Modulos.Users;
import com.example.financierael_transador.Servicios.ApiInterface;
import com.example.financierael_transador.Servicios.ApiService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText user, password;
    Button entrar, crearCuenta;
    CheckBox checkBoxCaptcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        entrar = findViewById(R.id.entrar);
        crearCuenta = findViewById(R.id.create_user);
        checkBoxCaptcha = findViewById(R.id.checkBoxCaptcha);

        crearCuenta.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CrearCuenta_Activity.class);
            startActivity(intent);
            finish();
        });

        entrar.setOnClickListener(v -> {
            if (checkBoxCaptcha.isChecked()) {
                validarCredenciales();
            } else {
                Toast.makeText(LoginActivity.this, "Por favor, marca la casilla de verificación para continuar.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validarCredenciales() {
        String inputUserName = user.getText().toString();
        String inputPassword = password.getText().toString();

        if (inputUserName.isEmpty() || inputPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su usuario y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiInterface apiService = ApiService.getApiService();
        Call<Users> call = apiService.buscarUsuario(inputUserName, inputPassword);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Users user = response.body();
                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

