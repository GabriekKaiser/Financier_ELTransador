package com.example.financierael_transador;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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

        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CrearCuenta_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        //Validar las creedenciales cuando el usuario toca Entrar
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxCaptcha.isChecked()) {
                    validarCredenciales();
                } else {
                    Toast.makeText(LoginActivity.this, "Por favor, marca la casilla de verificación para continuar.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void validarCredenciales(){

        String inputUserName = user.getText().toString();
        String inputPassword = password.getText().toString();

        if (inputUserName.isEmpty() || inputPassword.isEmpty()){
            Toast.makeText(this,"Por Favor ingrese su usuario y contrasena", Toast.LENGTH_SHORT).show();
            return;
        }

        //Cargar La Lista de usuarios desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userList", null);
        Type type = new TypeToken<ArrayList<Users>>() {}.getType();
        ArrayList<Users> userList = gson.fromJson(json, type);

        if (userList == null) {
            Toast.makeText(this, "No hay usuarios registrados.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean validUser = false;
        for (Users user : userList) {
            if (user.getUsername().equals(inputUserName) && user.getPassword().equals(inputPassword)) {
                validUser = true;
                break;
            }
        }

        if (validUser) {
            // Guardar el nombre de usuario en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", inputUserName);
            editor.apply();

            // Credenciales válidas, proceder con el login
            Toast.makeText(this, "Inicio de sesión exitoso.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Finaliza la LoginActivity para que no pueda regresar al login con el botón de retroceso
        }else {
            // Credenciales inválidas
            Toast.makeText(this, "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
        }

    }
}