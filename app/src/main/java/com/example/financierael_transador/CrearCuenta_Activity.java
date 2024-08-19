package com.example.financierael_transador;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kotlin.reflect.KType;

public class CrearCuenta_Activity extends AppCompatActivity {
    EditText user, password, confirmPassword;
    Button buttonCreateUser, backLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_cuenta_activity);

        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        buttonCreateUser = findViewById(R.id.button_CreateUser);

        //Aqui le asignamos a el boton su funcion CrearUsuario
        buttonCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearUsuario();
            }
        });
    }

    public void CrearUsuario(){
        String name = user.getText().toString();
        String pass = password.getText().toString();
        String confPass = confirmPassword.getText().toString();

        if (name.isEmpty() || pass.isEmpty() || confPass.isEmpty()){
            Toast.makeText(this, "Por favor complete los espacios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(confPass)){
            Toast.makeText(this, "La Contrasena no Coincide. ", Toast.LENGTH_SHORT).show();
            return;
        }

        //cargamos la lista de usuarios
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userList", null);
        Type type = new TypeToken<ArrayList<Users>>() {}.getType();
        ArrayList<Users> userList = gson.fromJson(json, type);

        if (userList == null){
            userList = new ArrayList<>();
        }
        for (Users existingUser : userList){
            if (existingUser.getUsername().equals(name)){
                Toast.makeText(this, "El Usuario Ya existe. ", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //add new user on the list
        userList.add(new Users(name,pass));

        //save the list updating on SharedPreferences
        SharedPreferences.Editor edit = sharedPreferences.edit();
        json = gson.toJson(userList);
        edit.putString("userList", json);
        edit.apply();

        //texto que confirma la creacion de usuarios
        Toast.makeText(this, "el Usuario ha sido creado con exito", Toast.LENGTH_SHORT).show();

        //Nos enviaria a la pantalla de inicio de sesion
        Intent intent = new Intent(CrearCuenta_Activity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}